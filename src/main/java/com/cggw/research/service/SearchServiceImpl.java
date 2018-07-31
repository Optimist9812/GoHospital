package com.cggw.research.service;


import com.cggw.research.dao.FullTestSearchHosp;
import com.cggw.research.dao.FullTextSearchDoc;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

/**
 * Created by lenovo on 2018/7/13.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    FullTextSearchDoc fullTextSearchDoc;

    @Autowired
    FullTestSearchHosp fullTestSearchHospital;

    @Override
    public SearchHits searchDoc(String keywords) throws UnknownHostException {
        return fullTextSearchDoc.searchDoc(keywords);
    }

    @Override
    public SearchHits searchHosp(String keywords) throws UnknownHostException {
        return fullTestSearchHospital.selectHosp(keywords);
    }
}
