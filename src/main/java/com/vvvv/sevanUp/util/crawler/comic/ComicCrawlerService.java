package com.vvvv.sevanUp.util.crawler.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComicCrawlerService {
    private Map<String, ComicCrawler> comicCrawlerMap;
    @Autowired
    public void setComicCrawlers(List<ComicCrawler> comicCrawlers) {
        comicCrawlerMap = comicCrawlers.stream().collect(Collectors.toMap(
                comicCrawler -> AnnotationUtils.findAnnotation(comicCrawler.getClass(), Service.class).value(),
                comicCrawler -> comicCrawler
        ));
    }

    public List<Map<String, String>> qryComicChapter(String comicId, String starSourceCode) {
        ComicCrawler comicCrawler = comicCrawlerMap.get(starSourceCode);
        if (comicCrawler == null) {
            return null;
        }
        return comicCrawler.qryComicChapter(comicId);
    }

    public String qryLatestComicChapter(String comicId, String starSourceCode) {
        ComicCrawler comicCrawler = comicCrawlerMap.get(starSourceCode);
        if (comicCrawler == null) {
            return null;
        }
        return comicCrawler.qryLatestComicChapter(comicId);
    }

    public Map<String, Object> qryChapterImages(String comicId, String chapterId, String starSourceCode) {

        ComicCrawler comicCrawler = comicCrawlerMap.get(starSourceCode);
        if (comicCrawler == null) {
            return null;
        }
        return comicCrawler.qryChapterImages(comicId, chapterId);
    }

    public List<Map<String, String>> searchComic(String comicName) {
        List<Map<String, String>> result = new ArrayList<>();
        comicCrawlerMap.forEach((source, service) -> result.addAll(service.searchComic(comicName)));
        return result;
    }

    public String qryChapterBefore(String comicId, String chapterId, String starSourceCode) {
        List<Map<String, String>> list = qryComicChapter(comicId, starSourceCode);
        String beforeChapterId = null;
        for (Map<String, String> stringStringMap : list) {
            if (stringStringMap.get("chapterId").equals(chapterId)) {
                return beforeChapterId;
            }
            beforeChapterId = stringStringMap.get("chapterId");
        }
        return beforeChapterId;
    }

    public String qryChapterAfter(String comicId, String chapterId, String starSourceCode) {
        List<Map<String, String>> list = qryComicChapter(comicId, starSourceCode);
        String beforeChapterId = null;
        for (Map<String, String> stringStringMap : list) {
            if (chapterId.equals(beforeChapterId)) {
                return stringStringMap.get("chapterId");
            }
            beforeChapterId = stringStringMap.get("chapterId");
        }
        return null;
    }

}
