package com.yoon.demo.controller;

import com.yoon.demo.vo.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> NotFoundExceptionResponse(NotFoundException ex) {
        return new ResponseEntity<>(ex.getErrmsg(), ex.getHttpStatus());
    }
}
