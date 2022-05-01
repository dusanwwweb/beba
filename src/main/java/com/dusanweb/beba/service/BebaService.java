package com.dusanweb.beba.service;

import com.dusanweb.beba.model.Post;

import java.util.List;
import java.util.Optional;

public interface BebaService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void delete(T t);

    void deleteById(Long id);

}
