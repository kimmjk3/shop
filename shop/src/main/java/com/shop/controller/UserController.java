package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.constant.Method;
import com.shop.domain.ProductDTO;
import com.shop.domain.UserDTO;
import com.shop.service.UserService;
import com.shop.util.UiUtils;

@Controller // 해당 클래스를 컨트롤러로 동작
public class UserController extends UiUtils {

    @Autowired
    private UserService userService;

    // 메인페이지 진입
    @GetMapping(value = "/")
    public String openMain(Model model) {
        System.out.println("index페이지 진입");
        return "shop/index";
    }

    // 공지사항 페이지
    @GetMapping(value = "/shop/notice.do")
    public String openNotice(Model model) {
        System.out.println("공지사항 페이지 진입");
        return showMessageWithRedirect("페이지 준비중입니다.", "/", Method.GET, null, model);
    }

    // 마이페이지 진입
    @GetMapping(value = "/shop/mypage.do")
    public String openMypage(Model model) {
        System.out.println("마이페이지 진입");
        return showMessageWithRedirect("페이지 준비중입니다.", "/", Method.GET, null, model);
    }

    // 회원가입 페이지 진입
    @GetMapping(value = "/shop/join.do") // 회원가입 주소
    public String openUserJoin(Model model) {
        System.out.println("회원가입 페이지 진입");
        model.addAttribute("user", new UserDTO());
        return "shop/join";
    }

    // 회원가입 처리
    @PostMapping(value = "/shop/join.do")
    public String registerUser(UserDTO params, Model model) {
        try {
            System.out.println("접속테스트");
            boolean isRegistered = userService.registerUser(params);
            if (isRegistered == false) {
                // TODO=> 회원가입 등록 실패하였다는 메시지 전달
                System.out.println("회원가입 실패");
                return showMessageWithRedirect("회원가입 실패하였습니다.", "/shop/login.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            // TODO=> 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
            System.out.println("데이터베이스 처리 오류");
            e.printStackTrace();
            return showMessageWithRedirect("테이터베이스 처리 오류가 발생하였습니다.", "/shop/login.do", Method.GET, null, model);
        } catch (Exception e) {
            // TODO=> 시스템에 문제가 발생하였다는 메시지 전달
            System.out.println("시스템에 문제가 발생");
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/login.do", Method.GET, null, model);
        }
        return showMessageWithRedirect("회원가입 성공하였습니다.", "/shop/login.do", Method.GET, null, model);
    }

    // 로그인 주소 진입
    @GetMapping(value = "/shop/login.do") // 로그인 주소
    public String openUserLogin(Model model) {
        model.addAttribute("user", new UserDTO());
        System.out.println("로그인페이지 진입");
        return "shop/login";
    }

    // 로그인 처리
    @PostMapping(value = "/shop/login.do")
    public String loginPOST(HttpServletRequest request, UserDTO params, HttpSession session, Model model) {
        // System.out.println("로그인 메서드 진입");
        // System.out.println("전달된 데이터:" + params);

        UserDTO user = userService.loginUser(params);
        // String test =userLo.getUserPW();
        // System.out.println("입력된 데이터:" + params);

        if (user == null) { // 일치하지 않은 아이디와 비밀번호 입력 할 경우
            session.setAttribute("userID", null);
            return showMessageWithRedirect("아이디 또는 비밀번호를 잘못 입력했습니다.\n입력하신 내용을 다시 확인해주세요.", "/", Method.GET, null,
                    model);
        } else {
            // 일치하는 아이디, 비밀번호 입력 할 경우 로그인 성공 세션값 부여
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("userPW", user.getUserPW());
            session.setAttribute("userAuthority", user.getUserAuthority());
        }

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping(value = "/shop/logout") // 회원가입 주소
    public String logout(HttpSession session) {
        // 세션제거
        session.invalidate();
        return "redirect:/";
    }

    // 관심상품 등록
    @GetMapping(value = "/shop/interestitem.do")
    public String InterestItem(@RequestParam(value = "productNumber", required = false) Integer productNumber,
            Model model, HttpSession session) {

        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }

        try {
            System.out.println("관심상품 등록");
            System.out.println(userID + " " + productNumber);
            boolean isRegistered = userService.registerInterestItem(userID, productNumber);
            if (isRegistered == false) {
                // TODO=> 등록 실패하였다는 메시지 전달
                return showMessageWithRedirect("이미 관심등록된 상품입니다.", "/shop/productlist.do", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            // TODO=> 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
            System.out.println("데이터베이스 처리 오류");
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 오류가 발생하였습니다.", "/shop/productlist.do", Method.GET, null, model);
        } catch (Exception e) {
            // TODO=> 시스템에 문제가 발생하였다는 메시지 전달
            System.out.println("시스템에 문제가 발생");
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/productlist.do", Method.GET, null, model);
        }
        return showMessageWithRedirect("관심상품 등록 되었습니다.", "/shop/productlist.do", Method.GET, null, model);
    }

    // 관심상품 리스트 페이지 이동
    @GetMapping(value = "/shop/interestitemlist.do")
    public String openProductList(Model model, HttpSession session) {

        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }

        List<ProductDTO> interestItemList = userService.getInterestItemList(userID);
        model.addAttribute("interestItemList", interestItemList);

        return "shop/interestitemlist";
    }

    @GetMapping(value = "/shop/interestitemdelete.do")
    public String deleteBoard(@RequestParam(value = "productNumber", required = false) Integer productNumber,
            Model model, HttpSession session) {
        System.out.println("/shop/boarddelete.do 실행됨");

        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }

        if (userID == null && productNumber == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/shop/interestitemlist.do", Method.GET, null, model);
        }

        try {
            boolean isDeleted = userService.deleteInterestItem(userID, productNumber);
            if (isDeleted == false) {
                return showMessageWithRedirect("관심상품 삭제에 실패하였습니다.", "/shop/interestitemlist.do", Method.GET, null,
                        model);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/shop/interestitemlist.do", Method.GET, null,
                    model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/shop/interestitemlist.do", Method.GET, null, model);
        }

        return showMessageWithRedirect("관심상품 삭제가 완료되었습니다.", "/shop/interestitemlist.do", Method.GET, null, model);
    }
}
