package com.cggw.research.controller;

import com.cggw.article.domain.Article;
import com.cggw.register.domain.Doctor;
import com.cggw.register.domain.Hospital;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cggw.research.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by lenovo on 2018/7/13.
 */

@Controller
public class SearchController {

    @Resource
    SearchService searchService;


    /**
     * 测试成功  搜索医院
     * @param keywords
     * @return
     * @throws UnknownHostException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("searchHospital")
    public List<Hospital> searchHospital(String keywords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        return this.showHospital(searchService.searchHosp(keywords));
    }

    /**
     * 搜索医生  成功
     * @param keywords
     * @return
     * @throws UnknownHostException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("searchDoctor")
    public List<Doctor> searchDoctor(String keywords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        return this.showDoctor(searchService.searchDoc(keywords));
    }

    /**
     * 搜索没问题，返回值还要考虑
     * @param keywords
     * @param model
     * @return
     * @throws UnknownHostException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("searchAll")
    public  ModelAndView searchAll(String keywords,ModelAndView model) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        List<Hospital> hospitals = this.showHospital(searchService.searchHosp(keywords));
        List<Doctor> doctors = this.showDoctor(searchService.searchDoc(keywords));
        model.addObject("hospitals",hospitals);
        model.addObject("doctors",doctors);
        return model;
    }

    private List<Doctor> showDoctor(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<Doctor> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        return list;
    }

    private List<Hospital> showHospital(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<Hospital> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        return list;
    }

    private static <T>T  Map2Bean(Map<String,Object> map, Class<T> beanType) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, ParseException {
        System.out.println(map.toString());
        T obj = beanType.newInstance();
        BeanInfo info  = Introspector.getBeanInfo(obj.getClass(),Object.class);
        PropertyDescriptor[] propertyDescriptors =info.getPropertyDescriptors();
        for(PropertyDescriptor p:propertyDescriptors){
            StringBuffer str = new StringBuffer(p.getName().toLowerCase());
            str.insert(1,"_");
            Object value = map.get(new String(str));
            p.getWriteMethod().invoke(obj, value);
        }
        return obj;
    }

}
