package com.example.myautomobileprojectkyj.controller;

import com.example.myautomobileprojectkyj.service.MemberService;
import com.example.myautomobileprojectkyj.vo.MemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;


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
    public ModelAndView joinController(ModelAndView ModelAndView, HttpServletRequest request, @RequestParam HashMap<String, String> p, Locale locale){
        System.out.println("loginController 실행됨 : join" );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/joinpage");


        String inputYn = request.getParameter("inputYn");
        String zipNo = request.getParameter("zipNo");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");
        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String addrDetail = request.getParameter("addrDetail");
        String jibunAddr = request.getParameter("jibunAddr");

        mv.addObject("inputYn", inputYn);
        mv.addObject("zipNo", zipNo);
        mv.addObject("roadAddrPart1", roadAddrPart1);
        mv.addObject("roadAddrPart2", roadAddrPart2);
        mv.addObject("jibunAddr", jibunAddr);
        mv.addObject("addrDetail", addrDetail);

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

    @RequestMapping(value = "/idcheckprocess.do" , method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> idCheckProcessController(@RequestParam("id") String id) throws Exception {
        HashMap<String, Object> map = new HashMap();

        System.out.println("AJAX 기동!!");
        MemberVo memberVo = new MemberVo();

        memberVo.setId(id);
        memberVo.setPassword(null);
        int countOfMember = memberService.isMember(memberVo);
        if(countOfMember > 0){
            map.put("returnkey","1");
        }else{
            map.put("returnkey","0");
        }
        return map;
    }

    @RequestMapping(value = "/test.action", method = { RequestMethod.POST })
    public void test(@RequestParam("name") String name,
                     @RequestParam("age") String age,
                     @RequestParam("gender") String gender) {

        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);
    }

    @RequestMapping(value = "/jusoPopup.do")
    public ModelAndView jusoPopup(ModelAndView ModelAndView, HttpServletRequest request, @RequestParam HashMap<String, String> p, Locale locale) {

        System.out.println("jusoPopup 컨트롤러 실행!!!");

        // callback 함수가 실행되어야하니 호출한 html 파일로 return
        ModelAndView mav = new ModelAndView("login/jusopopup");

        String inputYn = request.getParameter("inputYn");
        String zipNo = request.getParameter("zipNo");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");
        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String addrDetail = request.getParameter("addrDetail");
        String jibunAddr = request.getParameter("jibunAddr");

        mav.addObject("inputYn", inputYn);
        mav.addObject("zipNo", zipNo);
        mav.addObject("roadAddrPart1", roadAddrPart1);
        mav.addObject("roadAddrPart2", roadAddrPart2);
        mav.addObject("jibunAddr", jibunAddr);
        mav.addObject("addrDetail", addrDetail);

        return mav;
    }


}
