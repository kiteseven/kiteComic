package org.kiteseven.kitepojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicChapter {
    private Long comicId;

    private Long chapterNumber;//章节号

    private String chapterName;//章节名

}
