/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Funcionario;
import com.supermercado.repository.GenericRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class FuncionarioService extends GenericService<Funcionario, Long> {
    
    public FuncionarioService(GenericRepository<Funcionario, Long> repository) {
        super(repository);
    }

    public List<Funcionario> findAll() {
        return super.findAll(Funcionario.class); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
