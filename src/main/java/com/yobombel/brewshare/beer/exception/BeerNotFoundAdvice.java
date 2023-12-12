package com.yobombel.brewshare.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BeerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BeerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String beerNotFoundHandler(BeerNotFoundException exception){
        return exception.getMessage();
    }
}
