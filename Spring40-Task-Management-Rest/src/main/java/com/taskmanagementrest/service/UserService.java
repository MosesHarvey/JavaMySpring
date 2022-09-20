package com.taskmanagementrest.service;




import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.exception.TaskManagementException;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(String username) throws TaskManagementException;

    void deleteByUserName(String username);

    List<UserDTO>listAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);
}
