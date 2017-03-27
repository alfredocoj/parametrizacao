package com.uema.admin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Created by alfredo on 27/03/17.
 */
@Entity(name="CategoriaRecurso")
@Table(name="seg_categorias_recursos")
public class CategoriaRecurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ctr_id")
    private Integer ctrId;

    @Column(name = "ctr_nome", nullable = false, length = 150)
    private String ctrNome;

    @Column(name = "ctr_descricao", nullable = false, length = 150)
    private String ctrDescricao;

    @Column(name = "ctr_icone", nullable = false, length = 45)
    private String ctrIcone;

    @Column(name = "ctr_ordem", nullable = false, columnDefinition = "INT(4) default 0")
    private Integer ctr_ordem;

    @Column(name = "ctr_visivel", nullable = false, columnDefinition = "boolean default true")
    private String ctrVisivel;

    public Integer getCtrId() {
        return ctrId;
    }

    public void setCtrId(Integer ctrId) {
        this.ctrId = ctrId;
    }

    public String getCtrNome() {
        return ctrNome;
    }

    public void setCtrNome(String ctrNome) {
        this.ctrNome = ctrNome;
    }

    public String getCtrDescricao() {
        return ctrDescricao;
    }

    public void setCtrDescricao(String ctrDescricao) {
        this.ctrDescricao = ctrDescricao;
    }

    public String getCtrIcone() {
        return ctrIcone;
    }

    public void setCtrIcone(String ctrIcone) {
        this.ctrIcone = ctrIcone;
    }

    public Integer getCtr_ordem() {
        return ctr_ordem;
    }

    public void setCtr_ordem(Integer ctr_ordem) {
        this.ctr_ordem = ctr_ordem;
    }

    public String getCtrVisivel() {
        return ctrVisivel;
    }

    public void setCtrVisivel(String ctrVisivel) {
        this.ctrVisivel = ctrVisivel;
    }

    @Override
    public String toString() {
        return "CategoriaRecurso{" +
                "ctrId=" + ctrId +
                ", ctrNome='" + ctrNome + '\'' +
                ", ctrDescricao='" + ctrDescricao + '\'' +
                ", ctrIcone='" + ctrIcone + '\'' +
                ", ctr_ordem=" + ctr_ordem +
                ", ctrVisivel='" + ctrVisivel + '\'' +
                '}';
    }
}
