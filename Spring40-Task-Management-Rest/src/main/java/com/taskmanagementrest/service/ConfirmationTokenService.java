package com.taskmanagementrest.service;

import com.taskmanagementrest.entity.ConfirmationToken;
import com.taskmanagementrest.exception.TaskManagementException;
import org.springframework.mail.SimpleMailMessage;

public interface ConfirmationTokenService {

    ConfirmationToken save(ConfirmationToken confirmationToken);
    void sendEmail(SimpleMailMessage email);
    ConfirmationToken readByToken(String token) throws TaskManagementException;
    void delete(ConfirmationToken confirmationToken);
}
