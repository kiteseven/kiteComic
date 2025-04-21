package org.kiteseven.kiteserver.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.kiteseven.kitecommon.result.PageResult;
import org.kiteseven.kitecommon.result.Result;
import org.kiteseven.kitepojo.entity.Comic;
import org.kiteseven.kitepojo.entity.UserReaderConfig;
import org.kiteseven.kitepojo.vo.ComicPageVO;
import org.kiteseven.kitepojo.vo.ComicVO;
import org.kiteseven.kitepojo.vo.ComicChapterVO;
import org.kiteseven.kitepojo.vo.ComicShowVO;
import org.kiteseven.kiteserver.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.kiteseven.kitecommon.utils.CryptoUtils.encipher;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/comic")
public class ComicController {
    @Autowired
    ComicService comicService;
    //获取主页展示的漫画
    @GetMapping("/getShowComic")
    public Result<ComicShowVO> getShowComic(){
        log.info("获取主页展示漫画");
        return Result.success(comicService.getShowComic());
    }
    @GetMapping("/{slug}")
    public Result<ComicVO> getComic(Long comicId){
        log.info("获取Id为：{}的漫画",comicId);
        return Result.success(comicService.getComic(comicId));
    }

    @GetMapping("/{slug}/chapters")
    public Result<List<ComicChapterVO>> getAllChapter(Long comicId){
        log.info("准备获取漫画id为：{}的漫画章节",comicId);
        List<ComicChapterVO> chapterVOList=comicService.getComicChapters(comicId);
        return Result.success(chapterVOList);
    }
    @GetMapping("/{slug}/chapters/{chapterNumber}")
    public Result<ComicPageVO> getComicPages(Long chapterNumber,Long comicId){
        log.info("准备获取漫画id为：{}的漫画章节第:{}话",comicId,chapterNumber);
        return Result.success(comicService.getComicPage(chapterNumber,comicId));
    }

    @GetMapping("/settings/readerSettings")
    public Result<UserReaderConfig> getUserReaderConfig(){
        log.info("获取用户阅读设置");
        return Result.success(comicService.getUserReaderConfig());
    }

    @GetMapping("/getShareSlug")
    public Result<String> getShareSlug(String comicId){
        log.info("获取分享链接");
        return Result.success();

    }

    @GetMapping("/share/{shareId}/{comicSlug}")
    public void handleShareLink(@PathVariable String shareId,String comicSlug ,HttpServletResponse response) throws IOException {
           Comic comic =comicService.getComicBySlug(comicSlug);
           String reTargetSlug = "/comic/" + comic.getSlug() + "?v=" + encipher(String.valueOf(comic.getComicId()));
           response.sendRedirect(reTargetSlug);
    }
}
