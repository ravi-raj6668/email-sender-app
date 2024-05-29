package com.substringhashcode.emailsenderapp.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Slf4j
@Service
public class EmailService implements IEmailService {

    private static final String FROM = "work.devravi@gmail.com";
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        log.info("Sending Text Email To Receiver");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            simpleMailMessage.setFrom(FROM);
            mailSender.send(simpleMailMessage);
            log.info("Email Send Successfully!!!");
        } catch (Exception e) {
            log.error("Error while sending email {}: ", e.getMessage());
        }
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        log.info("Sending Text Email To Multiple Receiver");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            simpleMailMessage.setFrom(FROM);
            mailSender.send(simpleMailMessage);
            log.info("Email Send Successfully!!!");
        } catch (Exception e) {
            log.error("Error while sending email {}: ", e.getMessage());
        }

    }

    @Override
    public void sendEmailUsingHTML(String to, String subject, String htmlContent) {
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(FROM);
            messageHelper.setText(htmlContent, true);
            mailSender.send(simpleMailMessage);
            log.info("Email html-type send successfully");
        } catch (MessagingException e) {
            log.error("Error while sending mail {} : ", e.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(FROM);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            messageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
            mailSender.send(mimeMessage);
            log.info("Email Send Successfully...!!!");
        } catch (MessagingException e) {
            log.error("Error while sending mail {}: ", e.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachmentUsingInputStream(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(FROM);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(message, true);
            File file = new File("src/main/resources/static/email/abc.jpg");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            messageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
            mailSender.send(mimeMessage);
            log.info("Email Send Successfully...!!!");
        } catch (MessagingException | IOException e) {
            log.error("Error while sending mail {}: ", e.getMessage());
        }
    }
}
