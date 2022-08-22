package com.etask.service;

import com.etask.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String> {
    // save user
    // find user by user name
    // delete

//    UserDTO save(UserDTO user);
//    UserDTO findByID(String username);
//    List<UserDTO> findAll();
//    void delete(UserDTO user);
//    void deleteByID(String username);

    List<UserDTO>findManagers();
    List<UserDTO>findEmployees();


}
