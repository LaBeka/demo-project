package edu.example.demoproject.validation;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalRestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String controllerHandler(ConstraintViolationException ex){
        System.out.println(ex);
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String controllerHandler(Exception ex){
        System.out.println(ex.toString());
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }
}
