package org.kiteseven.kitepojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComicCreateDTO {
    //创建漫画DTO
    private String comicTitle; //漫画标题

    private Integer authorId;  //作者id

    private String authorName;

    private String description;

    private String coverImage;

    private String genre;

    private Integer status;

    private Long uploadUserId;

    private Integer review_status;

    private LocalDateTime updateTime;

    private String slug;
}
