/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.repository.GenericRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Felipe
 */
public abstract class GenericService<T, ID> {

    protected GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll(Class<T> entityClass) {
        return repository.findAll(entityClass);
    }

    public Optional<T> findById(Class<T> entityClass, ID id) {
        return repository.findById(entityClass, id);
    }

    public void deleteAll(Class<T> entityClass) {
        repository.deleteAll(entityClass);
    }

    public void deleteById(Class<T> entityClass, ID id) {
        repository.deleteById(entityClass, id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }

    public T update(T entity) {
        return repository.update(entity);
    }
}
