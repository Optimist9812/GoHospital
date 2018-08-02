package com.cggw.research.test;

import com.cggw.article.domain.Article;
import com.cggw.register.domain.Hospital;
import com.cggw.research.service.SearchServiceImpl;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cgw on 2018/8/1.
 */
public class ControllerTest {
    public static void main(String[] args) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException, InvocationTargetException {
        SearchServiceImpl searchService = new SearchServiceImpl();
        SearchHits searchHits = searchService.searchHosp("第一");
        List<Hospital> list = showResult(searchHits);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static List<Hospital> showResult(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<Hospital> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String, Object> map = hit.getSource();
            Hospital hospital = Map2Bean(map, Hospital.class);
            list.add(hospital);
        }
        return list;
    }
    private static <T>T  Map2Bean(Map<String,Object> map, Class<T> beanType) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, ParseException {
        System.out.println(map.toString());
        T obj = beanType.newInstance();
        BeanInfo info  = Introspector.getBeanInfo(obj.getClass(),Object.class);
        PropertyDescriptor[] propertyDescriptors =info.getPropertyDescriptors();
        for(PropertyDescriptor p:propertyDescriptors){
            System.out.println(p.getPropertyType());
            System.out.println(p.getName());
            StringBuffer str = new StringBuffer(p.getName().toLowerCase());
            str.insert(1,"_");
            System.out.println(str);
            Object value = map.get(new String(str));
            System.out.println(value);
            p.getWriteMethod().invoke(obj, value);
            System.out.println("****************");
        }
        return obj;
    }
}
