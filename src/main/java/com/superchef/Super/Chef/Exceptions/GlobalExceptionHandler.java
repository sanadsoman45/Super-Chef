package com.superchef.Super.Chef.Exceptions;

import com.superchef.Super.Chef.entities.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(recipeAlreadyexists exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handlerException(RecipeNotFound recipeNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(recipeNotFoundException.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handlerException(UserEmailAlreadyExists useremailException){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
        exceptionResponse.setMessage(useremailException.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handlerException(userNotFound exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handlerException(IngredientAlreadyExistsException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handlerException(IngredientNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exception.getMessage());
        exceptionResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<ExceptionResponse> handlerException(Exception exception){
//        ExceptionResponse exceptionResponse = new ExceptionResponse();
//        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//        exceptionResponse.setMessage(exception.getCause().getMessage());
//        exceptionResponse.setTimeStamp(LocalDateTime.now());
//        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
//    }

}
