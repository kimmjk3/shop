package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO {
    Integer attachNumber; // 파일 번호
    Integer productNumber; // 게시글번호
    String attachOriginalName; // 원본 파일명
    String attachSaveName; // 저장 파일명
    long attachSize; // 파일 크기
    String attachInsertDate; // 등록일
    String attachDeleteDate; // 삭제일
}