package com.vvvv.sevanUp.util.crawler.comic.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vvvv.basicConfiguration.enums.CharsetType;
import com.vvvv.basicConfiguration.util.HttpTool;
import com.vvvv.basicConfiguration.util.crawler.comic.ComicCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("MKZ")
public class MKZ extends ComicCrawler {
    private static CharsetType charset = CharsetType.UTF8;

    @Override
    public List<Map<String, String>> qryComicChapter(String comicId) {
        List<Map<String, String>> list = new ArrayList<>();

        String url = "https://comic.mkzcdn.com/chapter/?comic_id=" + comicId;
        String data = HttpTool.doGet(url, charset);
        JSONObject jsonObject = JSONObject.parseObject(data);
        if (jsonObject.get("code").equals("200")) {
            JSONArray datas = jsonObject.getJSONArray("data");
            datas.forEach(element -> {
                Map<String, String> map = new HashMap();
                JSONObject jo = (JSONObject) element;
                map.put("chapterId", (String) jo.get("chapter_id"));
                map.put("title", (String) jo.get("title"));
                list.add(map);
            });
        }
        return list;
    }

    @Override
    public String qryLatestComicChapter(String comicId) {
        String url = "https://comic.mkzcdn.com/chapter/?comic_id=" + comicId;
        String data = HttpTool.doGet(url, charset);
        JSONObject jsonObject = JSONObject.parseObject(data);
        if (jsonObject.get("code").equals("200")) {
            JSONArray datas = jsonObject.getJSONArray("data");
            JSONObject latestChapter = (JSONObject) datas.get(datas.size() - 1);
            return (String) latestChapter.get("title");
        }

        return null;
    }

    @Override
    public Map<String, Object> qryChapterImages(String comicId, String chapterId) {
        String url = "https://www.mkzhan.com/" + comicId + "/" + chapterId + ".html";
        String html = HttpTool.doGet(url, charset);
        Document document = Jsoup.parse(html);
        int id = 0;
        Elements imgs = document.getElementsByClass("lazy-read");
        List<Map<String, String>> list = new ArrayList<>();
        for (Element img : imgs) {
            Map map = new HashMap();
            map.put("src", img.attr("data-src"));
            map.put("id", id++);
            list.add(map);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("chapterId", chapterId);
        map.put("chapterName", document.getElementsByClass("last-crumb").get(0).text());
        return map;
    }

    @Override
    public List<Map<String, String>> searchComic(String comicName) {
        List<Map<String, String>> list = new ArrayList<>();

        String url = "https://www.mkzhan.com/search/?keyword=" + comicName;
        String html = HttpTool.doGet(url, charset);
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("common-comic-item");
        for (Element element : elements) {
            Map<String, String> map = new HashMap<>();
            map.put("comicId", element.attr("data-cid"));
            Element comic = element.getElementsByTag("img").get(0);
            map.put("src", comic.attr("data-src"));
            map.put("name", comic.attr("alt"));
            Element latestChapter = element.getElementsByClass("hl").get(0);
            map.put("latestChapterId", latestChapter.attr("href"));
            map.put("latestChapter", latestChapter.text());
            map.put("starSource", "漫客栈");
            map.put("starSourceCode", "MKZ");
            list.add(map);
        }

        return list;
    }
}
