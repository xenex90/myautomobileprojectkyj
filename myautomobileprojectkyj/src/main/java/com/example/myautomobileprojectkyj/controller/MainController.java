package com.example.myautomobileprojectkyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/main")
    public ModelAndView mainController(ModelAndView ModelAndView){
        System.out.println("MainController 실행됨" );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        return mv;
    }

}
