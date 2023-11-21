package com.yobombel.brewshare.beer.exception;

public class BeerNotFoundException extends RuntimeException{

    public BeerNotFoundException(Long id){
        super("Beer not found, id: " + id);
    }

}