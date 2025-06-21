/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.beans;

import com.supermercado.entity.Fornecedor;
import com.supermercado.service.FornecedorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
public class FornecedoresMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores = new ArrayList<>();

    @Inject
    private FornecedorService fornecedorService;

    @PostConstruct
    public void init() {
        fornecedores = fornecedorService.findAll();
    }
    
    public void redirecionar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("fornecedores.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepararNovo() {
        fornecedor = new Fornecedor();
    }

    public void prepararEdicao(Fornecedor f) {
        this.fornecedor = f;
    }

    public void salvar() {
        if (fornecedor.getId() == null) {
            fornecedorService.save(fornecedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor cadastrado com sucesso"));
        } else {
            fornecedorService.update(fornecedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor atualizado com sucesso"));
        }
        fornecedores = fornecedorService.findAll();
    }

    public void excluir(Fornecedor f) {
        fornecedorService.deleteById(Fornecedor.class, f.getId());
        fornecedores = fornecedorService.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor excluído com sucesso"));
    }
}
