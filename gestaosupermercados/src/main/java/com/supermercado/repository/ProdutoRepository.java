/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.supermercado.repository;

import com.supermercado.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Optional;

/**
 *
 * @author Felipe
 */
public class ProdutoRepository extends GenericRepository<Produto, Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Optional<Produto> findByCodigoBarras(String codigoBarras) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);
            Root<Produto> root = cq.from(Produto.class);

            cq.select(root).where(cb.equal(root.get("codigoBarras"), codigoBarras));

            Produto produto = entityManager.createQuery(cq).getSingleResult();

            return Optional.of(produto);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
