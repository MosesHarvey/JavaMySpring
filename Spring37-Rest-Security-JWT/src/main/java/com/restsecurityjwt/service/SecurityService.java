package com.restsecurityjwt.service;

import com.restsecurityjwt.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {

    private UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = loadUser(username);
        if(foundUser == null){
            throw new UsernameNotFoundException("user not found" + username);
        }

        return new org.springframework.security.core.userdetails.User(foundUser.getUserName(), foundUser.getPassword(), listAuthorities(foundUser));
    }

    public User loadUser(String value){

        boolean isEmail = value.contains("@");
        return isEmail ? userService.readByEmail(value)  : userService.readByUserName(value);

    }

    private List<GrantedAuthority> listAuthorities(User user){
        List<GrantedAuthority>grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        return grantedAuthorityList;
    }
}
