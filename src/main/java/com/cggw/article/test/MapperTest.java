package com.cggw.article.test;

import com.cggw.article.dao.ArticleMapperImpl;
import com.cggw.article.domain.Article;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/12.
 */
public class MapperTest {

    private TransportClient client;
    private String index = "article_index";
    private String type = "article_type";


    @Test
    public void testAdd() throws ParseException, IOException {
        //public Article(Integer aId, String aTitle, Date tTime, String[] aTag)
        String date = "20180711";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse(date);
        String[] a ={"开心","快乐"};
        Article article = new Article(126,"论无敌是多么的寂寞",date1,a,null);
        new ArticleMapperImpl().addArticle(article);
    }

    @Test
    public void testDel() throws UnknownHostException {
        new ArticleMapperImpl().deleteArticle(124);
    }

    @Test
    public void testSearch() throws UnknownHostException {
        SearchHits searchHits = new ArticleMapperImpl().selectArticle("无敌");
        showResult(searchHits);
    }

    private void showResult(SearchHits searchHits){
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
            System.out.println(hit.getSource());
            Map<String, Object> map = hit.getSource();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                System.out.println(entry.getKey()  + "   " + entry.getValue());
            }
        }
    }


}
