package com.uema.admin.entities;

import javax.persistence.*;

/**
 * Created by alfredo on 27/03/17.
 */
@Entity(name="Perfil")
@Table(name="seg_perfis")
public class Perfil extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prf_id")
    private Integer prfId;

    @ManyToOne(targetEntity=Modulo.class, fetch=FetchType.LAZY)
    @JoinColumn(name="prf_mod_id", nullable=false)
    private Modulo modulo;

    @Column(name = "prf_nome", nullable = false, length = 150)
    private String prfNome;

    @Column(name = "prf_descricao", nullable = false, length = 150)
    private String prfDescricao;

    public Integer getPrfId() {
        return prfId;
    }

    public void setPrfId(Integer prfId) {
        this.prfId = prfId;
    }

    public String getPrfNome() {
        return prfNome;
    }

    public void setPrfNome(String prfNome) {
        this.prfNome = prfNome;
    }

    public String getPrfDescricao() {
        return prfDescricao;
    }

    public void setPrfDescricao(String prfDescricao) {
        this.prfDescricao = prfDescricao;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "prfId=" + prfId +
                ", prfNome='" + prfNome + '\'' +
                ", prfDescricao='" + prfDescricao + '\'' +
                ", moduloId=" + modulo.getModId() +
                ", moduloNome=" + modulo.getModNome() +
                '}';
    }
}
