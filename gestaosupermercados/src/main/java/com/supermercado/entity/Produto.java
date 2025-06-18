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

    //http://www.eanpictures.com.br:9000/api/desc/?
    
    
}
