package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO {
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
}