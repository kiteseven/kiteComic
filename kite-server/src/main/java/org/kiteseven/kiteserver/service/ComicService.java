package org.kiteseven.kiteserver.service;


import org.kiteseven.kitecommon.result.PageResult;
import org.kiteseven.kitepojo.entity.Comic;
import org.kiteseven.kitepojo.entity.UserReaderConfig;
import org.kiteseven.kitepojo.vo.ComicPageVO;
import org.kiteseven.kitepojo.vo.ComicVO;
import org.kiteseven.kitepojo.vo.ComicChapterVO;
import org.kiteseven.kitepojo.vo.ComicShowVO;

import java.util.List;

public interface ComicService {
    ComicShowVO getShowComic();

    ComicVO getComic(Long comicId);

    List<ComicChapterVO> getComicChapters(Long comicId);

    ComicPageVO getComicPage(Long chapterNumber, Long comicId);

    UserReaderConfig getUserReaderConfig();

    Comic getComicBySlug(String slug);
}
