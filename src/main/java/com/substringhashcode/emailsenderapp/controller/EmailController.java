package com.substringhashcode.emailsenderapp.controller;

import com.substringhashcode.emailsenderapp.entity.EmailRequest;
import com.substringhashcode.emailsenderapp.helper.ResponseMessage;
import com.substringhashcode.emailsenderapp.service.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final IEmailService emailService;


    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/sendEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendHtmlEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        emailService.sendEmailUsingHTML(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
        return ResponseEntity.ok(ResponseMessage.builder().message("Email send successfully...").httpStatus(HttpStatus.OK).success(true).build());
    }

    @PostMapping("/send-email-file")
    public ResponseEntity<ResponseMessage> sendEmailWithAttachment(@RequestPart EmailRequest emailRequest, @RequestPart MultipartFile multipartFile) throws IOException {
        emailService.sendEmailWithAttachmentUsingInputStream(emailRequest.getTo(),
                emailRequest.getSubject(), emailRequest.getMessage(),
                multipartFile.getInputStream());
        return ResponseEntity.ok(ResponseMessage.builder().message("Email send successfully...").httpStatus(HttpStatus.OK).success(true).build());
    }
}
