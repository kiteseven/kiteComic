package org.kiteseven.kiteserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.kiteseven.kitepojo.entity.ComicPage;
import org.kiteseven.kitepojo.vo.ComicVO;

import java.util.List;

@Mapper
public interface ComicMapper {
    @Select("select * from comic order by update_time desc limit 0,6")
    List<ComicVO> getLatestUpdateComic();

    List<ComicVO> getWeekRankingComic();

    List<ComicVO> getMonthRankingComic();
    @Select("select * from comic order by click desc limit 0,6;")
    List<ComicVO> getRecommendComic();
    @Select("select * from comic where genre=1 order by click desc limit 0,9;")
    List<ComicVO> getBoysRankingComic();
    @Select("select * from comic where genre=2 order by click desc limit 0,9;")
    List<ComicVO> getGirlsRankingComic();
    @Select("select * from comic order by click desc limit 0,5;")
    List<ComicVO> getCarouselComics();
    @Select("select * from comic where comic_id=#{comicId}")
    ComicVO getComicById(Long comicId);
    @Select("select comic_title from comic where comic_id =#{comicId}")
    String getComicTitle(Long comicId);

}
