package com.cggw.resaerch.controller;

import com.cggw.resaerch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/7/13.
 */
@Controller
public class SearchController {

    @Autowired
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
