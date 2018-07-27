package com.cggw.article.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;

/**
 * Created by cgw on 2018/7/26.
 */
@Controller("/articleController")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    //创建一篇新的文章
    @RequestMapping("addArticle")
    public void addArticle(Article article, HttpServletResponse response) throws IOException{
        boolean isSuccess = articleService.addArticle(article);
/*      PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("isSuccess",isSuccess);*/
        response.getWriter().print( new JSONObject().put("isSuccess",isSuccess).toString());
    }

    //删除文章
    /*@RequestBody是将json形式的数据转化成User类型的数据
    @ResponseBody是将User类型的数据转成json发送到前端*/
    @RequestMapping("deleteArticle")
    public @ResponseBody String  deleteArticle(Integer aId, ModelAndView model) throws IOException {
        boolean isSuccess = articleService.deleteArticle(aId);
        System.out.println("****");
        model.addObject(isSuccess);
        return "a";

    }

    //根据关键词查询文章
    @RequestMapping("selectArticle")
    public void selectArticle(String keyWords) throws UnknownHostException{
        SearchHits sesrchHits = articleService.selectArticle(keyWords);
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
