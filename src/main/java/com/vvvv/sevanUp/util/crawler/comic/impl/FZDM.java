//package com.vvvv.sevanUp.util.crawler.comic.impl;
//
//
//import com.vvvv.basicConfiguration.enums.CharsetType;
//import com.vvvv.basicConfiguration.util.CommonUtil;
//import com.vvvv.basicConfiguration.util.HttpTool;
//import com.vvvv.basicConfiguration.util.crawler.comic.ComicCrawler;
//import org.apache.logging.log4j.util.Strings;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service("FZDM")
//public class FZDM extends ComicCrawler {
//    private static final CharsetType charset = CharsetType.UTF8;
//
//    @Override
//    public List<Map<String, String>> qryComicChapter(String comicId) {
//        List<Map<String, String>> list = new ArrayList<>();
//
//        String url = "https://manhua.fzdm.com/" + comicId;
//        String html = HttpTool.doGet(url, charset);
//        Document document = Jsoup.parse(html);
//        Elements chapters = document.getElementsByClass("pure-u-1-2");
//        for (Element chapter : chapters) {
//            Element chapterInfo = chapter.getElementsByTag("a").get(0);
//            Map<String, String> map = new HashMap<>();
//            map.put("chapterId", chapterInfo.attr("href"));
//            map.put("title", chapterInfo.attr("title"));
//            list.add(map);
//        }
//        Collections.reverse(list);
//        return list;
//    }
//
//    @Override
//    public String qryLatestComicChapter(String comicId) {
//        String url = "https://manhua.fzdm.com/" + comicId;
//        String html = HttpTool.doGet(url, charset);
//        if (Strings.isBlank(html)) {
//            return null;
//        }
//        Document document = Jsoup.parse(html);
//        Element chapter = document.getElementsByClass("pure-u-1-2").get(0).getElementsByTag("a").get(0);
//        return chapter.attr("title");
//    }
//
//    @Override
//    public Map<String, Object> qryChapterImages(String comicId, String chapterId) {
//        String url = "https://manhua.fzdm.com/" + comicId + chapterId;
//        String suffix = "";
//        int id = 0;
//
//        List<Map<String, String>> list = new ArrayList<>();
//        a:
//        while (true) {
//            String html = HttpTool.doGet(url + suffix, charset);
//            Document document = Jsoup.parse(html);
//            Map<String, String> map = new HashMap<>();
//            String imgUrl = CommonUtil.regex(html, "(mhss=\")([0-9a-zA-Z./:]+[0-9a-zA-Z.]+)(\")", 2)
//                    + "/" + CommonUtil.regex(html, "(mhurl=\")([0-9a-zA-Z./]+)(\")", 2);
//            map.put("src", imgUrl);
//            map.put("id", String.valueOf(id++));
//            list.add(map);
//            Elements elements = document.getElementsByClass("pure-button-primary");
//            for (Element element : elements) {
//                if (element.text().equals("下一页")) {
//                    suffix = element.attr("href");
//                    continue a;
//                }
//            }
//            break;
//        }
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("data", list);
//        map.put("chapterId", chapterId);
//        map.put("chapterName", chapterId);
//        return map;
//    }
//
//    @Override
//    public List<Map<String, String>> searchComic(String comicName) {
//        return Collections.emptyList();
//    }
//}
