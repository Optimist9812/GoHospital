package com.cggw.article.service;

import com.cggw.article.dao.ArticleMapper;
import com.cggw.article.dao.ArticleMapperImpl;
import com.cggw.article.domain.Article;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by cgw on 2018/7/26.
 */
@Service
public class ArticleService {

    private ArticleMapper articleMapper = new ArticleMapperImpl();

    public ArticleService() {
        System.out.println("ArticleService创建完成！");
    }

    //创建一篇新的文章
    public boolean addArticle(Article article) throws IOException {
        return articleMapper.addArticle(article);
    }

    //删除文章
    public boolean deleteArticle(Integer aId) throws UnknownHostException {
        return articleMapper.deleteArticle(aId);
    }

    //根据关键词查询文章
    public SearchHits selectArticle(String keyWords) throws UnknownHostException {
        return articleMapper.selectArticle(keyWords);
    }
}
