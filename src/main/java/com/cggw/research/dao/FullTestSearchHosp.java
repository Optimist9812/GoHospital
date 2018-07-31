package com.cggw.research.dao;

import com.cggw.article.domain.Article;
import com.cggw.register.domain.Hospital;
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
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by cgw on 2018/7/29.
 */
@Repository
public class FullTestSearchHosp {

    private TransportClient client;
    private String index = "hosp_index";
    private String type = "hosp_type";
    private String[] indics ={"hosp_index"};

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

    public boolean addHosp(Hospital hospital) throws IOException {
        setup();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject()
                .field("h_id",hospital.getHId())
                .field("h_name",hospital.getHName())
                .field("h_type",hospital.getHType())
                .field("h_access",hospital.getHAccess())
                .field("h_addr",hospital.getHAddr())
                .field("h_pic",hospital.getHPic())
                .field("h_score",hospital.getHScore())
                .endObject();
        IndexResponse response = client.prepareIndex(index,type,hospital.getHId().toString()).setSource(source).get();
        cleanUp();
        if(response!=null){
            return true;
        }
        return false;

    }

    public boolean deleteHosp(Integer hId) throws UnknownHostException {
        setup();
        DeleteResponse deleteResponse = client.prepareDelete(index,type,hId.toString()).get();
        cleanUp();
        return true;
    }

    public SearchHits selectHosp(String keyWords) throws UnknownHostException {
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
