package com.cggw.forum.controller;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.ForumAndName;
import com.cggw.forum.domain.Reply;
import com.cggw.forum.service.ForumService;
import com.cggw.login.controller.JwtHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;


    /**
     * 首页获取所有内容(待测试)
     * 初次进入此界面，获取10个与关键字相关的推荐的帖子(待测试)
     * @return
     */
    @RequestMapping("getAllForum")
    @ResponseBody
    //该注解用于将Controller方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后（如：json格式），写入到Response对象的body数据区。
    public List<ForumAndName> getAllForum(String keywords){
        List<ForumAndName> list = forumService.getAll(keywords);
        return list;
    }

    /**
     * 删除一个帖子，包括forum，reply中的内容（测试成功）
     * @param tId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteForum")
    public boolean deleteForum(Integer tId){
        //根据forumid删除forum
        forumService.deleteReplyBytId(tId);
        return forumService.deleteForumBytId(tId);
    }

    /**
     *创建一个帖子(待测试)
     * @param forum
     * @return
     */
    @ResponseBody
    @RequestMapping("insertIntoForum")
    public boolean inserIntoForum(Forum forum,MultipartFile[] file) throws IOException {
        forum = fileUpload(file,forum.getTId(),forum);
        boolean isSuccess = forumService.insertIntoForum(forum);
        return isSuccess;
    }


    /**
     * 实现图片在服务器中的保存，并且在fourm中传输对应的url地址
     * @param file
     * @return
     */
    private Forum fileUpload(MultipartFile[] file,int tId,Forum forum) throws IOException {
        int count = 1;
        for(MultipartFile mf : file){
            if(!mf.isEmpty()){
                String name = "forum"+tId;
                //获取扩展名
                String ext = FilenameUtils.getExtension(mf.getOriginalFilename());
                //创建文件夹上传路径
                String url = "http://120.79.241.203:8080/GoHospital/forumImg/forum"+tId;
                File file1 = new File(url);
                if(!file1.exists()){
                    file1.mkdir();
                }
                //以绝对路径保存重名后的图片
                mf.transferTo(new File(url+"/"+name+"."+ext));
                //保存到数据库
                String path = url+"/"+name+"."+ext;
                System.out.println(url+"/"+name+"."+ext);
                if(count == 1){
                    forum.setImgurl1(path);
                }else if (count ==2){
                    forum.setImgurl2(path);
                }else{
                    forum.setImgurl3(path);
                }
            }
            count++;
        }
        return forum;
    }

    /**
     * 点击帖子显示帖子所有评论(测试成功)
     * @param tId
     * @return
     * @throws ParseException
     */
    @RequestMapping("queryAllReply")
    @ResponseBody
    public ModelAndView queryAllReply(Integer tId,ModelAndView model) throws ParseException {
        //获取该id的发帖内容
        String tContent = forumService.getForumById(tId);
        System.out.println("获取json数据");
        //获取该id所有评论
        List<Reply> list = forumService.getReplyByForumId(tId);
        model.addObject("tContent",tContent);
        model.addObject("queryAllReply",list);
        return model;
    }


    /**
     * 删除所有自评论（待测试）
     * @param rId
     */
    @ResponseBody
    @RequestMapping("deleteReply")
    public boolean deleteReply(Integer rId){
        boolean isSuccess = false;
        //根据回帖id删除回帖
        isSuccess = forumService.deleteReply(rId);
        return isSuccess;
    }


    /**
     * 添加评论（测试成功）
     * @param reply
     * @return
     */
    @ResponseBody
    @RequestMapping("addReply")
    public boolean addReply(Reply reply){
        //根据评论贴的添加回帖
        return forumService.insertIntoReply(reply);
    }

    /**
     * 根据前端传来的token获取自己所发的所有帖子(待测试)
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping("getOwnForum")
    public List<Forum> getOwnForum(String token){
        int account = (int)new JwtHelper().parseJWT(token).get("account");
        List<Forum> forums = forumService.getOwnForum(account);
        return forums;
    }



}
