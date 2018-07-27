package com.cggw.article.test;

import com.cggw.article.dao.ArticleMapperImpl;
import com.cggw.article.domain.Article;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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

}
