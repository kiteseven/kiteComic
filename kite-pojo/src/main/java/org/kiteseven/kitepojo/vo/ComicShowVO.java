package org.kiteseven.kitepojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//主页的漫画展示数据传输对象
public class ComicShowVO implements Serializable {
    //周排行漫画
    List<ComicVO> weekRankingComic;
    //月排行漫画
    List<ComicVO> monthRankingComic;
    //最新更新
    List<ComicVO> dailyUpdateComic;
    //推荐漫画
    List<ComicVO> RecommendComic;
    //少年漫
    List<ComicVO> boysRankingComic;
    //少女漫
    List<ComicVO> girlsRankingComic;
    //走马灯展示漫画
    List<ComicVO> carouselComics;
}
