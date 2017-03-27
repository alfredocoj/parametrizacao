package com.uema.admin.entities;

import javax.persistence.*;

/**
 * Created by alfredo on 27/03/17.
 */
@Entity(name="Recurso")
@Table(name="seg_recursos")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rcs_id")
    private Integer rcs_id;

    @ManyToOne(targetEntity=CategoriaRecurso.class, fetch=FetchType.LAZY)
    @JoinColumn(name="rcs_ctr_id", nullable=false)
    private CategoriaRecurso categoriaRecurso;

    @ManyToOne(targetEntity=Modulo.class, fetch=FetchType.LAZY)
    @JoinColumn(name="rcs_mod_id", nullable=false)
    private Modulo modulo;

    @Column(name = "rcs_nome", nullable = false, length = 150)
    private String rcsNome;

    @Column(name = "rcs_descricao", nullable = false, length = 150)
    private String rcsDescricao;

    @Column(name = "rcs_icone", nullable = false, length = 45)
    private String rcsIcone;

    public Integer getRcs_id() {
        return rcs_id;
    }

    public void setRcs_id(Integer rcs_id) {
        this.rcs_id = rcs_id;
    }

    public CategoriaRecurso getCategoriaRecurso() {
        return categoriaRecurso;
    }

    public void setCategoriaRecurso(CategoriaRecurso categoriaRecurso) {
        this.categoriaRecurso = categoriaRecurso;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getRcsNome() {
        return rcsNome;
    }

    public void setRcsNome(String rcsNome) {
        this.rcsNome = rcsNome;
    }

    public String getRcsDescricao() {
        return rcsDescricao;
    }

    public void setRcsDescricao(String rcsDescricao) {
        this.rcsDescricao = rcsDescricao;
    }

    public String getRcsIcone() {
        return rcsIcone;
    }

    public void setRcsIcone(String rcsIcone) {
        this.rcsIcone = rcsIcone;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "rcs_id=" + rcs_id +
                ", categoriaRecursoId=" + categoriaRecurso.getCtrId() +
                ", categoriaRecursoNome=" + categoriaRecurso.getCtrNome() +
                ", moduloId=" + modulo.getModId() +
                ", moduloNome=" + modulo.getModNome() +
                ", rcsNome='" + rcsNome + '\'' +
                ", rcsDescricao='" + rcsDescricao + '\'' +
                ", rcsIcone='" + rcsIcone + '\'' +
                '}';
    }
}
