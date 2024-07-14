package com.scaler.productservice.controlleradvise;

import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.dtos.ExceptionDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.rmi.ServerException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handlerArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Arithmetic exception has happened");
        exceptionDto.setSolution("I don't know, please try again");

        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handlerBadRequestException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "Bad Request happened",
                HttpStatus.BAD_REQUEST
        );
        return responseEntity;
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handlerNotFoundException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "Not Found happened",
                HttpStatus.NOT_FOUND
        );
        return responseEntity;
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> handlerServerErrorException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "Internal Server Error happened",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    public ResponseEntity<String> handlerGateWayTimeOutErrorException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "Gateway timout happened",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @ExceptionHandler(HttpServerErrorException.BadGateway.class)
    public ResponseEntity<String> handlerBadGateWayErrorException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "Bad Gateway",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlerProductNotFoundException(ProductNotFoundException ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("The provided product " + ex.getProdcut() + " not found");

        exceptionDto.setSolution("Provide a valid product ID");

        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );
        return responseEntity;
    }


}
