package br.com.netdeal.contrusapplication.interfaces.repositories;

import java.util.List;

public interface IBaseRepository<T> {
    T save(T entity);
    T saveAndFlush(T entity);
    List<T> findAll();
    T findById(long id);
}
