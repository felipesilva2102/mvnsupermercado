/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Fornecedor;
import com.supermercado.repository.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class FornecedorService extends GenericService<Fornecedor, Long> {

    public FornecedorService(GenericRepository<Fornecedor, Long> repository) {
        super(repository);
    }

    public List<Fornecedor> findAll() {
        return super.findAll(Fornecedor.class); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public FornecedorService() {
        super(null); // ou algum valor padrão, dependendo da implementação do GenericService
    }

}
