package com.taskmanagementrest.controller;



import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.entity.common.AuthenticationRequest;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.mapper.MapperUtil;
import com.taskmanagementrest.service.UserService;
import com.taskmanagementrest.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@DefaultExceptionMessage(defaultMessage = "Bad Credentials")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private MapperUtil mapperUtil;
    private JWTUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, MapperUtil mapperUtil, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseWrapper>doLogin(@RequestBody AuthenticationRequest authenticationRequest) throws TaskManagementException {
       String password = authenticationRequest.getPassword();
       String username = authenticationRequest.getUsername();

        UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);

        UserDTO foundUser = userService.findByUserName(username);
        User convertedUser = mapperUtil.convert(foundUser, new User());

        if(!foundUser.isEnabled()){
            throw new TaskManagementException("Please verify your user");
        }

        String jwtToken = jwtUtil.generateToken(convertedUser);

        return ResponseEntity.ok(new ResponseWrapper("Login Successful", jwtToken));

   }



}
