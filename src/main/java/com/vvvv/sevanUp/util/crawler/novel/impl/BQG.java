package com.vvvv.sevanUp.util.crawler.novel.impl;

import com.vvvv.basicConfiguration.util.crawler.novel.NovelCrawler;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BQG")
@Log4j2
public class BQG extends NovelCrawler {
    private static final String bqgurl = "http://www.biquge.info";

    @Override
    public List<Map<String, String>> qryComicChapter(String comicId) {
        List<Map<String, String>> maps = new ArrayList<>();
        try {
            Document document = Jsoup.connect(bqgurl+comicId).get();
            Elements as = document.select(".box_con #list dl dd a");
            as.forEach(t->{
                Map<String, String> stringStringHashMap = new HashMap<String, String>();
                stringStringHashMap.put("chapterId", t.attr("href"));
                stringStringHashMap.put("title", t.text());
                maps.add(stringStringHashMap);
            });
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @Override
    public String qryLatestComicChapter(String comicId) {
        String url = bqgurl + comicId;
        String lastName = null;
        try {
            Document document = Jsoup.connect(url).get();
            Elements select = document.select(".box_con #info p a");
//            for (int i = 0; i < select.size(); i++) {
//                if (select.get(i).text().contains("新")) {
//                    lastName = select.get(i).text();
//                }
//            }
            lastName = select.get(0).text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastName;
    }

    //chapterName
    @Override
    public Map<String, Object> qryChapterImages(String comicId, String chapterId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Document document = Jsoup.connect(bqgurl+comicId+chapterId).get();
            Elements content = document.select(".box_con #content");
            StringBuilder sb = new StringBuilder();
            String[] split = content.html().split("<br>");
            for (int i = 0; i < split.length; i++) {
                String con = split[i].replaceAll("&nbsp;", "");
                sb.append("    ").append(con).append("\n\n");
            }
            map.put("data", sb.toString());
            Elements tags = document.select(".box_con .bottem a");
            tags.forEach(t->{
                String text = t.text();
                if (text.contains("上一章")) {
                    map.put("up", t.attr("href"));
                }else if (text.contains("下一章")){
                    map.put("down", t.attr("href"));
                }
            });
            Elements chapterName = document.select(".box_con .bookname h1");
            map.put("chapterName", chapterName.text());
            map.put("chapterId", chapterId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<Map<String, String>> searchComic(String name) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            //笔趣网
            String url = "http://www.biquge.info/modules/article/search.php?searchkey=" + name;
            log.info("BQG:{}", url);
            Connection connect = Jsoup.connect(url);
            Document document = connect.get();
            //搜索出列表
            Elements select = document.select(".grid tbody tr");
            select.forEach(t->{
                Elements as = select.select("td a");
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < as.size(); i++) {
                    String text = as.get(i).text();
                    switch (i) {
                        case 0:
                            map.put("comicId", as.get(i).attr("href"));
                            map.put("name", text);
                            break;
                        case 1:
                            map.put("latestChapterId", " ");
                            map.put("latestChapter", text);
                            break;
                    }
                }
                map.put("src", "");
                map.put("starSource", "笔趣阁");
                map.put("starSourceCode", "BQG");
                list.add(map);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            String url = "http://www.biquge.info/0_383/1596644.html";
            Document document = Jsoup.connect(url).get();
            Elements content = document.select(".box_con #content");
            String[] split = content.html().split("<br>");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                String con = split[i].replaceAll("&nbsp;", "");
                sb.append("    ").append(con).append("\n\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
