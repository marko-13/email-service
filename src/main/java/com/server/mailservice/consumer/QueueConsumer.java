package com.server.mailservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.mailservice.dto.MailDTO;
import com.server.mailservice.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    EmailServiceImpl emailService;

    public void receiveMessage(String message) {
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        processMessage(strMessage);
    }
    private void processMessage(String message) {
        try {
            MailDTO mail = new ObjectMapper().readValue(message, MailDTO.class);
            emailService.receiveMessage(mail);
        } catch(Exception e) {
            System.out.println("Error, email not sent");
        }
    }
}