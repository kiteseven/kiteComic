package org.kiteseven.kitepojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicPage {
    private Long comicId;//用来识别对应哪篇漫画

    private Long chapterNumber;//用来识别对应章节

    private int pageNumber;//页码

    private String imageUrl;//每一页的url
}
