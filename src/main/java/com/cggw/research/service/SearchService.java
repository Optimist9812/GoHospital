package com.cggw.research.service;

import org.elasticsearch.search.SearchHits;

import java.net.UnknownHostException;

/**
 * Created by lenovo on 2018/7/13.
 */

public interface SearchService {

    SearchHits searchDoc(String keywords) throws UnknownHostException;

    SearchHits searchHosp(String keywords) throws UnknownHostException;

}
