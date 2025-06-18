/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.beans;

import com.supermercado.entity.Cliente;
import com.supermercado.entity.ItemVenda;
import com.supermercado.entity.Venda;
import com.supermercado.service.ClienteService;
import com.supermercado.service.VendaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
@Named
@ViewScoped
public class VendaMBean implements Serializable {

    private Venda venda;
    private List<Cliente> clientes;
    private List<ItemVenda> itensVenda;
    private List<Venda> vendas;
    
    @Inject
    private ClienteService clienteService;
    
    @Inject
    private VendaService vendaService;

    @PostConstruct
    public void init() {
        venda = new Venda();
        itensVenda = new ArrayList<>();
        clientes = clienteService.findAll(); 
        vendas = vendaService.findAll(Venda.class);  
    }

    public void adicionarItem() {
        itensVenda.add(new ItemVenda());
    }

    public void salvar() {
        venda.setItens(itensVenda);
        vendaService.save(venda);
        venda = new Venda();
        itensVenda = new ArrayList<>();
    }
}
