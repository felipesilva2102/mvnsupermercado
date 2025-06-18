/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Produto;
import com.supermercado.repository.GenericRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class ProdutoService extends GenericService<Produto, Long> {
    
    @Inject
    private GenericRepository<Produto, Long> repository;

    public ProdutoService() {
        super(null);
    }

    @PostConstruct
    public void init() {
        super.repository = repository;
    }    

    public List<Produto> findAll() {
        return super.findAll(Produto.class);
    }
    
}
