package com.substringhashcode.emailsenderapp.helper;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseMessage {
    private String message;
    private HttpStatus httpStatus;
    private boolean success = false;
}
