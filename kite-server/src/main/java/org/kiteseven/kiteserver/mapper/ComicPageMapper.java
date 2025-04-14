package org.kiteseven.kiteserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kiteseven.kitepojo.entity.ComicPage;

import java.util.List;

@Mapper
public interface ComicPageMapper {
    List<ComicPage> getComicPage(Long chapterNumber, Long comicId);
}
