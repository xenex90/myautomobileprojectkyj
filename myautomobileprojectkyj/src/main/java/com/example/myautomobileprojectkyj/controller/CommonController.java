package com.example.myautomobileprojectkyj.controller;


import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class CommonController {

    @RequestMapping("/")
    public ModelAndView mainController(ModelAndView ModelAndView,  HttpServletRequest request){
        HashMap<String,String> map = new HashMap<>();
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String id =(String) session.getAttribute("loginSession");
        System.out.println("id ====" + id);

        if(id != null){
            System.out.println("로그인이 되어 있습니다");
            mv.addObject("loginResult","3");
            mv.addObject("id",id);
        }else{
            System.out.println("로그인이 되어 있지 않습니다.");
            mv.addObject("loginResult","4");
            mv.addObject("id",id);
        }


        System.out.println("CommonController 실행됨" );

        mv.setViewName("index");

        return mv;
    }

    @RequestMapping("index.html")
    public ModelAndView mainController2(ModelAndView ModelAndView,  HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        System.out.println("CommonController2 실행됨" );

        mv.setViewName("redirect:/");

        return mv;
    }

}
