package com.shop.service;

import com.shop.domain.UserDTO;

public interface UserService {

    // 회원가입

    public boolean registerUser(UserDTO params);

    public UserDTO getUserDetail(String userID);

    public boolean deleteUser(String userID);

    // 로그인
    public UserDTO loginUser(UserDTO params);

}