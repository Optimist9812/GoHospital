package com.cggw.article.dao;

import com.cggw.article.domain.Article;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by cgw on 2018/7/26.
 */
@Repository
public interface ArticleMapper {

    //创建一篇新的文章
    boolean addArticle(Article article) throws IOException;

    //删除文章
    boolean deleteArticle(Integer aId) throws UnknownHostException;

    //根据关键词查询文章
    SearchHits selectArticle(String keyWords) throws UnknownHostException;

}

