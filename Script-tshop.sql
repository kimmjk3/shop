drop DATABASE tshop;
create database tshop;
show databases;
use tshop;

CREATE TABLE `user` (
	`user_id`	VARCHAR(20)	NOT null primary key,	#야이디
	`user_pw`	VARCHAR(20)	NOT NULL,	#비밀번호
	`user_name`	VARCHAR(20)	NOT NULL,	#이름
	`user_gender`	INT	NOT NULL	COMMENT '1:남성 2:여성',	#성별
	`user_dob`	VARCHAR(15)	NOT NULL	COMMENT 'ex)20211213',	#생년월일
	`user_address1`	VARCHAR(20)	NOT NULL	COMMENT '우편번호',	#주소1
	`user_address2`	VARCHAR(100)	NOT NULL	COMMENT '기본주소',	#주소2
	`user_address3`	VARCHAR(100)	NOT NULL	COMMENT '상세주소',	#주소3
	`user_phone`	VARCHAR(20)	NOT NULL,	#전화번호
	`user_email`	VARCHAR(20)	NOT NULL,	#이메일
	`user_join_date`	DATETIME NOT NULL,	#가입일
	`user_secession_date`	DATETIME	NULL,	#탈퇴일
	`user_authority`	INT	NOT NULL	DEFAULT 1	COMMENT '0:관리자 1:유저',	#권한
	`user_state`	INT	NOT NULL	DEFAULT 1	COMMENT '0:탈퇴 1:활동중 2:정지'	#활동상태
);

CREATE TABLE `product` (
	`product_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#상품번호
	`product_category`	VARCHAR(20)	NOT NULL,	#상품카테고리
	`product_state`	INT	NOT NULL	COMMENT '0:삭제 1:준비중 2:판매중',	#상품상태
	`product_name`	VARCHAR(1000)	NOT NULL,	#상품명
	`product_price`	INT	NOT NULL,	#상품가격
	`product_stock`	INT	NULL,	#상품재고
	`product_desc`	VARCHAR(2000)	NOT NULL,	#상품상세내용
	`product_hits`	INT	NOT NULL	DEFAULT 0,	#상품조회수
	`product_input_date`	DATETIME	NOT NULL,	#상품등록일
	`product_current`	DATETIME	NULL,	#상품수정일
	`product_delete_date`	DATETIME	NULL,	#상품삭제일
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `attach` (
	`attach_number`	INT	NOT null AUTO_INCREMENT primary KEY,	#파일 번호
	`product_number`	INT,		#상품번호
	`post_number` INT,				#게시글번호
	
	`attach_thumbnail_original_name` VARCHAR(200) NOT NULL ,		#원본 파일명
	`attach_thumbnail_save_name`	VARCHAR(200)	NOT NULL,		#저장 파일명
	`attach_thumbnail_size`	INT	NOT NULL,		#파일 크기
	`attach_thumbnail_location` VARCHAR(200) NOT NULL,	#경로
	
	`attach_contents_original_name` VARCHAR(200),		#원본 파일명
	`attach_contents_save_name`	VARCHAR(200),		#저장 파일명
	`attach_contents_size`	INT,		#파일 크기
	`attach_contents_location` VARCHAR(200),	#경로
	
	`attach_insert_date`	DATETIME	NOT NULL,		#등록일
	`attach_delete_date`	DATETIME	NULL	#삭제일
);

CREATE TABLE `user_order_detail` (
	`order_detail_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',
	`order_count`	INT	NULL,
	`order_state`	INT	NULL,
	`order_number`	INT	NOT null,
	`product_number`	INT	NOT NULL
);

