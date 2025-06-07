package com.shop.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.user.service.UserService;
import com.shop.user.vo.UserVO;
import com.shop.util.MessageMethod;
import com.shop.util.MessageUtils;

@Controller
public class UserController extends MessageUtils {

	@Autowired
	private UserService userService;

	// 메인 페이지
	@GetMapping(value = "/")
	public String callMainPage(Model model) {
		return "shop/index";
	}

	// 회원가입 페이지
	@GetMapping(value = "/join.do")
	public String callUserJoin(Model model, HttpSession session) {
		// 로그인이 되어있을 경우 접근 불가
		if ((String) session.getAttribute("userID") != null) {
			return showMessageWithRedirect("잘못된 접근입니다.", "/shop/index.do", MessageMethod.GET, null, model);
		}
		model.addAttribute("user", new UserVO());
		return "shop/join";
	}

	// 회원가입 처리
	@RequestMapping(value = "/joinAction.do")
	public String registerUser(UserVO param, Model model) {
		try {
			boolean isRegistered = userService.registerUser(param);
			if (isRegistered == false) {
				return showMessageWithRedirect("회원가입 실패하였습니다.", "/login.do", MessageMethod.GET, null, model);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return showMessageWithRedirect("테이터베이스 처리 오류가 발생하였습니다.", "/login.do", MessageMethod.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/login.do", MessageMethod.GET, null, model);
		}
		return showMessageWithRedirect("회원가입 성공하였습니다.", "/login.do", MessageMethod.GET, null, model);
	}

	// 로그인 페이지 진입
	@RequestMapping(value = "/login.do") // 로그인 주소
	public String openUserLogin(Model model, HttpSession session) {
		// 로그인이 되어있을 경우 접근 불가
		if ((String) session.getAttribute("userID") != null) {
			return showMessageWithRedirect("잘못된 접근입니다.", "/shop/index.do", MessageMethod.GET, null, model);
		}
		model.addAttribute("user", new UserVO());
		return "shop/login";
	}

	// 로그인처리
	@RequestMapping(value = "/actionLogin.do")
	public String actionLogin(HttpServletRequest request, UserVO param, HttpSession session, Model model) {
		int result = userService.actionLogin(param);
		if (result != 1) { // 일치하지 않은 아이디와 비밀번호 입력 할 경우
			session.setAttribute("userID", null);
			return showMessageWithRedirect("아이디 또는 비밀번호를 잘못 입력했습니다.\n입력하신 내용을 다시 확인해주세요.", "/login.do", MessageMethod.GET, null, model);
		} else {
			// 일치하는 아이디, 비밀번호 입력 할 경우 로그인 성공 세션값 부여
			UserVO uservo = new UserVO();
			uservo = userService.getUserDetail(param);
			session.setAttribute("userID", uservo.getUserID());
			session.setAttribute("userName", uservo.getUserName());
			session.setAttribute("userAuthority", uservo.getUserAuthority());
		}
		return "redirect:/";
	}

	// 로그아웃
	@RequestMapping(value = "/shop/logoutAction.do")
	public String logout(HttpSession session) {
		session.invalidate();	// 세션제거
		return "redirect:/";
	}
}
