package com.shop.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.user.mapper.UserMapper;
import com.shop.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    // 회원가입
    @Override
    public boolean registerUser(UserVO param) {
        return (userMapper.insertUser(param) > 0) ? true : false;
    }
    
    // 로그인처리
    @Override
    public int actionLogin(UserVO param) {
    	System.out.println("원인");
    	System.out.println(param.toString());
        return userMapper.actionLogin(param);
    }

    // 회원상세
    @Override
    public UserVO getUserDetail(UserVO param) {
        return userMapper.selectUserDetail(param);
    }

    //계정비활성화
    @Override
    public boolean deactivateAccount(String userID) {
        int queryResult = 0;
        queryResult = userMapper.deactivateAccount(userID);

        return (queryResult == 1) ? true : false;
    }
    
}