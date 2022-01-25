package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.domain.UserDTO;
import com.shop.service.UserService;

@Controller // 해당 클래스를 컨트롤러로 동작
public class UserController {

    @Autowired
    private UserService userService;

    // 메인페이지 진입
    @GetMapping(value = "/shop/index.do")
    public String openMain(Model model) {
        System.out.println("index페이지 진입");
        return "shop/index";
    }

//회원가입 페이지 진입
    @GetMapping(value = "/shop/join.do") // 회원가입 주소
    public String openUserJoin(Model model) {
        System.out.println("회원가입 페이지 진입");
        model.addAttribute("user", new UserDTO());
        return "shop/join";
    }

//회원가입 처리
    @PostMapping(value = "/shop/join.do")
    public String registerUser(UserDTO params) {
        try {
            System.out.println("접속테스트");
            boolean isRegistered = userService.registerUser(params);
            if (isRegistered == false) {
                // TODO=> 회원가입 등록 실패하였다는 메시지 전달
                System.out.println("회원가입 실패");
            }
        } catch (DataAccessException e) {
            // TODO=> 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
            System.out.println("데이터베이스 처리 오류");
            e.printStackTrace();
        } catch (Exception e) {
            // TODO=> 시스템에 문제가 발생하였다는 메시지 전달
            System.out.println("시스템에 문제가 발생");
        }
        return "redirect:/shop/login.do";
    }

//로그인 주소 진입
    @GetMapping(value = "/shop/login.do") // 로그인 주소
    public String openUserLogin(Model model) {
        model.addAttribute("user", new UserDTO());
        System.out.println("로그인페이지 진입");
        return "shop/login";
    }

    // 로그인 처리
    @PostMapping(value = "/shop/login.do")
    public String loginPOST(HttpServletRequest request, UserDTO params, HttpSession session) {
        // System.out.println("로그인 메서드 진입");
        // System.out.println("전달된 데이터:" + params);

        UserDTO user = userService.loginUser(params);
        // String test =userLo.getUserPW();
        // System.out.println("입력된 데이터:" + params);

        if (user == null) { // 일치하지 않은 아이디와 비밀번호 입력 할 경우
            session.setAttribute("userID", null);
            return "redirect:/shop/login.do";
        } else {
            // 일치하는 아이디, 비밀번호 입력 할 경우 로그인 성공 세션값 부여
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("userPW", user.getUserPW());
            session.setAttribute("userAuthority", user.getUserAuthority());
        }

        return "redirect:/shop/index.do";
    }

    // 로그아웃
    @GetMapping(value = "/shop/logout") // 회원가입 주소
    public String logout(HttpSession session) {
        // 세션제거
        session.invalidate();
        return "redirect:/shop/index.do";
    }
}
