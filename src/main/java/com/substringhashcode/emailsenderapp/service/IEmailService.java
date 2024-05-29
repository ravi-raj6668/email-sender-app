package com.substringhashcode.emailsenderapp.service;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.InputStream;

public interface IEmailService {
    //send email to single person
    public void sendEmail(String to, String subject, String message);

    //send email to multiple person
    public void sendEmail(String[] to, String subject, String message);

    //send email using html content
    public void sendEmailUsingHTML(String to, String subject, String htmlContent) throws MessagingException;

    //send email with attachment
    public void sendEmailWithAttachment(String to, String subject, String message, File file);

    public void sendEmailWithAttachmentUsingInputStream(String to, String subject, String message, InputStream inputStream);
}
