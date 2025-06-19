/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Produto;
import com.supermercado.repository.GenericRepository;
import com.supermercado.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class ProdutoService extends GenericService<Produto, Long> {
    
    @Inject
    private GenericRepository<Produto, Long> repository;
    
    @Inject
    private ProdutoRepository produtoRepository;

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

    public Optional<Produto> findByCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras);
    }
    
}
