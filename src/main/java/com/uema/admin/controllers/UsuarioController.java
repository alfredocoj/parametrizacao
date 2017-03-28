package com.uema.admin.controllers;

import com.uema.admin.entities.EntityBase;
import com.uema.admin.entities.Usuario;
import com.uema.admin.models.UsuarioModel;
import com.uema.admin.util.ResponseSig;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by alfredo on 27/03/17.
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioModel usuarioModel;

    @RequestMapping("/admin/usuario/index")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView index(){
        System.out.println("index - usuario");
        ModelAndView mv = new ModelAndView("usuario/index");
        //mv.addObject("rotas", Rotas.getRoutes());
        return mv;
    }

    @RequestMapping(value= "/admin/usuario/listar", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object listar(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        return ResponseSig.success((Collection<? extends EntityBase>) usuarioModel.getAll(new Usuario()),200);
    }
}
