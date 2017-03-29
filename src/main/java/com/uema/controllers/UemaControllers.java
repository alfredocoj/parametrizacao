package com.uema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
public class UemaControllers {

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("title", "Sample Java");
        return model;
    }
}
