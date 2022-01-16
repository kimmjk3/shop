drop database shop;
create database shop;
show databases;
use shop;

CREATE TABLE `User` (
	`user_ID`	VARCHAR(20)	NOT null primary key,
	`user_PW`	VARCHAR(20)	NOT NULL,
	`user_Name`	VARCHAR(20)	NOT NULL,
	`user_Gender`	INT	NOT NULL	COMMENT '1:남성 2:여성',
	`user_DOB`	VARCHAR(8)	NOT NULL	COMMENT 'ex)20211213',
	`user_Address1`	VARCHAR(20)	NOT NULL	COMMENT '우편번호',
	`user_Address2`	VARCHAR(100)	NOT NULL	COMMENT '기본주소',
	`user_Address3`	VARCHAR(100)	NOT NULL	COMMENT '상세주소',
	`user_Phone`	VARCHAR(20)	NOT NULL,
	`user_Email`	VARCHAR(20)	NOT NULL,
	`user_JoinDate`	DATETIME	NOT NULL	DEFAULT NOW(),
	`user_SecessionDate`	DATETIME	NULL,
	`user_Authority`	INT	NOT NULL	DEFAULT 1	COMMENT '0:관리자 1:유저',
	`user_State`	INT	NOT NULL	DEFAULT 1	COMMENT '0:탈퇴 1:활동중 2:정지'
);

CREATE TABLE `Product` (
	`product_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`product_Category`	VARCHAR(20)	NOT NULL,
	`product_State`	INT	NOT NULL	COMMENT '0:준비중 1:판매중',
	`product_Name`	VARCHAR(20)	NOT NULL,
	`product_Price`	INT	NOT NULL,
	`product_Stock`	INT	NULL,
	`product_Desc`	VARCHAR(2000)	NOT NULL,
	`product_Hits`	INT	NOT NULL	DEFAULT 0,
	`product_Date`	DATETIME	NOT NULL	DEFAULT NOW(),
	`product_Current`	DATETIME	NULL,
	`product_DeleteDate`	DATETIME	NULL
);

CREATE TABLE `Product_ImageFile` (
	`file_Number`	INT	NOT null primary key,
	`orgin_FileName`	VARCHAR(200)	NOT NULL,
	`stored_FileName`	VARCHAR(200)	NOT NULL,
	`stored_thumbNail`	VARCHAR(200)	NOT NULL,
	`file_size`	INT	NOT NULL,
	`create_Date`	DATETIME	NOT NULL	DEFAULT NOW(),
	`delete_Date`	DATETIME	NULL,
	`product_Number`	INT	NOT NULL
);

CREATE TABLE `userOrder_Detail` (
	`order_Detail_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`order_Count`	INT	NULL,
	`order_State`	INT	NULL,
	`order_Number`	INT	NOT null,
	`product_Number`	INT	NOT NULL
);

CREATE TABLE `Board` (
	`board_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`board_Name`	VARCHAR(20)	NOT NULL,
	`board_inputDate`	DATETIME	NOT NULL	DEFAULT NOW(),
	`board_Correct`	DATETIME	NULL,
	`board_DeleteDate`	DATETIME	NULL
);

CREATE TABLE `user_Order` (
	`order_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`order_Date`	DATETIME	NOT NULL	DEFAULT NOW(),
	`delivery_Address1`	VARCHAR(20)	NOT NULL,
	`delivery_Address2`	VARCHAR(20)	NOT NULL,
	`delivery_Address3`	VARCHAR(20)	NOT NULL,
	`delivery_Message`	VARCHAR(100)	NULL,
	`receiver_Name`	VARCHAR(20)	NOT NULL,
	`receiver_Phone`	VARCHAR(20)	NOT NULL,
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `Post` (
	`post_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`board_Number`	INT	NOT NULL,
	`post_Title`	VARCHAR(100)	NOT NULL,
	`post_Contents`	VARCHAR(1000)	NOT NULL,
	`post_Category`	INT	NOT NULL	COMMENT '1: 마우스 2:키보드',
	`post_Score`	INT	NOT NULL,
	`post_InputDate`	DATETIME	NOT NULL	DEFAULT NOW(),
	`post_Correct`	DATETIME	NULL,
	`post_DeleteDate`	DATETIME	NULL,
	`post_Recommend`	INT	NOT NULL	DEFAULT 0,
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `Comment` (
	`comment_Number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`post_Number`	INT	NOT NULL,
	`board_Number`	INT	NOT NULL,
	`comment_Contents`	VARCHAR(1000)	NOT NULL,
	`comment_InputDate`	DATETIME	NOT NULL	DEFAULT NOW(),
	`comment_Correct`	DATETIME	NULL,
	`comment_DeleteDate`	DATETIME	NULL,
	`comment_Recommend`	INT	NOT NULL	DEFAULT 0,
	`user_ID`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `cart_Item` (
	`cart_ItemNumber`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`user_ID`	VARCHAR(20)	NOT NULL,
	`cart_ItemCount`	INT	NULL,
	`product_Number`	INT	NOT NULL
);

CREATE TABLE `Interest_Item` (
	`Interest_ItemNumber`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`user_ID`	VARCHAR(20)	NOT NULL,
	`product_Number`	INT	NOT NULL
);

ALTER TABLE `Product_ImageFile` ADD CONSTRAINT `FK_Product_TO_Product_ImageFile_1` FOREIGN KEY (
	`product_Number`
)
REFERENCES `Product` (
	`product_Number`
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


#사용자 데이터삽입
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
	('idid1234','pwpw1234', '김명진', 1, '19961202', '123-456', '상세주소', '도로명주소', '010-3505-3471', 'kimmjk35@naver.com', default, null, default, default)
	;

#게시판 데이터삽입
INSERT INTO `Board`(
	`board_Number`,
	`board_Name`,
	`board_inputDate`,
	`board_Correct`,
	`board_DeleteDate`)
values
	(default, '리뷰', default, null, null)
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
	`product_Date`,
	`product_Current`,
	`product_DeleteDate`)
values
	(default, 1, 1, '마우수수', '20000', 3, '신제품 마우수수', default, default, null, null)
	;

#게시글 데이터 삽입
INSERT INTO Post 
	VALUES (
              DEFAULT
            , 1
            , 'ㅎㅇ'
            , 'ㅎㅇㅎㅇ'
            , 1
            , 5
            , NOW()
            , NULL
            , NULL
            , default
            , 'idid1234'
        );

select * from user;
SELECT * FROM post;


select * from product;
SELECT * FROM board;



