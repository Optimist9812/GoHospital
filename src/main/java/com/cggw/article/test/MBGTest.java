package com.cggw.article.test;

import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2018/7/12.
 */
public class MBGTest {

    public static void main(String[] args) throws Exception {
        SearchHits sesrchHits =new ArticleService().selectArticle("纪柳院长");
        List<Article> list = showResult(sesrchHits);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static List<Article> showResult(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<Article> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String,Object> map = hit.getSource();
            Article article = Map2Bean(map, Article.class);
            list.add(article);
        }
        return list;
    }
    private static <T>T  Map2Bean(Map<String,Object> map, Class<T> beanType) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, ParseException {
        System.out.println(map.toString());
        T obj = beanType.newInstance();
        BeanInfo info  = Introspector.getBeanInfo(obj.getClass(),Object.class);
        PropertyDescriptor[] propertyDescriptors =info.getPropertyDescriptors();
        for(PropertyDescriptor p:propertyDescriptors){
            System.out.println("2  "+p.getName());
            StringBuffer str = new StringBuffer(p.getName());
            if("A".equals(str.substring(0,1))){
                str = new StringBuffer(str.substring(0,1).toLowerCase()+str.substring(1));
            }
            System.out.println(str);
            Object value = map.get(new String(str));
            if(p.getName().equals("ATime")){
                Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(((String)value).replace("Z", " UTC"));
                value = d;
            }
            System.out.println(value);
            p.getWriteMethod().invoke(obj, value);
            System.out.println("****************");
        }
        return obj;
    }

}
