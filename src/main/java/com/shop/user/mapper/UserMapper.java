package com.shop.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shop.user.vo.UserVO;

@Mapper
public interface UserMapper {

    // 회원가입
    public int insertUser(UserVO param);
    
    // 로그인처리
    public int actionLogin(UserVO param);

    // 회원상세
    public UserVO selectUserDetail(UserVO param);

    public int updateUser(UserVO params);

    //계정비활성화
    public int deactivateAccount(String userID);
    
}
