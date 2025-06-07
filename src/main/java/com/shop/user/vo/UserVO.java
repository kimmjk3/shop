package com.shop.user.vo;

public class UserVO {

    private String userID; // 아이디
    private String userPW; // 비밀번호
    private String userName; // 이름
    private String userGender; // 성별 1.남성 2.여성
    private String userDOB; // 생년월일
    private String userAddress1; // 우편번호
    private String userAddress2; // 기본주소
    private String userAddress3; // 상세주소
    private String userPhone; // 연락처
    private String userEmail; // 이메일
    private String userAuthority; // 권한 0.관리자 1.유저
    private String userState; // 활동상태 0.탈퇴 1.활동중 2.정지
	private String userRegisterDt; // 등록일
    private String userUpdateDt; // 수정일
    
	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", userPW=" + userPW + ", userName=" + userName + ", userGender="
				+ userGender + ", userDOB=" + userDOB + ", userAddress1=" + userAddress1 + ", userAddress2="
				+ userAddress2 + ", userAddress3=" + userAddress3 + ", userPhone=" + userPhone + ", userEmail="
				+ userEmail + ", userAuthority=" + userAuthority + ", userState=" + userState + ", userRegisterDt="
				+ userRegisterDt + ", userUpdateDt=" + userUpdateDt + "]";
	}
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
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
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
	public String getUserAuthority() {
		return userAuthority;
	}
	public void setUserAuthority(String userAuthority) {
		this.userAuthority = userAuthority;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
    public String getUserRegisterDt() {
		return userRegisterDt;
	}
	public void setUserRegisterDt(String userRegisterDt) {
		this.userRegisterDt = userRegisterDt;
	}
	public String getUserUpdateDt() {
		return userUpdateDt;
	}
	public void setUserUpdateDt(String userUpdateDt) {
		this.userUpdateDt = userUpdateDt;
	}
}