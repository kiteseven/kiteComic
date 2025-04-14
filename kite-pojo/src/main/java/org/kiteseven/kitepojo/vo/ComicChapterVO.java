package org.kiteseven.kitepojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComicChapterVO {
    private Long comicId;

    private Long chapterNumber;

    private String chapterName;//章节名
}
