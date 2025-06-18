/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.beans;

import com.supermercado.entity.CategoriaProduto;
import com.supermercado.entity.Fornecedor;
import com.supermercado.entity.Produto;
import com.supermercado.repository.FornecedorRepository;
import com.supermercado.service.FornecedorService;
import com.supermercado.service.ProdutoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Felipe
 */
@Named
@ViewScoped
@Getter
@Setter
public class ProdutoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Produto> mercadorias = new ArrayList<>();
    private Produto mercadoria;
    private int contadorInterno = 0;
    private List<CategoriaProduto> cat = new ArrayList<>();
    private List<Fornecedor> fornecedores = new ArrayList<>();

    @Inject
    private FornecedorService fornecedorService;

    @Inject
    private FornecedorRepository fornecedorRepository;
    
    @Inject
    private ProdutoService produtoService;

    @PostConstruct
    public void init() {
        mercadoria = new Produto();
    }

    public int getContador() {
        return ++contadorInterno;
    }

    public List<CategoriaProduto> categorias() {
        cat.addAll(Arrays.asList(CategoriaProduto.values()));
        return cat;
    }

    public List<Fornecedor> fornecedores() {
        fornecedores.clear();
        fornecedores.addAll(fornecedorRepository.findAll(Fornecedor.class));
        return fornecedores;
    }
    
    public Produto salvar() {
        return produtoService.save(mercadoria);
    }
}
