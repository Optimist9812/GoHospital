package com.cggw.research.service;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by lenovo on 2018/7/13.
 */

@Service
public class SearchServiceImpl implements SearchService {

    private TransportClient client;
    private String hospIndex = "hosp_index";
    private String hospType = "hosp_type";
    private String[] hospIndics ={"hosp_index"};

    private String docIndex = "doc_index";
    private String docType = "doc_type";
    private String[] docIndics ={"doc_index"};

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

    @Override
    public SearchHits searchDoc(String keywords) throws UnknownHostException {
        setup();
        SearchResponse response = client.prepareSearch(docIndics)  //索引库列表
                .setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.queryStringQuery(keywords)) //name为具体的field，prefix为所要查找的值
                .get();
        SearchHits searchHits = response.getHits();
        cleanUp();
        return searchHits;
    }

    @Override
    public SearchHits searchHosp(String keywords) throws UnknownHostException {
        setup();
        SearchResponse response = client.prepareSearch(hospIndics)  //索引库列表
                .setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.queryStringQuery(keywords)) //name为具体的field，prefix为所要查找的值
                .get();
        SearchHits searchHits = response.getHits();
        cleanUp();
        return searchHits;
    }
}
