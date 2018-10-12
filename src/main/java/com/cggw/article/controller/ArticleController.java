package com.cggw.article.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cgw on 2018/7/26.
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    public ArticleController() {
    }

    //创建一篇新的文章（待测）
    @ResponseBody
    @RequestMapping(value="addArticle")
    public boolean addArticle(Article article) throws IOException{
        boolean isSuccess = articleService.addArticle(article);
        return isSuccess;
}

    //删除文章（已经测通）
    /*@RequestBody是将json形式的数据转化成User类型的数据
    @ResponseBody是将User类型的数据转成json发送到前端*/
    @RequestMapping("deleteArticle")
    public @ResponseBody boolean  deleteArticle(Integer aId) throws IOException {
        boolean isSuccess = articleService.deleteArticle(aId);
        System.out.println("成功删除");
        return isSuccess;
    }

    //根据关键词查询文章(测通)
    @ResponseBody
    @RequestMapping("selectArticle")
    public List<Article> selectArticle(String keyWords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        SearchHits sesrchHits =new ArticleService().selectArticle(keyWords);
        List<Article> list = showResult(sesrchHits);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        return list;
    }

    //获取初始推荐文章
    @ResponseBody
    @RequestMapping("getRecommandArticle")
    public List<Article> getRecommandArticle() throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException, InvocationTargetException {
        SearchHits searchHits = articleService.getRecommandArticle();
        List<Article> list = showResult(searchHits);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return list;
    }

    private List<Article> showResult(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, ParseException {
        List<Article> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String, Object> map = hit.getSource();
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
