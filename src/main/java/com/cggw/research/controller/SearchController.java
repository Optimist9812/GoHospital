package com.cggw.research.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.cggw.research.service.SearchService;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;


/**
 * Created by lenovo on 2018/7/13.
 */

@Controller
public class SearchController {

    @Resource
    SearchService searchService;

    @RequestMapping("searchHospital")
    public void searchHospital(){
        searchService.searchHosp();
    }

    @RequestMapping("searchDoctor")
    public void searchDoctor(){
        searchService.searchDoc();
    }

    @RequestMapping("searchAll")
    public void searchAll(){
        searchService.searchHosp();
        searchService.searchDoc();
    }
}
