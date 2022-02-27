package com.licenta.demo.dao;

import java.util.List;

public interface AbstractDAO<T> {

    List<T> getAll();

    T getById(Long id);

    T add(T t) throws Exception;

    T update(T t);

    void remove(T t);

    void removeById(Long id);
}