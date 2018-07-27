package com.cggw.research.service;/*
package com.cggw.research.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.net.UnknownHostException;

*/
/**
 * Created by lenovo on 2018/7/12.
 *//*

@Repository("fullTextSearchBrand1")
public class FullTextSearchBrand {

    private TransportClient client;
    private String index = "car_shop";
    private String type = "car";


    @Before
    public void setup() throws UnknownHostException {
        //设置集群   sniff 自动探查功能（每5秒会自动查询是否有node连接至es集群）
        Settings settings = Settings.builder().
                put("cluster.name","elasticsearch").put("client.transport.sniff",true).build();
        //创建Client,将Node连接至es集群中
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
    }

    @After
    public void cleanUp(){
        client.close();
    }

    @Test
    public void searchFullText(){
        SearchResponse searchResponse = client. prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.matchQuery("brand","宝马"))
                .get();

        for(SearchHit searchHit :searchResponse.getHits().getHits()){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    */
/**
     * term测试还有待完成
     *//*

    @Test
    public void searchTerm(){
        SearchResponse searchResponse = client. prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.termQuery("name.raw","宝马316"))
                .get();

        for(SearchHit searchHit :searchResponse.getHits().getHits()){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    @Test
    public void searchMulti(){
        SearchResponse searchResponse = client. prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.multiMatchQuery("宝马","brand","name"))
                .get();

        for(SearchHit searchHit :searchResponse.getHits().getHits()){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    @Test
    public void searchPrefix(){
        SearchResponse searchResponse = client. prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.prefixQuery("name","宝"))
                .get();

        for(SearchHit searchHit :searchResponse.getHits().getHits()){
            System.out.println(searchHit.getSourceAsString());
        }
    }

}
*/
