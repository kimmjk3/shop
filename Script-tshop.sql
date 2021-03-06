drop DATABASE tshop;
create database tshop;
show databases;
use tshop;

CREATE TABLE `User` (
	`user_ID`	VARCHAR(20)	NOT null primary key,	#야이디
	`user_PW`	VARCHAR(20)	NOT NULL,	#비밀번호
	`user_Name`	VARCHAR(20)	NOT NULL,	#이름
	`user_Gender`	INT	NOT NULL	COMMENT '1:남성 2:여성',	#성별
	`user_DOB`	VARCHAR(15)	NOT NULL	COMMENT 'ex)20211213',	#생년월일
	`user_Address1`	VARCHAR(20)	NOT NULL	COMMENT '우편번호',	#주소1
	`user_Address2`	VARCHAR(100)	NOT NULL	COMMENT '기본주소',	#주소2
	`user_Address3`	VARCHAR(100)	NOT NULL	COMMENT '상세주소',	#주소3
	`user_Phone`	VARCHAR(20)	NOT NULL,	#전화번호
	`user_Email`	VARCHAR(20)	NOT NULL,	#이메일
	`user_JoinDate`	DATETIME NOT NULL,	#가입일
	`user_SecessionDate`	DATETIME	NULL,	#탈퇴일
	`user_Authority`	INT	NOT NULL	DEFAULT 1	COMMENT '0:관리자 1:유저',	#권한
	`user_State`	INT	NOT NULL	DEFAULT 1	COMMENT '0:탈퇴 1:활동중 2:정지'	#활동상태
);

CREATE TABLE `Product` (
	`product_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#상품번호
	`product_Category`	VARCHAR(20)	NOT NULL,	#상품카테고리
	`product_State`	INT	NOT NULL	COMMENT '0:삭제 1:준비중 2:판매중',	#상품상태
	`product_Name`	VARCHAR(1000)	NOT NULL,	#상품명
	`product_Price`	INT	NOT NULL,	#상품가격
	`product_Stock`	INT	NULL,	#상품재고
	`product_Desc`	VARCHAR(2000)	NOT NULL,	#상품상세내용
	`product_Hits`	INT	NOT NULL	DEFAULT 0,	#상품조회수
	`product_InputDate`	DATETIME	NOT NULL,	#상품등록일
	`product_Current`	DATETIME	NULL,	#상품수정일
	`product_DeleteDate`	DATETIME	NULL,	#상품삭제일
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `Attach` (
	`attach_Number`	INT	NOT null AUTO_INCREMENT primary KEY,	#파일 번호
	`product_Number`	INT,		#상품번호
	`post_Number` INT,				#게시글번호
	
	`attach_ThumbnailOriginalName` VARCHAR(200) NOT NULL ,		#원본 파일명
	`attach_ThumbnailSaveName`	VARCHAR(200)	NOT NULL,		#저장 파일명
	`attach_ThumbnailSize`	INT	NOT NULL,		#파일 크기
	`attach_ThumbnailLocation` VARCHAR(200) NOT NULL,	#경로
	
	`attach_ContentsOriginalName` VARCHAR(200),		#원본 파일명
	`attach_ContentsSaveName`	VARCHAR(200),		#저장 파일명
	`attach_ContentsSize`	INT,		#파일 크기
	`attach_ContentsLocation` VARCHAR(200),	#경로
	
	`attach_InsertDate`	DATETIME	NOT NULL,		#등록일
	`attach_DeleteDate`	DATETIME	NULL	#삭제일
);

CREATE TABLE `userOrder_Detail` (
	`order_Detail_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`order_Count`	INT	NULL,
	`order_State`	INT	NULL,
	`order_Number`	INT	NOT null,
	`product_Number`	INT	NOT NULL
);

