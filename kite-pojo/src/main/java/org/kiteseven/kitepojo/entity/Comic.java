package org.kiteseven.kitepojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comic implements Serializable {
    private Long comicId;

    private String comicTitle; // 漫画的标题

    private List<ComicChapter> chapters;//漫画章节

    private String authorName; // 漫画作者名

    private Long authorId; // 漫画作者Id

    private Long uploadUserId;

    private String genre; // 漫画的类别

    private String coverImage; // 封面图片URL

    private String description; // 漫画简介

    private double rating; // 漫画的评分

    private Integer status; // 是否完结

    private Integer review_status; //审核状态

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//最后更新时间

    private Integer leastChapter;//最新章节(更新至)

    private String slug;
}
