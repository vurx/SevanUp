package com.vvvv.sevanUp.util.crawler;


import com.vvvv.basicConfiguration.enums.CharsetType;
import com.vvvv.basicConfiguration.enums.ReturnInfoEnum;
import com.vvvv.basicConfiguration.exception.VurxException;
import com.vvvv.basicConfiguration.util.HttpTool;
import com.vvvv.basicConfiguration.util.StringUtil;
import com.vvvv.model.crawler.NovelVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class NovelCrawler {
    public static List<Map<String, String>> NOVEL_LIST = new ArrayList<>();

    static {
        Map<String, String> LONGZU_5 = new HashMap<>();
        LONGZU_5.put("novelName", "龙族5 - 悼亡者的归来");
        LONGZU_5.put("url", "http://longzu5.co/longzu5");
        NOVEL_LIST.add(LONGZU_5);
    }

    public static Map<String, String> getLatestChapter(String url) {
        String html = HttpTool.doGet(url, CharsetType.UTF8);
        Document document = Jsoup.parse(html);
        Element bookList = document.getElementsByClass("booklist").get(0);
        Element latestChapter = bookList.getElementsByTag("li").get(0).getElementsByTag("a").get(0);
        Map<String, String> map = new HashMap<>();
        map.put("chapter", latestChapter.attr("title"));
        map.put("href", latestChapter.attr("href"));
        return map;
    }

    public static List<Map<String, String>> qryChapter(String url) {
        String html = HttpTool.doGet(url, CharsetType.UTF8);
        Document document = Jsoup.parse(html);
        Elements chapters = document.getElementsByClass("booklist").get(0).getElementsByTag("li");

        List<Map<String, String>> list = new ArrayList<>();
        chapters.forEach(chapter -> {
            Map<String, String> map = new HashMap<>();
            Element item = chapter.getElementsByTag("a").get(0);
            map.put("chapter", item.attr("title"));
            map.put("url", item.attr("href"));
            list.add(map);
        });
        Collections.reverse(list);
        return list;
    }

    public static NovelVo qryChapterContent(String url) {
        String html = HttpTool.doGet(url, CharsetType.UTF8);
        if (StringUtil.isNotEmpty(html)) {
            Document document = Jsoup.parse(html);
            Element contentElement = document.getElementsByClass("post-body").get(0);
            Elements spans = contentElement.getElementsByTag("span");
            StringBuilder content = new StringBuilder();
            spans.forEach(span -> {
                String text = span.text().trim();
                if (StringUtil.isNotEmpty(text)) {
                    content.append("    ").append(text).append("\n\n");
                }
            });
            String title = document.getElementsByClass("post-title").get(0).text();
            String updateDate = document.getElementsByClass("post-date").get(0).text();
            NovelVo novel = new NovelVo();
            novel.setContent(content.toString());
            novel.setTitle(title);
            novel.setUpdateDate(updateDate);
            Elements ps = document.getElementsByTag("p");
            ps.forEach(p -> {
                if (p.text().startsWith("上一篇")) {
                    if (p.getElementsByTag("a").size() > 0) {
                        Element a = p.getElementsByTag("a").get(0);
                        novel.setBeforeChapterUrl(a.attr("href"));
                        novel.setBeforeChapterName(a.attr("title"));
                    }
                } else if (p.text().startsWith("下一篇")) {
                    if (p.getElementsByTag("a").size() > 0) {
                        Element a = p.getElementsByTag("a").get(0);
                        novel.setAfterChapterUrl(a.attr("href"));
                        novel.setAfterChapterName(a.attr("title"));
                    }
                }
            });
            return novel;

        } else {
            throw new VurxException(ReturnInfoEnum.URL_PARSE_ERROR);
        }
    }

    public static void main(String[] args) {
        System.out.println(qryChapterContent("http://longzu5.net/post/3.html"));
    }
}
