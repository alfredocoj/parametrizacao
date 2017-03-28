package com.uema.admin.models;

import com.uema.admin.entities.EntityBase;
import com.uema.admin.entities.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by alfredo on 27/03/17.
 */
@Repository
@Transactional
public class UsuarioModel extends ModelBase<Object> {

    @PersistenceContext
    private EntityManager manager;

    public boolean remove(EntityBase entidade){
        String sql = "UPDATE seg_usuarios SET ativo = false WHERE usr_id = :id";
        int flag = manager.createQuery(sql).setParameter("id", entidade.getId()).executeUpdate();

        if(flag!=0)
            return true;

        return false;
    }

    public String vincularPerfil(int idUsuario, int idPerfil){
        String sql = "insert into seg_perfis_usuarios(pru_usr_id, pru_prf_id) values ("+idUsuario+","+idPerfil+")";
        manager.createNativeQuery(sql).executeUpdate();
        return "";
    }
}
