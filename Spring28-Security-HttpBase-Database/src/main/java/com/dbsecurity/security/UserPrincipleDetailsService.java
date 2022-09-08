package com.dbsecurity.security;

import com.dbsecurity.entity.User;
import com.dbsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipleDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipleDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        UserPrinciple userPrinciple = new UserPrinciple(user);
        return userPrinciple;
    }
}
