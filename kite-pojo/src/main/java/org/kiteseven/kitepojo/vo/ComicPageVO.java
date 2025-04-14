package org.kiteseven.kitepojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kiteseven.kitepojo.entity.ComicPage;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComicPageVO implements Serializable {

    private Long comicId;

    private String comicTitle;

    private String chapterName;//章节名

    private Long totalChapterCount;//总章节数

    private List<ComicPage> comicPages;
}
