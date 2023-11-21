package com.yobombel.brewshare;

import java.util.List;

public interface CRUDService<T, Id> {

    Id add(T entity);

    List<T> findAll();

    T findById(Id id);

    void update(Id id, T entity);

    void deleteById(Id id);

}