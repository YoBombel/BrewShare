package com.yobombel.brewshare.beer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeerNotFoundException extends RuntimeException{

    private static final Logger log = LoggerFactory.getLogger(BeerNotFoundException.class);


    public BeerNotFoundException(Long id){
        super("Beer not found, id: " + id);
        log.info("Beer not found, id: {}", id);
    }

}