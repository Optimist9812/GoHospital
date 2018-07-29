package com.cggw.research.service;


import com.cggw.research.dao.FullTestSearchHosp;
import com.cggw.research.dao.FullTextSearchDoc;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2018/7/13.
 */

@Service
public class SearchServiceImpl implements SearchService {

    FullTextSearchDoc fullTextSearchDoc;

    FullTestSearchHosp fullTestSearchHospital;

    @Override
    public String searchDoc() {
        return null;
    }

    @Override
    public String searchHosp() {
        return null;
    }
}
