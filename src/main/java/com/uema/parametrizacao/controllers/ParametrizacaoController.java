package com.uema.parametrizacao.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequestMapping(value = "/parametrizacao")
public class ParametrizacaoController {

    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("parametrizacao/index");
    }
}