CREATE TABLE `Board` (
	`board_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#게시판번호
	`board_Name`	VARCHAR(20)	NOT NULL,	#게시판이름
	`board_inputDate`	DATETIME	NOT NULL,	#게시판생성일
	`board_Correct`	DATETIME	NULL,	#게시판수정일
	`board_DeleteDate`	DATETIME	NULL	#게시판삭제일
);

CREATE TABLE `user_Order` (
	`order_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#주문번호
	`order_Date`	DATETIME	NOT NULL,	#주문날자
	`delivery_Address1`	VARCHAR(20)	NOT NULL,	#배송주소1
	`delivery_Address2`	VARCHAR(20)	NOT NULL,	#배송주소2
	`delivery_Address3`	VARCHAR(20)	NOT NULL,	#배송주소3
	`delivery_Message`	VARCHAR(100)	NULL,	#배송메세지
	`receiver_Name`	VARCHAR(20)	NOT NULL,		#수령인이름
	`receiver_Phone`	VARCHAR(20)	NOT NULL,	#수령인전화번호
	`user_ID`	VARCHAR(20)	NOT NULL			#아이디
);

CREATE TABLE `Post` (
	`post_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#게시글번호
	`board_Number`	INT	NOT NULL,	#게시판번호
	`post_Title`	VARCHAR(100)	NOT NULL,	#게시글제목
	`post_Contents`	VARCHAR(1000)	NOT NULL,	#게시글내용
	`post_Category`	INT	NOT NULL	COMMENT '1: 마우스 2:키보드',	#게시글 카테고리
	`post_Score`	INT	NOT NULL,	#게시글별점
	`post_InputDate`	DATETIME	NOT NULL,	#게시글 작성일
	`post_Correct`	DATETIME	NULL,	#게시글수정일
	`post_DeleteDate`	DATETIME NULL,	#게시글삭제일
	`post_Recommend`	INT	NOT NULL	DEFAULT 0,	#게시글추천수
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `Comment` (
	`comment_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#댓글번호
	`post_Number`	INT	NOT NULL,	#게시글번호
	`board_Number`	INT	NOT NULL,	#게시판번호
	`comment_Contents`	VARCHAR(1000)	NOT NULL,	#댓글내용
	`comment_InputDate`	DATETIME	NOT NULL,	#댓글작성일
	`comment_Correct`	DATETIME	NULL,	#댓글수정일
	`comment_DeleteDate`	DATETIME	NULL,	#댓글삭제일
	`comment_Recommend`	INT	NOT NULL	DEFAULT 0,	#댓글추천수
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `cart_Item` (
	`cart_ItemNumber`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#장바구니 물품번호
	`user_ID`	VARCHAR(20)	NOT NULL,	#아이디
	`cart_ItemCount`	INT	NULL,	#물품수량
	`product_Number`	INT	NOT NULL	#상품번호
);

CREATE TABLE `Interest_Item` (
	`Interest_ItemNumber`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#관심 풀품번호
	`user_ID`	VARCHAR(20)	NOT NULL,	#아이디
	`product_Number`	INT	NOT NULL,	#상품번호
	`Interest_ItemDeleteDate` DATETIME NULL
);

ALTER TABLE `Attach` ADD CONSTRAINT `FK_Product_TO_Attach_1` FOREIGN KEY (
	`product_Number`
)
REFERENCES `Product` (
	`product_Number`
);

ALTER TABLE `Attach` ADD CONSTRAINT `FK_Post_TO_Attach_2` FOREIGN KEY (
	`post_Number`
)
REFERENCES `Post` (
	`post_Number`
);

ALTER TABLE `userOrder_Detail` ADD CONSTRAINT `FK_user_Order_TO_userOrder_Detail_1` FOREIGN KEY (
	`order_Number`
)
REFERENCES `user_Order` (
	`order_Number`
);

ALTER TABLE `userOrder_Detail` ADD CONSTRAINT `FK_Product_TO_userOrder_Detail_1` FOREIGN KEY (
	`product_Number`
)
REFERENCES `Product` (
	`product_Number`
);

ALTER TABLE `user_Order` ADD CONSTRAINT `FK_User_TO_user_Order_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);

ALTER TABLE `Post` ADD CONSTRAINT `FK_Board_TO_Post_1` FOREIGN KEY (
	`board_Number`
)
REFERENCES `Board` (
	`board_Number`
);

ALTER TABLE `Post` ADD CONSTRAINT `FK_User_TO_Post_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);

