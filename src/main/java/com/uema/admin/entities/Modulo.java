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
@Entity(name="Modulo")
@Table(name="seg_modulos")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mod_id")
    private Integer modId;

    @Column(name = "mod_nome", nullable = false, length = 150)
    private String modNome;

    @Column(name = "mod_descricao", nullable = false, length = 150)
    private String modDescricao;

    @Column(name = "mod_icone", nullable = false, length = 45)
    private String modIcone;

    @Column(name = "mod_ativo", nullable = false, columnDefinition = "boolean default true")
    private String modAtivo;

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public String getModNome() {
        return modNome;
    }

    public void setModNome(String modNome) {
        this.modNome = modNome;
    }

    public String getModDescricao() {
        return modDescricao;
    }

    public void setModDescricao(String modDescricao) {
        this.modDescricao = modDescricao;
    }

    public String getModIcone() {
        return modIcone;
    }

    public void setModIcone(String modIcone) {
        this.modIcone = modIcone;
    }

    public String getModAtivo() {
        return modAtivo;
    }

    public void setModAtivo(String modAtivo) {
        this.modAtivo = modAtivo;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "modId=" + modId +
                ", modNome='" + modNome + '\'' +
                ", modDescricao='" + modDescricao + '\'' +
                ", modIcone='" + modIcone + '\'' +
                ", modAtivo='" + modAtivo + '\'' +
                '}';
    }
}
