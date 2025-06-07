package com.shop.attach.vo;

public class AttachVO {
	
	Integer attachNumber; // 첨부 번호
    Integer productNumber; // 제품번호
    Integer postNumber; // 게시글번호
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
    
	public Integer getAttachNumber() {
		return attachNumber;
	}
	public void setAttachNumber(Integer attachNumber) {
		this.attachNumber = attachNumber;
	}
	public Integer getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}
	public Integer getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
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