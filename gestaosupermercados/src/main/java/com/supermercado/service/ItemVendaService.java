/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.ItemVenda;
import com.supermercado.repository.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class ItemVendaService extends GenericService<ItemVenda, Long> {
    
    public ItemVendaService(GenericRepository<ItemVenda, Long> repository) {
        super(repository);
    }

    public List<ItemVenda> findAll() {
        return super.findAll(ItemVenda.class); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
