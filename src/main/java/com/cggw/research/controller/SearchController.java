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

    @ResponseBody
    @RequestMapping("searchHospital")
    public List<Hospital> searchHospital(String keywords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return this.showHospital(searchService.searchHosp(keywords));
    }

    @ResponseBody
    @RequestMapping("searchDoctor")
    public List<Doctor> searchDoctor(String keywords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return this.showDoctor(searchService.searchDoc(keywords));
    }

    @ResponseBody
    @RequestMapping("searchAll")
    public ModelAndView searchAll(String keywords, ModelAndView model) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Hospital> hospitals = this.showHospital(searchService.searchHosp(keywords));
        List<Doctor> doctors = this.showDoctor(searchService.searchDoc(keywords));
        model.addObject("hospitals",hospitals);
        model.addObject("doctors",doctors);
        return model;
    }

    private List<Doctor> showDoctor(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        List<Doctor> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String, Object> map = hit.getSource();
            Doctor doctor = this.Map2Bean(map, Doctor.class);
            list.add(doctor);
        }
        return list;
    }

    private List<Hospital> showHospital(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        List<Hospital> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String, Object> map = hit.getSource();
            Hospital hospital = this.Map2Bean(map, Hospital.class);
            list.add(hospital);
        }
        return list;
    }


    private <T>T  Map2Bean(Map<String,Object> map,Class<T> beanType) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        T obj = beanType.newInstance();
        BeanInfo info  = Introspector.getBeanInfo(obj.getClass(),Object.class);
        PropertyDescriptor[] propertyDescriptors =info.getPropertyDescriptors();
        for(PropertyDescriptor p:propertyDescriptors){
            //map根据name获取到值value
            Object value = map.get(p.getName());
            //利用写方法将obj的对应属性进行赋值
            p.getWriteMethod().invoke(obj,value);
        }
        return obj;
    }

}
