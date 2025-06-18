/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Cliente;
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
public class ClienteService extends GenericService<Cliente, Long> {

    @Inject
    private GenericRepository<Cliente, Long> repository;

    // construtor padrão sem parâmetros, chama super com repository injetado
    public ClienteService() {
        super(null); // por enquanto, passamos null no construtor pai, mas vamos sobrescrever a referência abaixo
    }

    @PostConstruct
    public void init() {
        super.repository = repository; // inicializa o repository no pai após injeção
    }

    public List<Cliente> findAll() {
        return super.findAll(Cliente.class);
    }
}

