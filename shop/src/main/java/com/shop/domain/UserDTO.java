package com.shop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDTO {

    private String userID; // 아이디
    private String userPW; // 비밀번호
    private String userName; // 이름
    private int userGender; // 성별 1.남성 2.여성
    private String userDOB; // 생년월일
    private String userAddress1; // 우편번호
    private String userAddress2; // 기본주소
    private String userAddress3; // 상세주소
    private String userPhone; // 연락처
    private String userEmail; // 이메일
    private String userJoinDate; // 가입일
    private String userSecessionDate; // 탈퇴일

    private int userAuthority; // 권한 0.관리자 1.유저
    private int userState; // 활동상태 0.탈퇴 1.활동중 2.정지
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public String getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}
	public String getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public String getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public String getUserAddress3() {
		return userAddress3;
	}
	public void setUserAddress3(String userAddress3) {
		this.userAddress3 = userAddress3;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserJoinDate() {
		return userJoinDate;
	}
	public void setUserJoinDate(String userJoinDate) {
		this.userJoinDate = userJoinDate;
	}
	public String getUserSecessionDate() {
		return userSecessionDate;
	}
	public void setUserSecessionDate(String userSecessionDate) {
		this.userSecessionDate = userSecessionDate;
	}
	public int getUserAuthority() {
		return userAuthority;
	}
	public void setUserAuthority(int userAuthority) {
		this.userAuthority = userAuthority;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}

}