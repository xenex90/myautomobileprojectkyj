package com.example.myautomobileprojectkyj.controller;

import com.example.myautomobileprojectkyj.service.MemberService;
import com.example.myautomobileprojectkyj.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final MemberService memberService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/loginpage")
    public ModelAndView loginController(ModelAndView ModelAndView, HttpServletRequest request){
    System.out.println("loginController 실행됨 : loginpage" );
    ModelAndView mv = new ModelAndView();

    HttpSession session = request.getSession(false);

                          session.getId();

    System.out.println("loginpage controller 실행");
    if(session != null){
        String id = (String) session.getAttribute("id");
        mv.addObject("id", id);
        mv.addObject("loginParam", "Y");
        System.out.println("로그인 세션 발견");
    }

    mv.setViewName("login/loginpage");

    return mv;
    }

    @RequestMapping("/join")
    public ModelAndView joinController(ModelAndView ModelAndView, HttpServletRequest request, @RequestParam HashMap<String, String> p, Locale locale){
        System.out.println("loginController 실행됨 : join" );
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/joinpage");

        return mv;
    }

    @RequestMapping("/joinprocess")
    public ModelAndView joinController( ModelAndView ModelAndView, MemberVo memberVo) throws Exception {

        ModelAndView mv = new ModelAndView();

        memberService.joinMember(memberVo);

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/loginprocess")
    public ModelAndView loginProcessController(ModelAndView ModelAndView,HttpServletRequest request,
                                               HttpServletResponse response, String id, String password) throws Exception {

        ModelAndView mv = new ModelAndView();
        MemberVo memberVo = new MemberVo();

        int countOfMember = 0;
        String currentURL = request.getRequestURI();
        if((id == null || id == "") && currentURL.contains("loginprocess")){
            mv.setViewName("redirect:/");
            log.info("새로고침 이슈로 index로 돌아갑니다.");
            return mv;
        }
        System.out.println("loginprocess 싫행");

        if(id != null && password != null){
            memberVo.setId(id);
            memberVo.setPassword(password);
            countOfMember = memberService.isMember(memberVo);
            if( countOfMember > 0) {
                memberVo = memberService.loginMember(id, password);
                if(memberVo == null){
                    mv.addObject("loginResult","2");
                    mv.setViewName("login/loginpage");
                    System.out.println("해당 비번에 맞는 아이디가 없어 로그인 실패");
                    return mv;
                }
            }

        }



        if(countOfMember > 0 ){

            HttpSession session = request.getSession();

            session.setMaxInactiveInterval(1800);

            session.setAttribute("id", memberVo.getId());
            session.setAttribute("name", memberVo.getName());
            session.setAttribute("email", memberVo.getEmail());
            session.setAttribute("address", memberVo.getAddress());
            session.setAttribute("address2", memberVo.getAddress2());

            mv.addObject("loginResult","1");
            mv.setViewName("index");
            //세션에 로그인 회원 정보 보관
            session.setAttribute("loginSession",memberVo.getId() );
            System.out.println("memberVo.getId() ===="+ memberVo.getId() );
            mv.addObject("id" ,memberVo.getId());
            System.out.println("로그인 성공");
        }else{
            mv.addObject("loginResult","2");
            mv.setViewName("login/loginpage");
            System.out.println("로그인 실패");
        }

        return mv;
    }

    @RequestMapping(value = "/logoutprocess.do", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> logoutController(ModelAndView ModelAndView, HttpServletRequest request) throws Exception {
        HashMap<String,String> map = new HashMap<>();
        HttpSession session = request.getSession();
        String id =(String) session.getAttribute("loginSession");
        System.out.println("logoutprocess.do");
        System.out.println("id ====" + id);
        if (id != null) {
            map.put("loginParam","N");
            System.out.println("로그아웃 프로세스 실행");
            session.removeAttribute("loginSession");
            log.info("id = "+ session.getAttribute("loginSession"));
        }else{
            map.put("loginParam","Y");
            System.out.println("로그아웃 프로세스 실행실패");
        }

        return map;
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

    @RequestMapping(value = "/findid")
    public ModelAndView findIdController(ModelAndView mv,String phonenum, String domain, String email) throws Exception {
        HashMap<String, Object> map = new HashMap();
        map.put("phonenum",phonenum);
        map.put("email",email);
        map.put("domain",domain);
        String id = null;
        System.out.println("findidController 실행 !!!");
        log.info("*****phonenum******");
        log.info(phonenum);
        log.info("********************");
        log.info("*******email********");
        log.info(email);
        log.info("********************");
        log.info("***********domain*************");
        log.info(domain);
        log.info("******************************");
        mv.setViewName("login/finededid");
        if(memberService.countIdOfMember(map) > 0) {
            id = memberService.getIdOfMember(map);
            mv.addObject("id",id);
        }else{
            id = null;
        }
        return mv;
    }

    /*@RequestMapping(value = "/findid.do" , method=RequestMethod.POST)
    @ResponseBody
    public ModelAndView findIdAJaxController(ModelAndView mv, String phonenum, String email) throws Exception {
        HashMap<String, String> map = new HashMap();
        map.put("phonenum",phonenum);
        map.put("email",email);
        String id = null;
        if(memberService.countIdOfMember(map) > 0) {
            id = memberService.getIdOfMember(map);
            mv.setViewName("finededid");
        }else{
            id = null;
        }
        return mv;
    }
    */

    @RequestMapping(value = "/findidprocess.do" , method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> findIdProcessController(@RequestParam("email") String email,
                                                           @RequestParam("phonenum") int phonenum,
                                                           @RequestParam("domain") String domain) throws Exception {
        HashMap<String, Object> map = new HashMap();

        log.info("findIdProcessController 기동!!!");

        map.put("email",email);
        map.put("domain",domain);
        map.put("phonenum",phonenum);
        int countOfMember = memberService.countIdOfMember(map);
        if(countOfMember > 0){
            map.put("returnkey","1");
        }else{
            map.put("returnkey","0");
        }
        return map;
    }


}