ALTER TABLE `Product` ADD CONSTRAINT `FK_User_TO_Product_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);


ALTER TABLE `Comment` ADD CONSTRAINT `FK_Post_TO_Comment_1` FOREIGN KEY (
	`post_Number`
)
REFERENCES `Post` (
	`post_Number`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_Post_TO_Comment_2` FOREIGN KEY (
	`board_Number`
)
REFERENCES `Post` (
	`board_Number`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_User_TO_Comment_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);

ALTER TABLE `cart_Item` ADD CONSTRAINT `FK_User_TO_cart_Item_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);

ALTER TABLE `cart_Item` ADD CONSTRAINT `FK_Product_TO_cart_Item_1` FOREIGN KEY (
	`product_Number`
)
REFERENCES `Product` (
	`product_Number`
);

ALTER TABLE `Interest_Item` ADD CONSTRAINT `FK_User_TO_Interest_Item_1` FOREIGN KEY (
	`user_ID`
)
REFERENCES `User` (
	`user_ID`
);

ALTER TABLE `Interest_Item` ADD CONSTRAINT `FK_Product_TO_Interest_Item_1` FOREIGN KEY (
	`product_Number`
)
REFERENCES `Product` (
	`product_Number`
);


#유저 샘플 데이터삽입
INSERT INTO `USER`(
	`user_ID`,
	`user_PW`,
	`user_Name`,
	`user_Gender`,
	`user_DOB`,
	`user_Address1`,
	`user_Address2`,
	`user_Address3`,
	`user_Phone`,
	`user_Email`,
	`user_JoinDate`,
	`user_SecessionDate`,
	`user_Authority`,
	`user_State`)
values
	('idid1234','pwpw1234', '김이름', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('qwqw1234','qwqw1234', '김이름', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('asas1234','asas1234', '김성명', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('kimidid123','pwpw1234', '김성명', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 1, default)
	;

#게시판 샘플 데이터삽입
INSERT INTO `Board`(
	`board_Number`,
	`board_Name`,
	`board_inputDate`,
	`board_Correct`,
	`board_DeleteDate`)
values
	(default, '리뷰', now(), null, null)
	;

#게시글 데이터 삽입
INSERT INTO `Post`(
	`post_Number`,
	`board_Number`,
	`post_Title`,
	`post_Contents`,
	`post_Category`,
	`post_Score`,
	`post_InputDate`,
	`post_Correct`,
	`post_DeleteDate`,
	`post_Recommend`,
	`user_ID`
) 
	VALUES
		(DEFAULT, 1, '첫번째 리뷰 샘플', '첫번재 리뷰 내용', 1, 5, NOW(), NULL, NULL, default, 'idid1234'),
		(DEFAULT, 1, '두번째 리뷰 샘플', '두번재 리뷰 내용', 1, 3, NOW(), NULL, NULL, default, 'qwqw1234'),
		(DEFAULT, 1, '세번째 리뷰 샘플', '세번재 리뷰 내용', 1, 4, NOW(), NULL, NULL, default, 'asas1234')
		;

#상품 데이터삽입
INSERT INTO `Product`(
	`product_Number`,
	`product_Category`,
	`product_State`,
	`product_Name`,
	`product_Price`,
	`product_Stock`,
	`product_Desc`,
	`product_Hits`,
	`product_InputDate`,
	`product_Current`,
	`product_DeleteDate`,
	`user_id`)
values
	(default, 1, 2, '타이폰S 마르스프로 기계식 키보드 mk3', '172500', 50, '타이폰 마르스프로 기계식 키보드 mk3', default, now(), null, NULL, 'idid1234'),
	(default, 1, 2, '풀튜닝 타이폰 마르스프로 슈팅스타 기계식 키보드', '185000', 40, '풀튜닝 타이폰 마르스프로 슈팅스타 기계식 키보드', default, now(), null, NULL, 'idid1234'),
	(default, 1, 2, '풀튜닝 타이폰 마르스프로 베스타 기계식 키보드', '197000', 35, '풀튜닝 타이폰 마르스프로 베스타 기계식 키보드', default, now(), null, NULL, 'idid1234'),
	(default, 1, 2, '타이폰 마르스프로 슈팅스타 기계식 키보드', '172500', 45, '타이폰 마르스프로 슈팅스타 기계식 키보드', default, now(), null, NULL, 'idid1234'),
	(default, 1, 2, '타이폰 마르스 프로 베스타 기계식 키보드', '174000', 25, '타이폰 마르스 프로 베스타 기계식 키보드', default, now(), null, NULL, 'idid1234'),
	(default, 1, 2, '풀튜닝 마르스프로 MK3 기계식 키보드', '197000', 15, '풀튜닝 마르스프로 MK3 기계식 키보드', default, now(), null, NULL, 'idid1234')
	;

#상품 이미지 첨부
INSERT INTO `Attach`(
	`attach_Number`,	#파일 번호
	`product_Number`,#상품번호
	
	`attach_ThumbnailOriginalName`,		#원본 파일명
	`attach_ThumbnailSaveName`,		#저장 파일명
	`attach_ThumbnailSize`,		#파일 크기
	`attach_ThumbnailLocation`,	#경로
	
	`attach_ContentsOriginalName`,		#원본 파일명
	`attach_ContentsSaveName`,		#저장 파일명
	`attach_ContentsSize`,		#파일 크기
	`attach_ContentsLocation`,	#경로
	
	`attach_InsertDate`,	#등록일
	`attach_DeleteDate`	#삭제일
	)
values
	(default, '1', '원썸1.jpg', '샘플썸네일1.jpg', 500, "/attach/샘플썸네일1.jpg", '원내1', '샘플내용1', 500, "/attach/샘플내용1.jpg", now(), null),
	(default, '2', '원썸2.jpg', '샘플썸네일2.jpg', 500, "/attach/샘플썸네일2.jpg", '원내2', '샘플내용2', 500, "/attach/샘플내용2.jpg", now(), null),
	(default, '3', '원썸3.jpg', '샘플썸네일3.jpg', 500, "/attach/샘플썸네일3.jpg", '원내3', '샘플내용3', 500, "/attach/샘플내용3.jpg", now(), null),
	(default, '4', '원썸4.jpg', '샘플썸네일4.jpg', 500, "/attach/샘플썸네일4.jpg", '원내4', '샘플내용4', 500, "/attach/샘플내용4.jpg", now(), null),
	(default, '5', '원썸5.jpg', '샘플썸네일5.jpg', 500, "/attach/샘플썸네일5.jpg", '원내5', '샘플내용5', 500, "/attach/샘플내용5.jpg", now(), null),
	(default, '6', '원썸6.jpg', '샘플썸네일6.jpg', 500, "/attach/샘플썸네일6.jpg", '원내6', '샘플내용6', 500, "/attach/샘플내용6.jpg", now(), null)
	;

# 게시글 이미지 첨부
INSERT INTO `Attach`(
	`attach_Number`,	#파일 번호
	`post_Number`,#게시글번호
	
	`attach_ThumbnailOriginalName`,		#원본 파일명
	`attach_ThumbnailSaveName`,		#저장 파일명
	`attach_ThumbnailSize`,		#파일 크기
	`attach_ThumbnailLocation`,	#경로
	
	`attach_ContentsOriginalName`,		#원본 파일명
	`attach_ContentsSaveName`,		#저장 파일명
	`attach_ContentsSize`,		#파일 크기
	`attach_ContentsLocation`,	#경로
	
	`attach_InsertDate`,	#등록일
	`attach_DeleteDate`	#삭제일
	)
values
	(default, '1', '리샘1.jpg', '리뷰샘플1.jpg', 500, "/attach/리뷰샘플1.jpg", '리샘1-1', '리뷰샘플1-2', 500, "/attach/리뷰샘플1-2.jpg", now(), null),
	(default, '2', '리샘2.jpg', '리뷰샘플2.jpg', 500, "/attach/리뷰샘플2.jpg", null, null, 0, null, now(), null),
	(default, '3', '리샘3.jpg', '리뷰샘플3.jpg', 500, "/attach/리뷰샘플3.jpg", null, null, 0, null, now(), null)
	;

select * from user;
SELECT * FROM post;
select * from product;
SELECT * FROM board;
SELECT * FROM ATTACH;
SELECT * FROM Interest_Item;

show variables like 'lower_case_table_names';