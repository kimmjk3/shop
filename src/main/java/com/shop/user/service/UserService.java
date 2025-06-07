package com.shop.user.service;

import com.shop.user.vo.UserVO;

public interface UserService {

	// 회원가입
	public boolean registerUser(UserVO param);
	
	// 로그인처리
	public int actionLogin(UserVO param);

	// 회원상세
	public UserVO getUserDetail(UserVO param);

	//계정비활성화
	public boolean deactivateAccount(String userID);

}