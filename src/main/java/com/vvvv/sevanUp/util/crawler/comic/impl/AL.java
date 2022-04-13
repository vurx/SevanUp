package com.vvvv.sevanUp.util.crawler.comic.impl;

import com.vvvv.basicConfiguration.enums.CharsetType;
import com.vvvv.basicConfiguration.util.CommonUtil;
import com.vvvv.basicConfiguration.util.HttpTool;
import com.vvvv.basicConfiguration.util.crawler.comic.ComicCrawler;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service("AL")
public class AL extends ComicCrawler {
    private static CharsetType charset = CharsetType.GBK;

    @Override
    public List<Map<String, String>> qryComicChapter(String comicId) {
        List<Map<String, String>> list = new ArrayList<>();

        String url = "http://www.alimanhua.com/manhua/" + comicId;
        String html = HttpTool.doGet(url, charset);
        Document document = Jsoup.parse(html);
        Element chapterList = document.getElementById("play_0");
        Elements chapters = chapterList.getElementsByTag("a");
        for (Element chapter : chapters) {
            Map<String, String> map = new HashMap<>();
            map.put("chapterId", chapter.attr("href"));
            map.put("title", chapter.attr("title"));
            list.add(map);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public String qryLatestComicChapter(String comicId) {
        String url = "http://www.alimanhua.com/manhua/" + comicId;
        String html = HttpTool.doGet(url, charset);
        if (Strings.isBlank(html)) {
            return null;
        }
        Document document = Jsoup.parse(html);
        Element chapterList = document.getElementById("play_0");
        Element latestChapter = chapterList.getElementsByTag("a").get(0);
        return latestChapter.attr("title");
    }

    @Override
    public Map<String, Object> qryChapterImages(String comicId, String chapterId) {
        String url = "http://m.alimanhua.com/" + chapterId;
        String html = HttpTool.doGet(url, charset);
        Document document = Jsoup.parse(html);
        String cp = CommonUtil.regex(html, "(cp=\")(\\w+=*)(\";)", 2);
        String js = "function base64decode(str){var base64EncodeChars=\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\";" +
                "var base64DecodeChars=new Array(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,-1,-1,-1,63,52,53,54,55,56,57,58,59,60,61,-1,-1,-1,-1,-1,-1,-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,-1,-1,-1,-1,-1,-1,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,-1,-1,-1,-1,-1);" +
                "var c1,c2,c3,c4;var i,len,out;len=str.length;i=0;out=\"\";while(i<len){do{c1=base64DecodeChars[str.charCodeAt(i++)&255]}while(i<len&&c1==-1);if(c1==-1){break}do{c2=base64DecodeChars[str.charCodeAt(i++)&255]}while(i<len&&c2==-1);if(c2==-1){break}out+=String.fromCharCode((c1<<2)|((c2&48)>>4));do{c3=str.charCodeAt(i++)&255;if(c3==61){return out}c3=base64DecodeChars[c3]}while(i<len&&c3==-1);" +
                "if(c3==-1){break}out+=String.fromCharCode(((c2&15)<<4)|((c3&60)>>2));do{c4=str.charCodeAt(i++)&255;if(c4==61){return out}c4=base64DecodeChars[c4]}while(i<len&&c4==-1);if(c4==-1){break}out+=String.fromCharCode(((c3&3)<<6)|c4)}return out};\n" +
                "cp=\"" + cp + "\";\neval(base64decode(cp).slice(4));";
        String imageStr = CommonUtil.excuteJs(js);
        List<String> images = CommonUtil.regexList(imageStr, "(')([a-zA-Z0-9/.]*)(')", 2);
        int id = 0;
        List<Map<String, String>> list = new ArrayList<>();
        for (String image : images) {
            Map map = new HashMap();
            map.put("src", "http://res.img.youzipi.net/" + image);
            map.put("id", id++);
            list.add(map);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("chapterId", chapterId);
        Element title = document.getElementById("mangaTitle");
        String titleStr = title.text();
        String comicName = title.getElementsByTag("a").text();
        map.put("chapterName", titleStr.substring(comicName.length()));
        return map;
    }

    @Override
    public List<Map<String, String>> searchComic(String comicName) {
        List<Map<String, String>> list = new ArrayList<>();

        String url = "http://www.alimanhua.com/e/search/index.php";
        String params;
        try {
            params = "orderby=1&myorder=1&tbname=mh&tempid=3&show=title%2Cplayer%2Cplayadmin%2Cbieming%2Cpinyin&keyboard="
                    + URLEncoder.encode(comicName, "GBK")
                    + "&Submit=%E6%90%9C%E7%B4%A2%E6%BC%AB%E7%94%BB";
        } catch (UnsupportedEncodingException e) {
            params = "";
        }

        String html = HttpTool.doPost(url, params, charset);
        Document document = Jsoup.parse(html);
        Element element = document.getElementById("dmList");
        if (element != null) {
            Elements comics = element.getElementsByTag("li");
            for (Element comic : comics) {
                Map<String, String> map = new HashMap<>();
                String href = comic.getElementsByClass("pic").get(0).attr("href");
                map.put("comicId", CommonUtil.regex(href, "(/manhua/)(\\d+)", 2));
                Element img = element.getElementsByTag("img").get(0);
                map.put("src", img.attr("_src"));
                map.put("name", img.attr("alt"));
                Element chapter = comic.getElementsByClass("yellow").get(0);
                map.put("latestChapterId", " ");
                map.put("latestChapter", chapter.text());
                map.put("starSource", "阿狸漫画");
                map.put("starSourceCode", "AL");
                list.add(map);
            }
        }

        return list;
    }
}
