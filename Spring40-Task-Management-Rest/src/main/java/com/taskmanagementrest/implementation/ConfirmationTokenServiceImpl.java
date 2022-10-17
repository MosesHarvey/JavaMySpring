package com.taskmanagementrest.implementation;

import com.taskmanagementrest.entity.ConfirmationToken;
import com.taskmanagementrest.exception.TaskManagementException;
import com.taskmanagementrest.repository.ConfirmationTokenRepository;
import com.taskmanagementrest.service.ConfirmationTokenService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;
    private JavaMailSender javaMailSender;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, JavaMailSender javaMailSender) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ConfirmationToken save(ConfirmationToken confirmationToken) {

        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public ConfirmationToken readByToken(String token) throws TaskManagementException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElse(null);
        if(confirmationToken==null){
            throw new TaskManagementException("This token does not exist");
        }

        if(!confirmationToken.isTokenValid(confirmationToken.getExpireDate())){
            throw new TaskManagementException("This token has been expired");

        }
        return confirmationToken;
    }

    @Override
    public void delete(ConfirmationToken confirmationToken) {
     confirmationToken.setDeleted(true);
     confirmationTokenRepository.save(confirmationToken);
    }
}
