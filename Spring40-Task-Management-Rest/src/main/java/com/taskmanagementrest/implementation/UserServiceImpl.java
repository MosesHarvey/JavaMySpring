package com.taskmanagementrest.implementation;


import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.TaskDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.util.MapperUtil;
import com.taskmanagementrest.repository.UserRepository;
import com.taskmanagementrest.service.ProjectService;
import com.taskmanagementrest.service.TaskService;
import com.taskmanagementrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Lazy
    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private MapperUtil mapperUtil;
    @Lazy
    @Autowired
    private ProjectService projectService;

    @Lazy
    @Autowired
    private TaskService taskService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> listAllUsers() {

        List<User>list = userRepository.findAll(Sort.by("firstName"));
        // convert to dto
        return list.stream().map(obj->{return mapperUtil.convert(obj, new UserDTO());}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) throws AccessDeniedException {

        User user = userRepository.findByUserName(username);
        checkForAuthorities(user);
        return mapperUtil.convert(user, new UserDTO());
    }

    @Override
    public UserDTO save(UserDTO dto) throws TaskManagementException {
        User foundUser = userRepository.findByUserName(dto.getUserName());
        if(foundUser!=null) throw new TaskManagementException("User already exist");

      User user = mapperUtil.convert(dto, new User());
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      User suser = userRepository.save(user);

      return mapperUtil.convert(suser, new UserDTO());

    }

    @Override
    public UserDTO update(UserDTO dto) throws TaskManagementException, AccessDeniedException {
        // find current user
        User user = userRepository.findByUserName(dto.getUserName());

        if(user == null) throw new TaskManagementException("User does not exist");
        // map update user to dto to entity
        User convertedUser = mapperUtil.convert(dto, new User());
        // set id to converted user
        convertedUser.setId(user.getId());
        convertedUser.setPassword(passwordEncoder.encode(convertedUser.getPassword()));

        if(!user.isEnabled()) throw new TaskManagementException("User is not confirmed");

        checkForAuthorities(user);
        convertedUser.setEnabled(true);
        convertedUser.setId(user.getId());

        // save updated user

        userRepository.save(convertedUser);
        return findByUserName(dto.getUserName());
    }

    @Override
    public void delete(String username) throws TaskManagementException {
     User user = userRepository.findByUserName(username);

     if(user == null){
         throw new TaskManagementException("User does not exist");
     }
     if(!checkIfUserCanBeDeleted(user)){
         throw new TaskManagementException("User can not be deleted. It is linked by a project of task");

     }

     user.setUserName(user.getUserName() +"_" +user.getId());

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

        return users.stream().map(obj->{return  mapperUtil.convert(obj, new UserDTO());}).collect(Collectors.toList());
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

    @Override
    public UserDTO confirm(User user) {
        user.setEnabled(true);
        User confirmUser = userRepository.save(user);
        return mapperUtil.convert(confirmUser, new UserDTO());
    }

    private void checkForAuthorities(User user) throws AccessDeniedException {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && !authentication.getName().equals("anonymousUser")) {
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
            if(!authentication.getName().equals(user.getId().toString()) || roles.contains("Admin")){
                throw new AccessDeniedException("Access is Denied");
            }

        }
    }
}
