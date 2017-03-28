package com.uema.admin.models;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by alfredo on 27/03/17.
 */
@Repository
@Transactional
public class ModuloModel extends ModelBase<Object> {
    @PersistenceContext
    private EntityManager manager;
}
