package com.taskmanagementrest.implementation;


import com.taskmanagementrest.entity.User;
import com.taskmanagementrest.entity.common.UserPrinciple;
import com.taskmanagementrest.repository.UserRepository;
import com.taskmanagementrest.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
