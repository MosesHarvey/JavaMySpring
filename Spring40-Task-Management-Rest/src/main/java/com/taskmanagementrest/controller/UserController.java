package com.taskmanagementrest.controller;


import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.MailDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.ConfirmationToken;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.implementation.ConfirmationTokenServiceImpl;
import com.taskmanagementrest.mapper.MapperUtil;
import com.taskmanagementrest.service.ConfirmationTokenService;
import com.taskmanagementrest.service.RoleService;
import com.taskmanagementrest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Tag(name ="User Controller", description = "User API")
public class UserController {

    @Value("${app.local-url}")
    private String BASE_URL;

    private UserService userService;
    private MapperUtil mapperUtil;
    private ConfirmationTokenService confirmationTokenService;
    private RoleService roleService;


    public UserController(UserService userService, MapperUtil mapperUtil, ConfirmationTokenService confirmationTokenService, RoleService roleService) {
        this.userService = userService;
        this.mapperUtil = mapperUtil;
        this.confirmationTokenService = confirmationTokenService;
        this.roleService = roleService;
    }

    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @PostMapping("/create-user")
    @Operation(summary = "Crate new account")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ResponseWrapper> doRegister(@RequestBody UserDTO user) throws TaskManagementException {

        UserDTO createUser = userService.save(user);
        sendEmail(createEmail(createUser));

        return ResponseEntity.ok(new ResponseWrapper("User has been created", createUser));
    }

    @GetMapping
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @Operation(summary = "Read All Users")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ResponseWrapper>readAll(){
       List<UserDTO> users = userService.listAllUsers();
       return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved User", users));
    }

    @GetMapping("/{username}")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @Operation(summary = "Read a User")
    // admin can see other's profile, current user can see his/her own profile
    public ResponseEntity<ResponseWrapper>readByUserName(@PathVariable("username") String username){


        UserDTO userDTO = userService.findByUserName(username);
        return ResponseEntity.ok(new ResponseWrapper("User successfully retrieved", userDTO));
    }




    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @PutMapping("/update")
    @Operation(summary = "Update a User")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user) throws TaskManagementException {

        UserDTO updateUser = userService.update(user);

        return ResponseEntity.ok(new ResponseWrapper("User has been updated", updateUser));
    }

    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @DeleteMapping("/delete")
    @Operation(summary = "Delete a User")
    @PreAuthorize("hasAuthority('Admin')")
   public ResponseEntity<ResponseWrapper>deleteUser(@PathVariable("username") String username) throws TaskManagementException {
        userService.delete(username);
        return ResponseEntity.ok(new ResponseWrapper("successfully deleted"));
   }

    @GetMapping("/role")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
    @Operation(summary = "Read All Users by role")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
   public ResponseEntity<ResponseWrapper>readByRole(@RequestParam String role){
        List<UserDTO>userList = userService.listAllByRole(role);
        return ResponseEntity.ok(new ResponseWrapper("successfully retrieved users", userList));
   }





    private MailDTO createEmail(UserDTO userDTO) {

        User user =mapperUtil.convert(userDTO, new User());
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationToken.setDeleted(false);

        ConfirmationToken createdConfirmationToken = confirmationTokenService.save(confirmationToken);

        return MailDTO.builder()
                .emailTo(user.getUserName())
                .token(createdConfirmationToken.getToken())
                .subject("Confirm Registration")
                .message("To Confirm your account, please click here")
                .url(BASE_URL + "/confirmation?token=")
                .build();
    }

    private void sendEmail(MailDTO mailDTO) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailDTO.getEmailTo());
        mailMessage.setSubject(mailDTO.getSubject());
        mailMessage.setText(mailDTO.getMessage()+mailDTO.getUrl()+mailDTO.getToken());

        confirmationTokenService.sendEmail(mailMessage);
    }



}
