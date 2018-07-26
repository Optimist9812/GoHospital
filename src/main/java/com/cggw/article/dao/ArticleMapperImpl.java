package com.cggw.article.dao;

import com.cggw.article.domain.Article;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by cgw on 2018/7/26.
 */
public class ArticleMapperImpl implements ArticleMapper {

    private TransportClient client;
    private String index = "article_index";
    private String type = "article_type";
    private String[] indics ={"article_index"};

    public void setup() throws UnknownHostException {
        //设置集群   sniff 自动探查功能（每5秒会自动查询是否有node连接至es集群）
        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").put("client.transport.sniff",true).build();
        //创建Client,将Node连接至es集群中
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
    }

    public void cleanUp(){
        client.close();
    }

    @Override
    public boolean addArticle(Article article) throws IOException {
        setup();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("a_id",article.getAId())
                .field("a_title",article.getATitle())
                .field("t_time",article.getTTime())
                .field("a_tag",article.getATag())
                .field("a_address",article.getAAddress())
                .endObject();
        IndexResponse response = client.prepareIndex(index,type,article.getAId().toString()).setSource(source).get();
        cleanUp();
        if(response!=null){
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteArticle(Integer aId) throws UnknownHostException {
        setup();
        DeleteResponse deleteResponse = client.prepareDelete(index,type,aId.toString()).get();
        cleanUp();
        return true;
    }



    @Override
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
}
