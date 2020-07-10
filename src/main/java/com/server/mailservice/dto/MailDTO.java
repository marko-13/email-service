package com.server.mailservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class MailDTO {

    private UUID id;
    private String role;
    private String username;
    private String firstName;
    private String lastName;
    private String name;
}