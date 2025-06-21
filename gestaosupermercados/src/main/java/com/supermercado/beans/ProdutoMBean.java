/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.beans;

import com.supermercado.entity.CategoriaProduto;
import com.supermercado.entity.Cliente;
import com.supermercado.entity.Fornecedor;
import com.supermercado.entity.Produto;
import com.supermercado.repository.FornecedorRepository;
import com.supermercado.service.FornecedorService;
import com.supermercado.service.ProdutoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    private Fornecedor fornecedor = new Fornecedor();
    private int contadorInterno = 0;
    private List<CategoriaProduto> cat = new ArrayList<>();
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private Long fornecedorId;

    @Inject
    private FornecedorService fornecedorService;

    @Inject
    private FornecedorRepository fornecedorRepository;

    @Inject
    private ProdutoService produtoService;

    @PostConstruct
    public void init() {
        mercadorias = produtoService.findAll();
        fornecedores.addAll(fornecedorService.findAll());
        cat.addAll(Arrays.asList(CategoriaProduto.values()));
        mercadoria = new Produto();
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public void redirecionar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("venda.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getContador() {
        return ++contadorInterno;
    }

    public Produto salvar() {
        if (fornecedorId != null) {
            mercadoria.setFornecedor(fornecedorRepository.findById(Fornecedor.class, fornecedorId).orElse(null));
        }
        Optional<Produto> produto = produtoService.findByCodigoBarras(mercadoria.getCodigoBarras());
        if (mercadoria.getId() == null && produto.isEmpty()) {
            produtoService.save(mercadoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto salvo com sucesso"));
        } else {
            // Produto existente
            Produto produtoAlterado = houveAlteracao(mercadoria, produto.get());
            produtoService.update(produtoAlterado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente atualizado com sucesso"));
        }

        mercadorias = produtoService.findAll();

        return mercadoria;
    }

    public void prepararNovo() {
        mercadoria = new Produto();
        fornecedores = fornecedorService.findAll(); // atualiza
    }

    public void prepararEdicao(Produto produto) {
        this.mercadoria = produto;

        fornecedores = fornecedorService.findAll(); // atualiza
    }

    public void excluir(Produto produto) {
        produtoService.deleteById(Produto.class, produto.getId());
        mercadorias = produtoService.findAll();
    }
    
    private Produto houveAlteracao(Produto orElse, Produto produtoBanco) {
        Produto produtoAlterado = new Produto();

        produtoAlterado.setId(produtoBanco.getId());
        produtoAlterado.setCat(orElse.getCat());
        produtoAlterado.setCodigoBarras(produtoBanco.getCodigoBarras());
        produtoAlterado.setCategoria(orElse.getCategoria());
        produtoAlterado.setDescricao(orElse.getDescricao());
        produtoAlterado.setFornecedor(orElse.getFornecedor());
        produtoAlterado.setNome(orElse.getNome());
        produtoAlterado.setPreco(orElse.getPreco());
        produtoAlterado.setQuantidadeEmEstoque(orElse.getQuantidadeEmEstoque());
        produtoAlterado.setUploadImagem(orElse.getUploadImagem());

        return produtoAlterado;
    }

}
