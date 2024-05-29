package com.substringhashcode.emailsenderapp.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
}
