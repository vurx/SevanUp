package com.vvvv.sevanUp.util.crawler.comic;

import java.util.List;
import java.util.Map;

public abstract class ComicCrawler {
    /**
     * 查询所有章节
     * @param comicId
     * @return
     */
    public abstract List<Map<String, String>> qryComicChapter(String comicId);

    /**
     * 查询最新章节名称
     * @param comicId
     * @return
     */
    public abstract String qryLatestComicChapter(String comicId);

    /**
     * 查询漫画图片
     * @param comicId
     * @param chapterId
     * @return
     */
    public abstract Map<String, Object> qryChapterImages(String comicId, String chapterId);

    /**
     * 漫画搜索
     * @param comicName
     * @return
     */
    public abstract List<Map<String, String>> searchComic(String comicName);
}
