package com.yobombel.brewshare;

public interface CRUDService <T, ID> {

    ID add(T entity);

}