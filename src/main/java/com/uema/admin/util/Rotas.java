package com.uema.admin.util;

import java.util.List;

/**
 * Created by alfredo on 27/03/17.
 */
public class Rotas {

    public String modulo;

    public String recurso;

    public List<String> permissao;

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public List<String> getPermissao() {
        return permissao;
    }

    public void setPermissao(List<String> permissao) {
        this.permissao = permissao;
    }
}
