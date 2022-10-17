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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    @Value("${app.local-url}")
    private String BASE_URL;
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

   @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again")
   @PostMapping("/create-user")
   @Operation(summary = "Crate new account")
   private ResponseEntity<ResponseWrapper>doRegister(@RequestBody UserDTO user) throws TaskManagementException {
        UserDTO createUser = userService.save(user);
        sendEmail(createEmail(createUser));

        return ResponseEntity.ok(new ResponseWrapper("User has been created", createUser));
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
