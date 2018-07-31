package com.cggw.article.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.article.domain.Article;
import com.cggw.article.service.ArticleService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cgw on 2018/7/26.
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    public ArticleController() {
        System.out.println("ArticleController已经创建");
    }

    //创建一篇新的文章
    @ResponseBody
    @RequestMapping("addArticle")
    public boolean addArticle(@RequestBody Article article) throws IOException{
        boolean isSuccess = articleService.addArticle(article);
        return isSuccess;
/*      PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("isSuccess",isSuccess);
        response.getWriter().print( new JSONObject().put("isSuccess",isSuccess).toString());*/
    }

    //删除文章（已经测通）
    /*@RequestBody是将json形式的数据转化成User类型的数据
    @ResponseBody是将User类型的数据转成json发送到前端*/
    @RequestMapping("deleteArticle")
    public @ResponseBody boolean  deleteArticle(Integer aId) throws IOException {
        boolean isSuccess = articleService.deleteArticle(aId);
        return isSuccess;

    }

    //根据关键词查询文章
    @ResponseBody
    @RequestMapping("selectArticle")
    public List<Article> selectArticle(String keyWords) throws UnknownHostException, IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        SearchHits sesrchHits = articleService.selectArticle(keyWords);
        return showResult(sesrchHits);
    }

    private List<Article> showResult(SearchHits searchHits) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        List<Article> list = new ArrayList<>();
        SearchHit[] hits = searchHits.getHits();
        for(SearchHit hit:hits){
            Map<String, Object> map = hit.getSource();
            Article article = this.Map2Bean(map, Article.class);
            list.add(article);
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
