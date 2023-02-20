package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	Integer postNumber;
    Integer boardNumber;
    String postTitle;
    String postContents;
    Integer postCategory;
    Integer postScore;
    String postInputDate;
    String postCorrect;
    String postDeleteDate;
    Integer postRecommend;
    String userID;

    // Attach(첨부)
    Integer attachNumber; // 파일 번호

    String attachThumbnailOriginalName; // 원본 파일명
    String attachThumbnailSaveName; // 저장 파일명
    long attachThumbnailSize; // 파일 크기
    String attachThumbnailLocation; // 경로

    String attachContentsOriginalName; // 원본 파일명
    String attachContentsSaveName; // 저장 파일명
    long attachContentsSize; // 파일 크기
    String attachContentsLocation; // 경로

    String attachInsertDate; // 등록일
    String attachDeleteDate; // 삭제일
    
    public Integer getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
	}
	public Integer getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(Integer boardNumber) {
		this.boardNumber = boardNumber;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContents() {
		return postContents;
	}
	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}
	public Integer getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(Integer postCategory) {
		this.postCategory = postCategory;
	}
	public Integer getPostScore() {
		return postScore;
	}
	public void setPostScore(Integer postScore) {
		this.postScore = postScore;
	}
	public String getPostInputDate() {
		return postInputDate;
	}
	public void setPostInputDate(String postInputDate) {
		this.postInputDate = postInputDate;
	}
	public String getPostCorrect() {
		return postCorrect;
	}
	public void setPostCorrect(String postCorrect) {
		this.postCorrect = postCorrect;
	}
	public String getPostDeleteDate() {
		return postDeleteDate;
	}
	public void setPostDeleteDate(String postDeleteDate) {
		this.postDeleteDate = postDeleteDate;
	}
	public Integer getPostRecommend() {
		return postRecommend;
	}
	public void setPostRecommend(Integer postRecommend) {
		this.postRecommend = postRecommend;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Integer getAttachNumber() {
		return attachNumber;
	}
	public void setAttachNumber(Integer attachNumber) {
		this.attachNumber = attachNumber;
	}
	public String getAttachThumbnailOriginalName() {
		return attachThumbnailOriginalName;
	}
	public void setAttachThumbnailOriginalName(String attachThumbnailOriginalName) {
		this.attachThumbnailOriginalName = attachThumbnailOriginalName;
	}
	public String getAttachThumbnailSaveName() {
		return attachThumbnailSaveName;
	}
	public void setAttachThumbnailSaveName(String attachThumbnailSaveName) {
		this.attachThumbnailSaveName = attachThumbnailSaveName;
	}
	public long getAttachThumbnailSize() {
		return attachThumbnailSize;
	}
	public void setAttachThumbnailSize(long attachThumbnailSize) {
		this.attachThumbnailSize = attachThumbnailSize;
	}
	public String getAttachThumbnailLocation() {
		return attachThumbnailLocation;
	}
	public void setAttachThumbnailLocation(String attachThumbnailLocation) {
		this.attachThumbnailLocation = attachThumbnailLocation;
	}
	public String getAttachContentsOriginalName() {
		return attachContentsOriginalName;
	}
	public void setAttachContentsOriginalName(String attachContentsOriginalName) {
		this.attachContentsOriginalName = attachContentsOriginalName;
	}
	public String getAttachContentsSaveName() {
		return attachContentsSaveName;
	}
	public void setAttachContentsSaveName(String attachContentsSaveName) {
		this.attachContentsSaveName = attachContentsSaveName;
	}
	public long getAttachContentsSize() {
		return attachContentsSize;
	}
	public void setAttachContentsSize(long attachContentsSize) {
		this.attachContentsSize = attachContentsSize;
	}
	public String getAttachContentsLocation() {
		return attachContentsLocation;
	}
	public void setAttachContentsLocation(String attachContentsLocation) {
		this.attachContentsLocation = attachContentsLocation;
	}
	public String getAttachInsertDate() {
		return attachInsertDate;
	}
	public void setAttachInsertDate(String attachInsertDate) {
		this.attachInsertDate = attachInsertDate;
	}
	public String getAttachDeleteDate() {
		return attachDeleteDate;
	}
	public void setAttachDeleteDate(String attachDeleteDate) {
		this.attachDeleteDate = attachDeleteDate;
	}
}