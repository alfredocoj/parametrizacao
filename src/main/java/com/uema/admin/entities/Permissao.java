package com.uema.admin.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredo on 27/03/17.
 */
@Entity(name="Permissao")
@Table(name="seg_permissoes")
public class Permissao extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prm_id")
    private Integer prmId;

    @ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name="seg_perfis_permissoes", joinColumns={@JoinColumn(name="prp_prm_id", referencedColumnName = "prm_id")}, inverseJoinColumns={@JoinColumn(name="prp_prf_id", referencedColumnName = "prf_id")})
    private List<Perfil> perfil = new ArrayList<>();

    @ManyToOne(targetEntity=Recurso.class, fetch=FetchType.LAZY)
    @JoinColumn(name="prm_rcs_id", nullable=false)
    private Recurso recurso;

    @Column(name = "prm_nome", nullable = false, length = 150)
    private String prmNome;

    @Column(name = "prm_descricao", nullable = false, length = 150)
    private String prmDescricao;

    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Perfil> perfil) {
        this.perfil = perfil;
    }

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
                ", recursoId=" + recurso.getRcsId() +
                ", recursoNome=" + recurso.getRcsNome() +
                ", prmNome='" + prmNome + '\'' +
                ", prmDescricao='" + prmDescricao + '\'' +
                '}';
    }
}
