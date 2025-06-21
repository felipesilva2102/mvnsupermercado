/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.beans;

import com.supermercado.entity.Cliente;
import com.supermercado.service.ClienteService;
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
//@Getter
//@Setter
public class ClientesMBean implements Serializable {

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Cliente getClienteParaExcluir() {
        return clienteParaExcluir;
    }

    public void setClienteParaExcluir(Cliente clienteParaExcluir) {
        this.clienteParaExcluir = clienteParaExcluir;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    private List<Cliente> clientes = new ArrayList<>();
    private Cliente clienteSelecionado = new Cliente();

    private Cliente clienteParaExcluir = new Cliente();
    private Cliente cliente = new Cliente();

    @Inject
    private ClienteService clienteService;

    @PostConstruct
    public void init() {
        clienteSelecionado = new Cliente();
        clientes = clienteService.findAll();
    }

    public void redirecionar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepararNovo() {
        clienteSelecionado = new Cliente();
    }

    public void prepararEdicao(Cliente cliente) {
        clienteSelecionado = cliente;
    }

    public void salvar() {
        if (clienteSelecionado.getId() == null && !clienteService.findClienteByCpf(clienteSelecionado.getCpf())) {
            // Novo cliente
            if (!isCpfValido(clienteSelecionado.getCpf())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido", null));
                FacesContext.getCurrentInstance().validationFailed();
                return;
            }
            clienteService.save(clienteSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente salvo com sucesso"));
        } else {
            // Cliente existente
            Cliente clienteAlterado = houveAlteracao(clienteService.findById(Cliente.class, clienteService.findIdByCpf(clienteSelecionado.getCpf())).orElse(null), clienteSelecionado);
            clienteService.update(clienteAlterado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente atualizado com sucesso"));
        }

        // Atualiza a lista para refletir as mudanças
        clientes = clienteService.findAll();

        // Opcional: limpar o clienteSelecionado para novo cadastro
        clienteSelecionado = new Cliente();
    }

    public void excluir(Cliente cliente) {
        clienteService.deleteById(Cliente.class, cliente.getId());

        // Atualiza a lista para refletir as mudanças
        clientes = clienteService.findAll();

        // Opcional: limpar o clienteSelecionado para novo cadastro
        clienteSelecionado = new Cliente();
    }

    public void prepararExcluir(Cliente cliente) {
        clienteParaExcluir = clienteService.findById(Cliente.class, clienteService.findIdByCpf(cliente.getCpf())).orElse(null);
    }

    public void confirmarExcluir() {
        if (clienteParaExcluir != null) {
            excluir(clienteParaExcluir);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente excluído"));
        }
    }

    private boolean isCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int dig1 = 11 - (soma % 11);
            if (dig1 > 9) {
                dig1 = 0;
            }

            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * peso--;
            }
            int dig2 = 11 - (soma % 11);
            if (dig2 > 9) {
                dig2 = 0;
            }

            return cpf.charAt(9) - '0' == dig1 && cpf.charAt(10) - '0' == dig2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Cliente houveAlteracao(Cliente orElse, Cliente clienteSelecionado) {
        Cliente clienteAlterado = new Cliente();

        clienteAlterado.setId(orElse.getId());
        clienteAlterado.setNome(clienteSelecionado.getNome());
        clienteAlterado.setCpf(orElse.getCpf());
        clienteAlterado.setTelefone(clienteSelecionado.getTelefone());
        clienteAlterado.setEmail(clienteSelecionado.getEmail());
        clienteAlterado.setEndereco(clienteSelecionado.getEndereco());

        return clienteAlterado;
    }

}
