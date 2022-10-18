package com.taskmanagementrest.controller;



import com.taskmanagementrest.annotation.DefaultExceptionMessage;
import com.taskmanagementrest.dto.MailDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.entity.ConfirmationToken;
import com.taskmanagementrest.entity.ResponseWrapper;
import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.entity.common.AuthenticationRequest;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.mapper.MapperUtil;
import com.taskmanagementrest.service.ConfirmationTokenService;
import com.taskmanagementrest.service.UserService;
import com.taskmanagementrest.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name ="Login Controller", description = "Authenticate API")
public class LoginController {


    private AuthenticationManager authenticationManager;
    private UserService userService;
    private MapperUtil mapperUtil;
    private JWTUtil jwtUtil;
    private ConfirmationTokenService confirmationTokenService;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, MapperUtil mapperUtil, JWTUtil jwtUtil, ConfirmationTokenService confirmationTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
        this.jwtUtil = jwtUtil;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping("/authenticate")
    @DefaultExceptionMessage(defaultMessage = "Bad Credentials")
    @Operation(summary = "Login to application")
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



    @DefaultExceptionMessage(defaultMessage = "Failed to confirm email, try again")
    @GetMapping("/confirmation")
    @Operation(summary = "Confirm account")
    public ResponseEntity<ResponseWrapper>confirmEmail(@RequestParam("token") String token) throws TaskManagementException {
        ConfirmationToken confirmationToken = confirmationTokenService.readByToken(token);

        UserDTO confirmUser = userService.confirm(confirmationToken.getUser());
        confirmationTokenService.delete(confirmationToken);

        return ResponseEntity.ok(new ResponseWrapper("User has been confirmed", confirmUser));
    }




}
