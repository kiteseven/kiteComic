package org.kiteseven.kiteserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kiteseven.kitecommon.constant.MessageConstant;
import org.kiteseven.kitecommon.exception.ComicErrorException;
import org.kiteseven.kitecommon.result.PageResult;
import org.kiteseven.kitepojo.entity.Comic;
import org.kiteseven.kitepojo.entity.UserReaderConfig;
import org.kiteseven.kitepojo.vo.ComicPageVO;
import org.kiteseven.kitepojo.vo.ComicVO;
import org.kiteseven.kitepojo.vo.ComicChapterVO;
import org.kiteseven.kitepojo.vo.ComicShowVO;
import org.kiteseven.kiteserver.mapper.ComicChapterMapper;
import org.kiteseven.kiteserver.mapper.ComicMapper;
import org.kiteseven.kiteserver.mapper.ComicPageMapper;
import org.kiteseven.kiteserver.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicMapper comicMapper;
    @Autowired
    ComicChapterMapper comicChapterMapper;

    @Autowired
    ComicPageMapper comicPageMapper;
    @Override
    public ComicShowVO getShowComic() {
        //创建数据传输对象
        ComicShowVO comicShowVO=new ComicShowVO();
        //获取最新更新,获取最近6部
        comicShowVO.setDailyUpdateComic(comicMapper.getLatestUpdateComic());
        log.info("获取到的最新更新{}",comicShowVO.getDailyUpdateComic());
        comicShowVO.setWeekRankingComic(comicMapper.getWeekRankingComic());
        log.info("获取到的周排行榜{}",comicShowVO.getWeekRankingComic());
        comicShowVO.setMonthRankingComic(comicMapper.getMonthRankingComic());
        log.info("获取到的月排行榜{}",comicShowVO.getMonthRankingComic());
        comicShowVO.setRecommendComic(comicMapper.getRecommendComic());
        log.info("获取到的推荐漫画{}",comicShowVO.getRecommendComic());
        comicShowVO.setBoysRankingComic(comicMapper.getBoysRankingComic());
        log.info("获取到的少年漫{}",comicShowVO.getBoysRankingComic());
        comicShowVO.setGirlsRankingComic(comicMapper.getGirlsRankingComic());
        log.info("获取到的少女漫{}",comicShowVO.getGirlsRankingComic());
        comicShowVO.setCarouselComics(comicMapper.getCarouselComics());
        log.info("获取到的走马灯展示：{}",comicShowVO.getCarouselComics());
        return comicShowVO;
    }

    @Override
    public ComicVO getComic(Long comicId) {
        ComicVO comicVO=comicMapper.getComicById(comicId);
        if(comicVO==null){
            throw new ComicErrorException(MessageConstant.COMIC_NOT_FOUND);
        }
        return comicVO;
    }

    @Override
    public List<ComicChapterVO> getComicChapters(Long comicId) {
        List<ComicChapterVO> chapterVOList=comicChapterMapper.getComicChapters(comicId);
        if(chapterVOList==null||chapterVOList.isEmpty()){
            throw new ComicErrorException(MessageConstant.COMIC_CHAPTER_NOT_FOUND);
        }
        log.info("获取到的章节:{}",chapterVOList);
        return chapterVOList;
    }

    @Override
    public ComicPageVO getComicPage(Long chapterNumber, Long comicId) {
        ComicPageVO comicPageVO=new ComicPageVO(
                comicId,
                comicMapper.getComicTitle(comicId),
                comicChapterMapper.getComicChapterName(comicId,chapterNumber),
                comicChapterMapper.getTotalComicChapterCount(comicId),
                comicPageMapper.getComicPage(chapterNumber,comicId)
        );
        log.info("获取到的章节内容信息：{}",comicPageVO);
        return comicPageVO;
    }

    @Override
    public UserReaderConfig getUserReaderConfig() {
        return comicMapper.getUserReaderConfig();
    }

    @Override
    public Comic getComicBySlug(String slug) {
        return comicMapper.getComicBySlug(slug);
    }
}
