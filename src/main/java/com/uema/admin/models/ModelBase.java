package com.uema.admin.models;

import com.uema.admin.entities.EntityBase;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alfredo on 27/03/17.
 */
@Repository
@Transactional
public class ModelBase<EntidadeBase> {

    @PersistenceContext
    protected EntityManager manager;

    protected String schema;

    protected EntityBase entidade;

    public ModelBase() {
        this.schema = "schema_default";
    }

    public ModelBase(EntityManager entityManager) {
        this.schema = "schema_default";
        manager = entityManager;
    }

    public void configEntityManager(String schema){
        this.schema = schema;
        manager = Persistence.createEntityManagerFactory(this.schema).createEntityManager();
    }

    public EntityBase save(EntityBase entidade) throws Exception{
        try{
            manager.persist(entidade);
            manager.flush();
            return entidade;
        } catch (PersistenceException e){
            throw new Exception( "Erro ao tentar salvar." );
        }
    }

    public EntityBase update(EntityBase entidade) throws Exception{
        try{
            EntityBase objeto = getById(entidade);
            System.out.println("UPDATE: "+manager.merge(entidade));
            return objeto;
        } catch (PersistenceException e){
            throw new Exception( "Erro ao tentar atualizar." );
        }
    }

    public boolean remove(EntityBase entidade) throws Exception{
        try{
            EntityBase objeto = getById(entidade);
            if(objeto.getId() != null){
                manager.remove(objeto);
                return true;
            } else
                return false;
        } catch (PersistenceException e){
            throw new Exception( "Erro ao tentar remover." );
        }
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getAll(EntityBase entidade, String sql) {
        return (List<EntityBase>) manager.createQuery(sql, entidade.getClass()).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getAll(EntityBase entidade) {
        return (List<EntityBase>)  this.manager.createQuery("SELECT e FROM "+entidade.getClass().getSimpleName()+" e", entidade.getClass()).getResultList();
    }

    public EntityBase getById(EntityBase entidade){
        return manager.find(entidade.getClass(), entidade.getId());
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getByAttribute(EntityBase entidade, String attribute, String value) {
        Query query = manager.createQuery("SELECT e FROM "+entidade.getClass().getSimpleName()+" e where e." + attribute + " = :valor");
        query.setParameter("valor", value);

        List<EntityBase> lista = query.getResultList();

        return lista;
    }

}