CREATE TABLE `board` (
	`board_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#게시판번호
	`board_name`	VARCHAR(20)	NOT NULL,	#게시판이름
	`board_input_date`	DATETIME	NOT NULL,	#게시판생성일
	`board_correct`	DATETIME	NULL,	#게시판수정일
	`board_delete_date`	DATETIME	NULL	#게시판삭제일
);

CREATE TABLE `user_order` (
	`order_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#주문번호
	`order_date`	DATETIME	NOT NULL,	#주문날자
	`delivery_address1`	VARCHAR(20)	NOT NULL,	#배송주소1
	`delivery_address2`	VARCHAR(20)	NOT NULL,	#배송주소2
	`delivery_address3`	VARCHAR(20)	NOT NULL,	#배송주소3
	`delivery_message`	VARCHAR(100)	NULL,	#배송메세지
	`receiver_name`	VARCHAR(20)	NOT NULL,		#수령인이름
	`receiver_phone`	VARCHAR(20)	NOT NULL,	#수령인전화번호
	`user_id`	VARCHAR(20)	NOT NULL			#아이디
);

CREATE TABLE `post` (
	`post_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#게시글번호
	`board_number`	INT	NOT NULL,	#게시판번호
	`post_title`	VARCHAR(100)	NOT NULL,	#게시글제목
	`post_contents`	VARCHAR(1000)	NOT NULL,	#게시글내용
	`post_category`	INT	NOT NULL	COMMENT '1: 마우스 2:키보드',	#게시글 카테고리
	`post_score`	INT	NOT NULL,	#게시글별점
	`post_input_date`	DATETIME	NOT NULL,	#게시글 작성일
	`post_correct`	DATETIME	NULL,	#게시글수정일
	`post_delete_date`	DATETIME NULL,	#게시글삭제일
	`post_recommend`	INT	NOT NULL	DEFAULT 0,	#게시글추천수
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `comment` (
	`comment_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#댓글번호
	`post_number`	INT	NOT NULL,	#게시글번호
	`board_number`	INT	NOT NULL,	#게시판번호
	`comment_contents`	VARCHAR(1000)	NOT NULL,	#댓글내용
	`comment_inputDate`	DATETIME	NOT NULL,	#댓글작성일
	`comment_correct`	DATETIME	NULL,	#댓글수정일
	`comment_delete_date`	DATETIME	NULL,	#댓글삭제일
	`comment_recommend`	INT	NOT NULL	DEFAULT 0,	#댓글추천수
	`user_id`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `cart_item` (
	`cart_item_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#장바구니 물품번호
	`user_id`	VARCHAR(20)	NOT NULL,	#아이디
	`cart_item_count`	INT	NULL,	#물품수량
	`product_number`	INT	NOT NULL	#상품번호
);

CREATE TABLE `interest_item` (
	`interest_item_number`	INT	NOT null auto_increment primary key	COMMENT 'AUTO_INCREMENT 추가',	#관심 풀품번호
	`user_id`	VARCHAR(20)	NOT NULL,	#아이디
	`product_number`	INT	NOT NULL,	#상품번호
	`interest_item_delete_date` DATETIME NULL
);

ALTER TABLE `attach` ADD CONSTRAINT `FK_product_TO_attach_1` FOREIGN KEY (
	`product_number`
)
REFERENCES `product` (
	`product_number`
);

ALTER TABLE `attach` ADD CONSTRAINT `FK_post_TO_attach_2` FOREIGN KEY (
	`post_number`
)
REFERENCES `post` (
	`post_number`
);

ALTER TABLE `user_order_detail` ADD CONSTRAINT `FK_user_order_TO_user_order_detail_1` FOREIGN KEY (
	`order_number`
)
REFERENCES `user_order` (
	`order_number`
);

ALTER TABLE `user_order_detail` ADD CONSTRAINT `FK_product_TO_user_order_detail_1` FOREIGN KEY (
	`product_number`
)
REFERENCES `product` (
	`product_number`
);

ALTER TABLE `user_order` ADD CONSTRAINT `FK_user_TO_user_order_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (
	`board_number`
)
REFERENCES `board` (
	`board_number`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `product` ADD CONSTRAINT `FK_user_TO_product_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);


ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (
	`post_number`
)
REFERENCES `post` (
	`post_number`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_2` FOREIGN KEY (
	`board_number`
)
REFERENCES `post` (
	`board_number`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_user_TO_comment_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `cart_item` ADD CONSTRAINT `FK_user_TO_cart_item_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `cart_item` ADD CONSTRAINT `FK_product_TO_cart_item_1` FOREIGN KEY (
	`product_number`
)
REFERENCES `product` (
	`product_number`
);

ALTER TABLE `interest_item` ADD CONSTRAINT `FK_user_TO_interest_item_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `interest_item` ADD CONSTRAINT `FK_product_TO_interest_item_1` FOREIGN KEY (
	`product_number`
)
REFERENCES `product` (
	`product_number`
);


#유저 샘플 데이터삽입
INSERT INTO `user`(
	`user_id`,
	`user_pw`,
	`user_name`,
	`user_gender`,
	`user_dob`,
	`user_address1`,
	`user_address2`,
	`user_address3`,
	`user_phone`,
	`user_email`,
	`user_join_date`,
	`user_secession_date`,
	`user_authority`,
	`user_state`)
values
	('idid1234','pwpw1234', '김이름', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('qwqw1234','qwqw1234', '김이름', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('asas1234','asas1234', '김성명', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 0, default),
	('kimidid123','pwpw1234', '김성명', 1, '19960000', '123-456', '상세주소', '도로명주소', '010-1234-5678', 'idid1234@naver.com', now(), null, 1, default)
	;

#게시판 샘플 데이터삽입
INSERT INTO `board`(
	`board_number`,
	`board_Name`,
	`board_input_date`,
	`board_correct`,
	`board_delete_date`)
values
	(default, '리뷰', now(), null, null)
	;

#게시글 데이터 삽입
INSERT INTO `post`(
	`post_number`,
	`board_number`,
	`post_title`,
	`post_contents`,
	`post_category`,
	`post_score`,
	`post_input_date`,
	`post_correct`,
	`post_delete_date`,
	`post_recommend`,
	`user_id`
) 
	VALUES
		(DEFAULT, 1, '첫번째 리뷰 샘플', '첫번재 리뷰 내용', 1, 5, NOW(), NULL, NULL, default, 'idid1234'),
		(DEFAULT, 1, '두번째 리뷰 샘플', '두번재 리뷰 내용', 1, 3, NOW(), NULL, NULL, default, 'qwqw1234'),
		(DEFAULT, 1, '세번째 리뷰 샘플', '세번재 리뷰 내용', 1, 4, NOW(), NULL, NULL, default, 'asas1234')
		;

#상품 데이터삽입
INSERT INTO `product`(
	`product_number`,
	`product_category`,
	`product_state`,
	`product_name`,
	`product_price`,
	`product_stock`,
	`product_desc`,
	`product_hits`,
	`product_input_date`,
	`product_current`,
	`product_delete_date`,
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
INSERT INTO `attach`(
	`attach_number`,	#파일 번호
	`product_number`,#상품번호
	
	`attach_thumbnail_original_name`,		#원본 파일명
	`attach_thumbnail_save_name`,		#저장 파일명
	`attach_thumbnail_size`,		#파일 크기
	`attach_thumbnail_location`,	#경로
	
	`attach_contents_original_name`,		#원본 파일명
	`attach_contents_save_name`,		#저장 파일명
	`attach_contents_size`,		#파일 크기
	`attach_contents_location`,	#경로
	
	`attach_insert_date`,	#등록일
	`attach_delete_date`	#삭제일
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
INSERT INTO `attach`(
	`attach_number`,	#파일 번호
	`post_number`,#게시글번호
	
	`attach_thumbnail_original_name`,		#원본 파일명
	`attach_thumbnail_save_name`,		#저장 파일명
	`attach_thumbnail_size`,		#파일 크기
	`attach_thumbnail_location`,	#경로
	
	`attach_contents_original_name`,		#원본 파일명
	`attach_contents_save_name`,		#저장 파일명
	`attach_contents_size`,		#파일 크기
	`attach_contents_location`,	#경로
	
	`attach_insert_date`,	#등록일
	`attach_delete_date`	#삭제일
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
SELECT * FROM attach;
SELECT * FROM interest_item;

show variables like 'lower_case_table_names';