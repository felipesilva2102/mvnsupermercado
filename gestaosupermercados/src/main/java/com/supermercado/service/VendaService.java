/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Venda;
import com.supermercado.repository.VendaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class VendaService extends GenericService<Venda, Long> {
   
    @Inject
    public VendaService(VendaRepository repository) {
        super(repository);
    }

    // Necessário construtor padrão para proxy:
    public VendaService() {
        super(null);
    }
   
}

