package com.jookim.bibleapi.services;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Service
public class CrawlerService {

    @Autowired
    public CrawlerService() {

    }
    private String urlToday = "http://www.su.or.kr/03bible/daily/qtView.do?qtType=QT1";


    public JSONObject crawlDailyVerses() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(urlToday, String.class);
        Document doc = Jsoup.parse(result);

        RestTemplate restTemplate2 = new RestTemplate();
        String result2 = restTemplate.getForObject(urlToday, String.class);
        String result3 = restTemplate.getForObject("http://www.su.or.kr/03bible/daily/qtView.do?qtType=QT5", String.class);
        Document doc2 = Jsoup.parse(result2);
        Document doc3 = Jsoup.parse(result3);
//        String title = doc.select("ul").select("li").select("p").text();
        String title = doc.select("ul").select("li").select("p").first().text();
        String chapter = doc.select("ul").select("li").select("p[class*=book_line]").first().text().toString();
        String content = doc.select("ul").select("li").select("table").text();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("chapter", chapter);
        jsonObject.put("content", content);

        return jsonObject;
    }

}
