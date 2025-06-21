/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.service;

import com.supermercado.entity.Cliente;
import com.supermercado.repository.GenericRepository;
import com.supermercado.repository.JpaUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Felipe
 */
@ApplicationScoped
public class ClienteService extends GenericService<Cliente, Long> {

    @Inject
    private GenericRepository<Cliente, Long> repository;

    // construtor padrão sem parâmetros, chama super com repository injetado
    public ClienteService() {
        super(null); // por enquanto, passamos null no construtor pai, mas vamos sobrescrever a referência abaixo
    }

    @PostConstruct
    public void init() {
        super.repository = repository; // inicializa o repository no pai após injeção
    }

    public List<Cliente> findAll() {
        return super.findAll(Cliente.class);
    }

    public boolean findClienteByCpf(String cpf) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Cliente> root = query.from(Cliente.class);

        query.select(cb.count(root))
                .where(cb.equal(root.get("cpf"), cpf.trim()));

        Long count = em.createQuery(query).getSingleResult();
        return count != null && count > 0;
    }

    public Long findIdByCpf(String cpf) {
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Cliente> root = query.from(Cliente.class);

        query.select(root.get("id"))
                .where(cb.equal(root.get("cpf"), cpf.trim()));

        try {
            return em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null; // ou lançar uma exceção customizada, se preferir
        }
    }
}
