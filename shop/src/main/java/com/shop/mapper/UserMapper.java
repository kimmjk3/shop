package com.shop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.UserDTO;

@Mapper
public interface UserMapper {

    // 회원가입값 입력
    public int insertUser(UserDTO params);

    public UserDTO selectUserDetail(String userID);

    public int updateUser(UserDTO params);

    public int deleteUser(String userID);

    // 로그인
    public UserDTO userLogin(UserDTO params);

}
