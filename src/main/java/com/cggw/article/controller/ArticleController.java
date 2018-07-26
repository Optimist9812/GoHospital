package com.cggw.article.controller;

import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by cgw on 2018/7/26.
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    //创建一篇新的文章
    @RequestMapping("addArticle")
    public void addArticle(Article article) throws IOException{
        articleService.addArticle(article);
    }

    //删除文章
    @RequestMapping("deleteArticle")
    public void deleteArticle(Integer aId) throws UnknownHostException{
        articleService.deleteArticle(aId);
    }

    //根据关键词查询文章
    @RequestMapping("selectArticle")
    public void selectArticle(String keyWords) throws UnknownHostException{
        articleService.selectArticle(keyWords);
    }

    private void showResult(SearchResponse response){
        SearchHits searchHits = response.getHits();
        float maxScore = searchHits.getMaxScore();
        System.out.println("maxScore"+maxScore);
        long totalHits = searchHits.getTotalHits();
        System.out.println("totalHits"+totalHits);
        SearchHit[] hits = searchHits.getHits();
        System.out.println("返回记录数"+hits.length);
        for(SearchHit hit:hits){
            long version = hit.version();
            String id = hit.getId();
            String index = hit.getIndex();
            String type = hit.getType();
            float score = hit.getScore();
            System.out.println("===================================================");
            String source = hit.getSourceAsString();
            System.out.println("version: " + version);
            System.out.println("id: " + id);
            System.out.println("index: " + index);
            System.out.println("type: " + type);
            System.out.println("score: " + score);
            System.out.println("source: " + source);
        }
    }
}
