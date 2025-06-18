/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.supermercado.entity;

/**
 *
 * @author Felipe
 */
public enum CategoriaProduto {
    ALIMENTACAO("Alimentação"),
    BEBIDAS("Bebidas"),
    HIGIENE_E_PERFUMARIA("Higiene e Perfumaria"),
    LIMPEZA("Limpeza"),
    FRIOS_E_LATICINIOS("Frios e Laticínios"),
    PADARIA("Padaria"),
    CONGELADOS("Congelados"),
    ELETRONICOS("Eletrônicos"),
    UTENSILIOS_DOMESTICOS("Utensílios Domésticos"),
    ROUPAS_E_ACESSORIOS("Roupas e Acessórios"),
    PAPELARIA("Papelaria"),
    PETSHOP("Pet Shop"),
    SAUDE_E_BEM_ESTAR("Saúde e Bem-estar"),
    OUTROS("Outros");

    private final String descricao;

    // Construtor da enum (sempre privado ou package-private)
    CategoriaProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    static class values {

        public values() {
        }
    }
}
