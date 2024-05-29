package com.substringhashcode.emailsenderapp;

import com.substringhashcode.emailsenderapp.service.IEmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@SpringBootTest
class EmailSenderTest {
    private static final String to = "ravi74079@gmail.com";

    @Autowired
    private IEmailService emailService;

    @Test
    void emailSendTest() {
        log.info("Sending mail...");
        emailService.sendEmail(to, "Testing email functionality", "This email is for testing purpose only. Please ignore...!!!");
    }

    @Test
    void sendHtmlEmailTest() throws MessagingException {
        log.info("Sending HTML email to receiver...");
        String htmlText = "<h1 style='color:blue;border:2px solid green;'>Substring Coder testing email with html functionality...</h1>";
        emailService.sendEmailUsingHTML(to, "Testing HTML email functionality", htmlText);
    }

    @Test
    void sendEmailWithAttachment() {
        log.info("Sending attachment mail to receiver...");
        emailService.sendEmailWithAttachment(to, "testing email with attachment", "file is attached.PFA", new File("C:\\Users\\ravi7\\IdeaProjects\\email-sender-app\\src\\main\\resources\\static\\abc.jpg"));
    }

    @Test
    void sendEmailWithAttachmentUsingInputStream() {
        log.info("Sending attachment mail to receiver...");
        File file = new File("C:\\Users\\ravi7\\IdeaProjects\\email-sender-app\\src\\main\\resources\\static\\abc.jpg");
        try {
            InputStream inputStream = new FileInputStream(file);
            emailService.sendEmailWithAttachmentUsingInputStream(to, "Testing email with attachment using inputstream",
                    "Input stream file is attached. PFA", inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
