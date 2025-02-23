package com.cgesgin.blogging_platform_api.model.service;

import java.util.List;

public interface IGenericService<T>{
    
    T save(T entity);
    
    T update(T entity);
    
    void delete(Long id);
    
    T findById(Long id);
    
    List<T> findAll();
}
