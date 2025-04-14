package org.kiteseven.kiteserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.kiteseven.kitepojo.vo.ComicChapterVO;

import java.util.List;

@Mapper
public interface ComicChapterMapper {
    @Select("select * from comic_chapter where comic_id=#{comicId} order by chapter_number")
    List<ComicChapterVO> getComicChapters(Long comicId);

    @Select("select chapter_name from comic_chapter where comic_id=#{comicId} and chapter_number=#{chapterNumber}")
    String getComicChapterName(Long comicId,Long chapterNumber);
    @Select("select COUNT(chapter_number) from comic_chapter where comic_id=#{comicId}")
    Long getTotalComicChapterCount(Long comicId);
}
