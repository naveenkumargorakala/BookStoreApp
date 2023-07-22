package com.example.bookstoreproject.exception;

import com.example.bookstoreproject.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {



    //Handle the Exceptions regarding validation
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> validateExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> errorList = exception.getBindingResult().getFieldErrors();
        List<String> errorList1 = errorList.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
        Response responseDTO = new Response("Invalid Format...", errorList1);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }


    //Handle the custom Exceptions which are not available in database
    @org.springframework.web.bind.annotation.ExceptionHandler(ExceptionClass.class)
    public ResponseEntity<Response> customException(ExceptionClass exception) {
        Response responseDTO = new Response(exception.getMessage(), "Exception while Request Processing...");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    //Handle media type Not acceptable exception
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Response> mediaTypeNotAcceptableHandler(HttpMediaTypeNotAcceptableException exception) {
        Response responseDTO = new Response("Invalid Media", "Check media Type ");
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    //Handle method Not supported exception
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response> requestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException exception) {
        Response responseDTO = new Response("Invalid API call", "Check API!!! ");
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    //Handle the message Not Readable Exception
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> messageNotReadableHandler(HttpMessageNotReadableException exception){
        Response responseDTO = new Response("You are entered not required message ","Check message any mistakes are there..!");
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_GATEWAY);
    }
}
