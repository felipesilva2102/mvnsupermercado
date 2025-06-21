/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Felipe
 */
@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String codigoBarras;

    @Column(length = 500)
    private String descricao;

    private BigDecimal preco;

    private Integer quantidadeEmEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private CategoriaProduto categoria;

    @ManyToOne
    private Fornecedor fornecedor;
    
    private List<CategoriaProduto> cat;

    @Lob
    @Column(length = 10485760) // até 10MB
    private String uploadImagem;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.codigoBarras);
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.preco);
        hash = 53 * hash + Objects.hashCode(this.quantidadeEmEstoque);
        hash = 53 * hash + Objects.hashCode(this.categoria);
        hash = 53 * hash + Objects.hashCode(this.fornecedor);
        hash = 53 * hash + Objects.hashCode(this.cat);
        hash = 53 * hash + Objects.hashCode(this.uploadImagem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.codigoBarras, other.codigoBarras)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.uploadImagem, other.uploadImagem)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        if (!Objects.equals(this.quantidadeEmEstoque, other.quantidadeEmEstoque)) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        return Objects.equals(this.cat, other.cat);
    }

    
    
}
