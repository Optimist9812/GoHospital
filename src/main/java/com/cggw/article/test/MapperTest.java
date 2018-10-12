package com.cggw.article.test;

import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.io.IOException;
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
        String str = "2018-04-11 16:34:32";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(str);
        Article article = new Article(10000,"纪柳院长：割双眼皮疼不疼？哪种好？","http://120.79.241.203:8080/GoHospital/article/article1.jsp",date,"帆帆老师","http://120.79.241.203:8080/GoHospital/img/article1/1.png","http://120.79.241.203:8080/GoHospital/img/article1/2.png","http://120.79.241.203:8080/GoHospital/img/article1/3.png");
        new ArticleService().addArticle(article);
    }

    @Test
    public void testDel() throws UnknownHostException {
        new ArticleService().deleteArticle(124);
    }

    @Test
    public void testSearch() throws UnknownHostException {
        SearchHits searchHits = new ArticleService().selectArticle("纪柳院长");
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
