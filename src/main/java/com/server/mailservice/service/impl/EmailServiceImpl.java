package com.server.mailservice.service.impl;

import com.server.mailservice.config.EmailContext;
import com.server.mailservice.dto.MailDTO;
import com.server.mailservice.service.EmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailContext _emailContext;

    public EmailServiceImpl(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void receiveMessage(MailDTO mail){
        if(mail.getRole().equals("Agent")){
            agentRegistrationMail(mail);
        }else {
            approveRegistrationMail(mail);
        }
    }

    public void approveRegistrationMail(MailDTO simpleUser) {
        String to = simpleUser.getUsername();
        String subject = "Simple user registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", simpleUser.getFirstName(), simpleUser.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/auth/login/%s/simple-user", simpleUser.getId()));
        _emailContext.send(to, subject, "approveRegistration", context);
        System.out.println("proslo slanje simple user maila");
    }

    public void agentRegistrationMail(MailDTO agent) {
        System.out.println(agent);
        String to = agent.getUsername();
        String subject = "Agent registration announcement";
        Context context = new Context();
        context.setVariable("name", String.format("%s", agent.getName()));
        _emailContext.send(to, subject, "agentRegistration", context);
        System.out.println("proslo slanje agent maila");
    }
}