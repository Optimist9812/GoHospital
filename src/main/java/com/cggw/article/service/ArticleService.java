package com.cggw.article.service;

import com.cggw.article.domain.Article;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by cgw on 2018/7/26.
 */
@Service
public class   ArticleService {

    private TransportClient client;
    private String index = "article_index";
    private String type = "article_type";
    private String[] indics ={"article_index"};

    public ArticleService() {
    }

    public void setup() throws UnknownHostException {
        //设置集群   sniff 自动探查功能（每5秒会自动查询是否有node连接至es集群）
        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").build();
        //创建Client,将Node连接至es集群中
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("120.79.241.203"),9300));
    }

    public void cleanUp(){
        client.close();
    }

    //创建一篇新的文章
    public boolean addArticle(Article article) throws IOException {
        setup();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("aId",article.getAId())
                .field("aName",article.getAName())
                .field("aUrl",article.getAUrl())
                .field("aTime",article.getATime())
                .field("aAuthor",article.getAAuthor())
                .field("img1Url",article.getImg1Url())
                .field("img2Url",article.getImg2Url())
                .field("img3Url",article.getImg3Url())
                .endObject();
          IndexResponse response = client.prepareIndex(index,type,article.getAId().toString()).setSource(source).get();
        cleanUp();
        if(response!=null){
            return true;
        }
        return false;
    }

    //删除文章
    public boolean deleteArticle(Integer aId) throws UnknownHostException {
        setup();
        DeleteResponse deleteResponse = client.prepareDelete(index,type,aId.toString()).get();
        cleanUp();
        return true;
    }

    //根据关键词查询文章
    public SearchHits selectArticle(String keyWords) throws UnknownHostException {
        setup();
        SearchResponse response = client.prepareSearch(indics)  //索引库列表
                .setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.queryStringQuery(keyWords)) //name为具体的field，prefix为所要查找的值
                .get();
        SearchHits searchHits = response.getHits();
        cleanUp();
        return searchHits;
    }

    //初始页面获取固定数额的推荐文章
    public SearchHits getRecommandArticle() throws UnknownHostException {
        setup();
        //指定要搜索的index和type
        SearchRequestBuilder search = client.prepareSearch(index).setTypes(type);
        //使用原生排序优化性能
        search.addSort("_doc", SortOrder.ASC);
        //设置每次读取的数量
        search.setSize(100);
        //默认是查询所有
        search.setQuery(QueryBuilders.queryStringQuery("*:*"));
        //获取首次的查询结果
        SearchResponse scrollResp = search.get();
        cleanUp();
        return scrollResp.getHits();
    }
}
