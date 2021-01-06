package com.jookim.bibleapi.controllers;


import com.jookim.bibleapi.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BibleAppController {

    private CrawlerService crawlerService;

    @Autowired
    public BibleAppController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "the app is up and running";
    }

    @GetMapping("/verses")
    @ResponseBody
    public String getVerses() {
        return crawlerService.crawlDailyVerses().toString();
    }
}
