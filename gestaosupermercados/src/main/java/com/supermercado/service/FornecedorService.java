/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Fornecedor;
import com.supermercado.repository.FornecedorRepository;
import com.supermercado.repository.GenericRepository;
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
public class FornecedorService extends GenericService<Fornecedor, Long> {

    @Inject
    private GenericRepository<Fornecedor, Long> repository;

    @Inject
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(GenericRepository<Fornecedor, Long> repository) {
        super(repository);
    }

    @PostConstruct
    public void init() {
        super.repository = repository;
    }

    public List<Fornecedor> findAll() {
        return super.findAll(Fornecedor.class); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public FornecedorService() {
        super(null); // ou algum valor padrão, dependendo da implementação do GenericService
    }

//    @Override
//    public Optional<Fornecedor> findById(Class<Fornecedor> fornecedor, Long id) {
//        return repository.findById(fornecedor, id);
//    }

}
