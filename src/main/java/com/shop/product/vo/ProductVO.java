package com.shop.product.vo;

public class ProductVO {
    // Product(상품)
    Integer productNumber; // 상품번호
    String productCategory; // 상품카테고리
    Integer productState; // 상품상테(0:준비중 1:판매중)
    String productName; // 상품명
    Integer productPrice; // 상품가격
    Integer productStock; // 상품재고
    String productDesc; // 상품상세내용
    Integer productHits; // 상품조회수
    String productInputDate; // 상품등록일
    String productCurrent; // 상품수정일
    String productDeleteDate; // 상품삭제일
    String userID; // 상품 등록인

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
    
	public Integer getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Integer getProductState() {
		return productState;
	}
	public void setProductState(Integer productState) {
		this.productState = productState;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getProductHits() {
		return productHits;
	}
	public void setProductHits(Integer productHits) {
		this.productHits = productHits;
	}
	public String getProductInputDate() {
		return productInputDate;
	}
	public void setProductInputDate(String productInputDate) {
		this.productInputDate = productInputDate;
	}
	public String getProductCurrent() {
		return productCurrent;
	}
	public void setProductCurrent(String productCurrent) {
		this.productCurrent = productCurrent;
	}
	public String getProductDeleteDate() {
		return productDeleteDate;
	}
	public void setProductDeleteDate(String productDeleteDate) {
		this.productDeleteDate = productDeleteDate;
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