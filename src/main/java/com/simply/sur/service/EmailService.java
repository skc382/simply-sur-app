package com.simply.sur.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.simply.sur.entity.EmailMessage;
import com.simply.sur.entity.EmailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class EmailService {

    static final String FROM = "kc.sridhar@gmail.com";

    static final String TO = "kc.sridhar@gmail.com";

    static final String SUBJECT = "DO NOT REPLY - SimplySur Client Email from %s";

    // The email body for recipients with non-HTML email clients.
    static final String TEXTBODY = "This email was sent simply sur web application";

    @Autowired
    private Environment env;

    AmazonSimpleEmailService client;

    @PostConstruct
    public void init() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(env.getProperty("aws.ses.access.key.id"),
                env.getProperty("aws.ses.secret.access.key"));
        client = AmazonSimpleEmailServiceClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public ResponseEntity<EmailResponse> sendEmail(EmailMessage emailMessage) {
        try {

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(getHtmlbody(emailMessage)))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(String.format(SUBJECT, emailMessage.getName().toUpperCase()))))
                    .withSource(FROM);
            client.sendEmail(request);
            log.info("Email sent!");
        } catch (Exception ex) {
            log.error("The email was not sent. Error message: "
                    + ex.getMessage());
            return new ResponseEntity<>(new EmailResponse("Email was not sent. Error message: "
                    +ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new EmailResponse("Email Successfully Sent!"), HttpStatus.OK);
    }

    private String getHtmlbody (EmailMessage emailMessage) {
        String htmlBody = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>SimplySur Email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <span>\n" +
                "            <strong>Name: </strong> %s\n" +
                "        </span>\n" +
                "        <br>\n" +
                "        <span>\n" +
                "            <strong>Email: </strong> %s\n" +
                "        </span>\n" +
                "        <br>\n" +
                "        <span>\n" +
                "            <strong>Subject: </strong> %s\n" +
                "        </span>\n" +
                "        <br>\n" +
                "        <span>\n" +
                "            <p>\n" +
                "                <strong>Message: </strong> %s\n" +
                "            </p>\n" +
                "        </span>\n" +
                "        <br>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>", emailMessage.getName(), emailMessage.getEmail(),
                emailMessage.getSubject(), emailMessage.getMessage());

        return htmlBody;
    }
}
