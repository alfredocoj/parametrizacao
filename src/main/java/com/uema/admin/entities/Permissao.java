package com.uema.admin.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredo on 27/03/17.
 */
@Entity(name="Permissao")
@Table(name="seg_permissoes")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prm_id")
    private Integer prmId;

    @ManyToOne(targetEntity=Recurso.class, fetch=FetchType.LAZY)
    @JoinColumn(name="prm_rcs_id", nullable=false)
    private Recurso recurso;

    @Column(name = "prm_nome", nullable = false, length = 150)
    private String prmNome;

    @Column(name = "prm_descricao", nullable = false, length = 150)
    private String prmDescricao;

    public Integer getPrmId() {
        return prmId;
    }

    public void setPrmId(Integer prmId) {
        this.prmId = prmId;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public String getPrmNome() {
        return prmNome;
    }

    public void setPrmNome(String prmNome) {
        this.prmNome = prmNome;
    }

    public String getPrmDescricao() {
        return prmDescricao;
    }

    public void setPrmDescricao(String prmDescricao) {
        this.prmDescricao = prmDescricao;
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "prmId=" + prmId +
                ", recurso=" + recurso +
                ", prmNome='" + prmNome + '\'' +
                ", prmDescricao='" + prmDescricao + '\'' +
                '}';
    }
}
