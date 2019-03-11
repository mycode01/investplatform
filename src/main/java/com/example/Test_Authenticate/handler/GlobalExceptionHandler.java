package com.example.Test_Authenticate.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Message handleException(HttpServletRequest request, Exception e){
//        return Message.builder().msg(e.getMessage()).status(HttpStatus.BAD_REQUEST).build();
//    }
//

    @Builder
    @Getter @Setter
    private static class Message{
        String msg;
        HttpStatus status;
    }
}
