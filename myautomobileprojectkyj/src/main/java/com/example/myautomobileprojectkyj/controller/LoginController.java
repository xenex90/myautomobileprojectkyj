package com.example.myautomobileprojectkyj.controller;

import com.example.myautomobileprojectkyj.service.MemberService;
import com.example.myautomobileprojectkyj.vo.MemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/loginpage")
    public ModelAndView loginController(ModelAndView ModelAndView){
    System.out.println("loginController 실행됨 : loginpage" );
    ModelAndView mv = new ModelAndView();
    mv.addObject("loginResult","3");
    mv.setViewName("login/loginpage");

    return mv;
    }

    @RequestMapping("/join")
    public ModelAndView joinController(ModelAndView ModelAndView){
        System.out.println("loginController 실행됨 : join" );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/joinpage");

        return mv;
    }

    @RequestMapping("/joinprocess")
    public ModelAndView joinController( ModelAndView ModelAndView, MemberVo memberVo) throws Exception {
        System.out.println("loginController 실행됨 : joinprocess" );
        ModelAndView mv = new ModelAndView();

        memberService.joinMember(memberVo);

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/loginprocess")
    public ModelAndView loginProcessController(ModelAndView ModelAndView, HttpServletRequest request, String id, String password) throws Exception {
        System.out.println("loginController 실행됨 : loginProcess" );
        ModelAndView mv = new ModelAndView();
        MemberVo memberVo = new MemberVo();

        int countOfMember = 0;

        if(id != null && password != null){
            memberVo.setId(id);
            memberVo.setPassword(password);
            countOfMember = memberService.isMember(memberVo);
            if( countOfMember > 0) {
                memberVo = memberService.loginMember(id, password);
            }
        }

        if(countOfMember > 0 ){
            mv.addObject("loginResult","1");
            mv.setViewName("index");
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보 보관
            session.setAttribute("loginSession",memberVo.getId() );
            System.out.println("로그인 성공");
        }else{
            mv.addObject("loginResult","2");
            mv.setViewName("login/loginpage");
            System.out.println("로그인 실패");
        }

        return mv;
    }

    @RequestMapping("/logoutprocess")
    public ModelAndView logoutController(ModelAndView ModelAndView, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();
        if (session != null) {
            session.invalidate();
            mv.addObject("logout","1");
        }
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping("/withdrawalprocess")
    public ModelAndView WithdrawalController(ModelAndView ModelAndView, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();
        if (session != null) {
            session.invalidate();
            mv.addObject("logout","1");
        }
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/idcheck")
    public ModelAndView IdCheckController(ModelAndView ModelAndView, HttpServletRequest request) throws Exception {


        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/idcheckpage");

        return mv;
    }

    @RequestMapping("/idcheckprocess")
    public ModelAndView IdCheckProcessController(ModelAndView ModelAndView, HttpServletRequest request) throws Exception {


        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/idcheckpage");

        return mv;
    }

}
