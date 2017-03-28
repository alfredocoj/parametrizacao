package com.uema.admin.models;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alfredo on 27/03/17.
 */
@Repository
@Transactional
public class PermissaoModel  extends ModelBase<Object> {
    @PersistenceContext
    private EntityManager manager;

    public List<Object[]> getListPermissaoPerfil(){
        Query q = manager.createNativeQuery(
                "           SELECT " +
                "               m.mod_nome " +
                "               r.rcr_nome, " +
                "               p.prm_nome " +
                "               pe.prf_nome , " +
                "           FROM seg_permissoes p\n" +
                "                INNER JOIN seg_perfis_permissoes pp ON prp_prm_id = prm_id\n" +
                "                INNER JOIN seg_recursos r ON rcs_id = prm_rcs_id\n" +
                "                INNER JOIN seg_modulos m ON mod_id = rcs_mod_id" +
                "                INNER JOIN seg_perfis ppe ON pe.id_perfil = pp.prp_prf_id");

        List<Object[]> objetos = q.getResultList();

       return objetos;
    }
}
