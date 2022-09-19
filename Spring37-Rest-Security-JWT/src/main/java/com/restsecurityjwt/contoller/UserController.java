package com.restsecurityjwt.contoller;

import com.restsecurityjwt.entity.ResponseWrapper;
import com.restsecurityjwt.entity.User;
import com.restsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/read")
//    @PreAuthorize("hasAuthority('USER')")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<ResponseWrapper>readAll(){
        List<User> users = userService.getAll();

        return ResponseEntity.ok(new ResponseWrapper("Done", users));
    }

}
