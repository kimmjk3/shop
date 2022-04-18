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
}