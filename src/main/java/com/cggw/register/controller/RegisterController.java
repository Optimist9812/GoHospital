package com.cggw.register.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.register.domain.*;
import com.cggw.register.service.RegisterService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cgw on 2018/7/24.
 */
@Controller
public class RegisterController {

   @Autowired
    private RegisterService registerService;

    public RegisterController() {
    }

    /**
     * 根据地理位置获取推荐医院（未完成）
     * (修改成分页形式)
     */
    @ResponseBody
    @RequestMapping("getRecommendHosp")
    public void getRecommendHosp(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        PrintWriter out = null;
        int k = 0;
        out = response.getWriter();
        List<Hospital> list = registerService.getHospByCommand();
        Iterator<Hospital> iterator = list.iterator();
        while(iterator.hasNext()&&k<10){
            Hospital hospital = iterator.next();
            json.put("hospital"+k,hospital);
            k++;
        }
        out.print(json.toString());

    }

    /**
     * 根据医院获取科室
     */
    @ResponseBody
    @RequestMapping("getHospdept")
    public List<String> getHospdept(Integer hId) throws IOException {
        return registerService.getHospdept(hId);
    }

    /**
     * 根据医院、科室获取科别
     * public List<String> getHospdeptRoom(Integer hId,Integer hDept)
     */
    @ResponseBody
    @RequestMapping("getHospdeptRoom")
    public List<String> getHospdeptRoom(Integer hId,String hDept,HttpServletResponse response) throws IOException {
        return registerService.getHospdeptRoom(hId,hDept);
    }
    /**
     * 根据科室获取医生
     * public List<Doctor> getDocByDept(Integer hId, Integer hDept)
     */
    @ResponseBody
    @RequestMapping("getDocByDept")
    public List<Doctor> getDocByDept(Integer hId, String hDept, HttpServletResponse response) throws IOException {
        return registerService.getDocByDept(hId,hDept);
    }

    /**
     * 获取某个医生最近时间段的appointmnet(未完成，利用分页进行)
     */
    @ResponseBody
    @RequestMapping("queryDoc")
    public List<Appointment> queryDoc(Integer dId,HttpServletResponse response) throws IOException {
        return registerService.queryDoc(dId);
    }

    /**
     * 根据科别查所有医生信息情况
     *  List<Appointment> queryDept(String hDept)
     */
    @ResponseBody
    @RequestMapping("queryDept")
    public List<Appointment> queryDept(String hDept,HttpServletResponse response) throws IOException {
        return  registerService.queryDept(hDept);
    }

    /**
     * 用户进行一次线上预约
     *
     */
    @ResponseBody
    @RequestMapping("register")
    public boolean register(Registeration registeration) throws IOException {
        Appointment appointment = new Appointment(registeration.getDId(),registeration.getApTime(),registeration.getAType(),null,null);
        Boolean isSuccess = registerService.updateAppointment(appointment,false);
        isSuccess = registerService.insertIntoRegisteration(registeration);
        return isSuccess;
    }

    /**
     * 用户进行一次线下预约
     * @param registeration
     * @param response
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("underline")
    public boolean underline(Underline underline,HttpServletResponse response) throws IOException {
        Appointment appointment = new Appointment(underline.getDId(),underline.getApTime(),underline.getAType(),null,null);
        boolean isSuccess=registerService.updateAppointment(appointment,false);
        isSuccess=registerService.insertIntoUnderline(underline);
        return isSuccess;
    }


    /**
     * 用户取消线上预约
     */
    @ResponseBody
    @RequestMapping("cancelRegisteration")
    public boolean cancelRegisteration(Registeration registeration,HttpServletResponse response) throws IOException {
        Appointment appointment = new Appointment(registeration.getDId(),registeration.getApTime(),registeration.getAType(),null,null);
        Boolean isSuccess=registerService.deleteUnderline(registeration.getUId());
        isSuccess=registerService.updateAppointment(appointment,true);
        return isSuccess;
    }

    /**
     * 用户取消线下预约
     */
    @ResponseBody
    @RequestMapping("cancelUnderline")
    public boolean cancelUnderline(Underline underline,HttpServletResponse response) throws IOException {
        Appointment appointment = new Appointment(underline.getDId(),underline.getApTime(),underline.getAType(),null,null);
        Boolean isSuccess=registerService.deleteUnderline(underline.getUId());
        isSuccess=registerService.updateAppointment(appointment,true);
        return isSuccess;
    }

    /**
     * 用户完成检查
     */
    @ResponseBody
    @RequestMapping("changeState")
    public boolean changeState(Integer id) throws IOException {
        return  registerService.changeState(id);
    }

    /**
     * 用户查看自己的预约
     * 先查看线上表，再查看线下表
     */
    @ResponseBody
    @RequestMapping("showRegisteration")
    public ModelAndView showRegisteration(Integer uId,ModelAndView model) throws IOException {
        List<Registeration> listRegisteration = registerService.queryRegisteration(uId);
        List<Underline> listUnderline = registerService.queryUnderline(uId);
        model.addObject("listRegisteration",listRegisteration);
        model.addObject("listUnderline",listUnderline);
        return model;
    }
}

