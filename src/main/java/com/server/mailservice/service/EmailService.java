package com.server.mailservice.service;

import com.server.mailservice.dto.MailDTO;

public interface EmailService {

    void receiveMessage(MailDTO mail);
}
