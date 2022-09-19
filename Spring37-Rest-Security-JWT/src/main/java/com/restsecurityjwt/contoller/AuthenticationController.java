package com.restsecurityjwt.contoller;

import com.restsecurityjwt.annotation.DefaultExceptionMessage;
import com.restsecurityjwt.entity.AuthenticationRequest;
import com.restsecurityjwt.entity.ResponseWrapper;
import com.restsecurityjwt.entity.User;
import com.restsecurityjwt.exception.ServiceException;
import com.restsecurityjwt.service.UserService;
import com.restsecurityjwt.util.JWTUtil;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/authenticate")
    @DefaultExceptionMessage(defaultMessage = "Bad Credentials")
    public ResponseEntity<ResponseWrapper>doLogin(@RequestBody AuthenticationRequest authenticationRequest){
        String password = authenticationRequest.getPassword();
        String username = authenticationRequest.getUsername();

        User foundUser = userService.readByUserName(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);

         String jwtToken = jwtUtil.generateToken(foundUser, foundUser.getUserName());

         return ResponseEntity.ok(new ResponseWrapper("Login successull", jwtToken));
    }

    @PostMapping("/create-user")
    public ResponseEntity<ResponseWrapper>createAccount(@RequestBody User user)throws ServiceException{
        User createUser = userService.createUser(user);
        return ResponseEntity.ok(new ResponseWrapper("User has been created Successfully", createUser));
    }
}
