package com.uema.parametrizacao.daos;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ParametrizacaoDao {

    @PersistenceContext
    private EntityManager manager;
}
