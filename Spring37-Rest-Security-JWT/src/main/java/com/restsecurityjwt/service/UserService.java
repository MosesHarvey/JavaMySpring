package com.restsecurityjwt.service;

import com.restsecurityjwt.entity.User;
import com.restsecurityjwt.enums.UserState;
import com.restsecurityjwt.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User readByUserName(String username){
        return userRepository.findByUserName(username).orElse(null);
    }

    public User readByEmail(String email){
        return userRepository.findByUserName(email).orElse(null);
    }

    public List<User> getAll(){
       return userRepository.findAll();
    }

    @Transactional
    public User createUser(User user){
        User foundUserByEmail = readByEmail(user.getEmail());
        User foundUserByUserName = readByUserName(user.getUserName());
        if(foundUserByEmail != null){
            throw new ServiceException("The user already exists, Please change your email");
        }
        if(foundUserByUserName != null){
            throw new ServiceException("The user already exists, Please change your User name");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsVerified(false);
        return userRepository.save(user);

    }

    @Transactional
    public User verifyUser(User user){
        user.setIsVerified(true);
        user.setUserState(UserState.ACTIVE);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new ServiceException("This user doesn't exist");
        }
        user.setIsVerified(false);
        userRepository.save(user);
    }

    @Transactional
    public User resetPassword(User user){
        User foundUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(foundUser == null){
            throw new ServiceException("User with email doesn't exist: "+user.getEmail());

        }
        foundUser.setUserState(UserState.ACTIVE);
        foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(foundUser);
    }


}
