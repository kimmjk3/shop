package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private Integer postNumber;
    private Integer boardNumber;
    private String postTitle;
    String postContents;
    Integer postCategory;
    Integer postScore;
    String postInputDate;
    String postCorrect;
    String postDeleteDate;
    Integer postRecommend;
    String userID;
}