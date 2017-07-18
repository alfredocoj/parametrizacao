package com.uema.admin.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredo on 26/03/17.
 */
@Entity(name="Usuario")
@Table(name="seg_usuarios")
public class Usuario extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    private Integer usrId;

    @Column(name = "usr_nome", nullable = false, length = 150)
    private String usrNome;

    @Column(name = "usr_email", nullable = false, length = 50)
    private String usrEmail;

    @Column(name = "usr_telefone", nullable = false, length = 17)
    private String usrTelefone;

    @Column(name = "usr_usuario", nullable = false, length = 45)
    private String usrUsuario;

    @Column(name = "usr_senha", nullable = false, length = 256)
    private String usrSenha;

    @Column(name = "usr_ativo", nullable = false, columnDefinition = "boolean default true")
    private String usrAtivo;

    @ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name="seg_perfis_usuarios", joinColumns={@JoinColumn(name="pru_usr_id", referencedColumnName = "usr_id")}, inverseJoinColumns={@JoinColumn(name="pru_prf_id", referencedColumnName = "prf_id")})
    private List<Perfil> perfil = new ArrayList<>();

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrNome() {
        return usrNome;
    }

    public void setUsrNome(String usrNome) {
        this.usrNome = usrNome;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrTelefone() {
        return usrTelefone;
    }

    public void setUsrTelefone(String usrTelefone) {
        this.usrTelefone = usrTelefone;
    }

    public String getUsrUsuario() {
        return usrUsuario;
    }

    public void setUsrUsuario(String usrUsuario) {
        this.usrUsuario = usrUsuario;
    }

    public String getUsrSenha() {
        return usrSenha;
    }

    public void setUsrSenha(String usrSenha) {
        this.usrSenha = usrSenha;
    }

    public String getUsrAtivo() {
        return usrAtivo;
    }

    public void setUsrAtivo(String usrAtivo) {
        this.usrAtivo = usrAtivo;
    }

    @JsonBackReference
    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Perfil> perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usrId=" + usrId +
                ", usrNome='" + usrNome + '\'' +
                ", usrEmail='" + usrEmail + '\'' +
                ", usrTelefone='" + usrTelefone + '\'' +
                ", usrUsuario='" + usrUsuario + '\'' +
                ", usrSenha='" + usrSenha + '\'' +
                ", usrAtivo='" + usrAtivo + '\'' +
                '}';
    }
}
