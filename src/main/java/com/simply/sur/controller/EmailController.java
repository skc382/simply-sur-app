package com.simply.sur.controller;

import com.simply.sur.entity.EmailMessage;
import com.simply.sur.entity.EmailResponse;
import com.simply.sur.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/email",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailMessage emailMessage) {
        return emailService.sendEmail(emailMessage);
    }
}
