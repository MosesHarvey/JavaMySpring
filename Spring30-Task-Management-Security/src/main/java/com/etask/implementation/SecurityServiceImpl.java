package com.etask.implementation;

import com.etask.entity.User;
import com.etask.entity.common.UserPrinciple;
import com.etask.repository.UserRepository;
import com.etask.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityServiceImpl implements SecurityService {


    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User does not exist!");
        }

        return new UserPrinciple(user);

    }
}
