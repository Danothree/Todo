package com.danoth.todo.advice;

import com.danoth.todo.exception.InvalidUserIdException;
import com.danoth.todo.exception.TitleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TitleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity titleException(TitleException e){
        log.info("title error : {}", e.getMessage());
        String error = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity userIdException(InvalidUserIdException e){
        log.info("userId error : ", e.getMessage());
        String error = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
