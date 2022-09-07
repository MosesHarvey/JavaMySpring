package com.etask.implementation;

import com.etask.dto.ProjectDTO;
import com.etask.dto.TaskDTO;
import com.etask.dto.UserDTO;
import com.etask.entity.User;
import com.etask.mapper.UserMapper;
import com.etask.repository.UserRepository;
import com.etask.service.ProjectService;
import com.etask.service.TaskService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Lazy
    @Autowired
    UserRepository userRepository;
    @Lazy
    @Autowired
    UserMapper userMapper;

    @Lazy
    @Autowired
    private ProjectService projectService;

    @Lazy
    @Autowired
    private TaskService taskService;

    @Override
    public List<UserDTO> listAllUsers() {

        List<User>list = userRepository.findAll(Sort.by("firstName"));
        // convert to dto
        return list.stream().map(obj->{return userMapper.convertToDTO(obj);}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {

        User user = userRepository.findByUserName(username);
        return userMapper.convertToDTO(user) ;
    }

    @Override
    public void save(UserDTO dto) {
      User obj = userMapper.convertToEntity(dto);
      userRepository.save(obj);
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User user = userRepository.findByUserName(dto.getUserName());
        // map update user tp dto to entity
        User convertedUser = userMapper.convertToEntity(dto);
        // set id to converted user
        convertedUser.setId(user.getId());
        // save updated user
        userRepository.save(convertedUser);
        return findByUserName(dto.getUserName());
    }

    @Override
    public void delete(String username) {
     User user = userRepository.findByUserName(username);
     user.setDeleted(true);
     userRepository.save(user);
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User>users = userRepository.findAllByRoleDescriptionIgnoreCase(role);

        return users.stream().map(obj->{return userMapper.convertToDTO(obj);}).collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfUserCanBeDeleted(User user) {

        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO>projectList = projectService.readAllByAssignedManager(user);
                return projectList.size()==0;
            case "Employee":
                List<TaskDTO>taskDTOS = taskService.readAllByEmployee(user);
                return taskDTOS.size()==0;
            default:
                return true;

        }

    }
}
