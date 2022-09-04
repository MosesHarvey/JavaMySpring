package com.etask.implementation;

import com.etask.dto.UserDTO;
import com.etask.entity.User;
import com.etask.mapper.UserMapper;
import com.etask.repository.UserRepository;
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
    @Override
    public List<UserDTO> listAllUsers() {

        List<User>list = userRepository.findAll(Sort.by("firstName"));
        // convert to dto
        return list.stream().map(obj->{return userMapper.convertToDTO(obj);}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO dto) {
      User obj = userMapper.convertToEntity(dto);
      userRepository.save(obj);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        return null;
    }

    @Override
    public void delete(String username) {

    }
}
