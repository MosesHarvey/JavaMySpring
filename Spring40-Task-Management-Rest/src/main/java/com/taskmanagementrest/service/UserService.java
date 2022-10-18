package com.taskmanagementrest.service;




import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.exception.TaskManagementException;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    UserDTO save(UserDTO dto) throws TaskManagementException;
    UserDTO update(UserDTO dto) throws TaskManagementException;
    void delete(String username) throws TaskManagementException;

    void deleteByUserName(String username);

    List<UserDTO>listAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);

    UserDTO confirm(User user);
}
