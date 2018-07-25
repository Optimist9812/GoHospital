package com.cggw.register.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.register.domain.*;
import com.cggw.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("getHospdept")
    public void getHospdept(Integer hId,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<String> list = registerService.getHospdept(hId);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String hostdept = iterator.next();
            json.put("hostdept"+i,hostdept);
            i++;
        }
        out.print(json.toString());
    }

    /**
     * 根据医院、科室获取科别
     * public List<String> getHospdeptRoom(Integer hId,Integer hDept)
     */
    @RequestMapping("getHospdeptRoom")
    public void getHospdeptRoom(Integer hId,String hDept,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<String> list = registerService.getHospdeptRoom(hId,hDept);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String hospdeptRoom = iterator.next();
            json.put("hospdeptRoom"+i,hospdeptRoom);
            i++;
        }
        out.print(json.toString());
    }
    /**
     * 根据科室获取医生
     * public List<Doctor> getDocByDept(Integer hId, Integer hDept)
     */
    @RequestMapping("getHospdeptRoom")
    public void getDocByDept(Integer hId,String hDept,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<Doctor> list = registerService.getDocByDept(hId,hDept);
        Iterator<Doctor> iterator = list.iterator();
        while(iterator.hasNext()){
            Doctor doctor = iterator.next();
            json.put("doctor"+i,doctor);
            i++;
        }
        out.print(json.toString());
    }

    /**
     * 获取某个医生最近时间段的appointmnet(未完成，利用分页进行)
     */
    @RequestMapping("queryDoc")
    public void queryDoc(Integer dId,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<Appointment> list = registerService.queryDoc(dId);
        Iterator<Appointment> iterator = list.iterator();
        while(iterator.hasNext()){
            Appointment appointment = iterator.next();
            json.put("appointment"+i,appointment);
            i++;
        }
        out.print(json.toString());
    }

    /**
     * 根据科别查所有医生信息情况
     *  List<Appointment> queryDept(String hDept)
     */
    @RequestMapping("queryDept")
    public void queryDept(String hDept,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<Appointment> list = registerService.queryDept(hDept);
        Iterator<Appointment> iterator = list.iterator();
        while(iterator.hasNext()){
            Appointment appointment = iterator.next();
            json.put("appointment"+i,appointment);
            i++;
        }
        out.print(json.toString());
    }

    /**
     * 用户进行一次预约
     *
     */
    @RequestMapping("register")
    public void register(Registeration registeration,HttpServletResponse response) throws IOException {
        boolean isSuccess = false;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        //Integer dId, Date apTime, String apType, Integer apMax, Integer apRemain
        Appointment appointment = new Appointment(registeration.getDId(),registeration.getApTime(),registeration.getAType(),null,null);
        Underline underline = new Underline();
        if(appointment.getApType() == "online"){
            isSuccess=registerService.updateAppointment(appointment,false);
            isSuccess=registerService.insertIntoRegisteration(registeration);
        }else{
            isSuccess=registerService.updateAppointment(appointment,false);
            isSuccess=registerService.insertIntoUnderline(underline);
        }
        json.put("isSuccess",isSuccess);
        out.print(json.toString());
    }


    /**
     * 用户取消预约
     */
    @RequestMapping("cancel")
    public void cancel(Registeration registeration,HttpServletResponse response) throws IOException {
        boolean isSuccess = false;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        Appointment appointment = new Appointment(registeration.getDId(),registeration.getApTime(),registeration.getAType(),null,null);
        if(appointment.getApType() == "online"){
            isSuccess=registerService.deleteUnderline(registeration.getUId());
            isSuccess=registerService.updateAppointment(appointment,true);
        }else{
            isSuccess=registerService.deleteRegisteration(registeration.getUId());
            isSuccess=registerService.updateAppointment(appointment,true);
        }
        json.put("isSuccess",isSuccess);
        out.print(json.toString());
    }

    /**
     * 用户完成检查
     */
    @RequestMapping("changeState")
    public void changeState(Integer id,HttpServletResponse response) throws IOException {
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        boolean isSuccess = registerService.changeState(id);
        json.put("isSuccess",isSuccess);
        out.print(json.toString());

    }

    /**
     * 用户查看自己的预约
     */
    @RequestMapping("showRegisteration")
    public void showRegisteration(Integer uId,HttpServletResponse response) throws IOException {
        int i=0;
        PrintWriter out = null;
        out = response.getWriter();
        JSONObject json = new JSONObject();
        List<Registeration> listRegisteration = registerService.queryRegisteration(uId);
        List<Underline> listUnderline = registerService.queryUnderline(uId);
        Iterator iterator = listRegisteration.iterator();
        while (iterator.hasNext()){
            Registeration registeration = (Registeration) iterator.next();
            json.put("registeration"+i,registeration);
            i++;
        }
        iterator = listUnderline.iterator();
        while (iterator.hasNext()){
            Underline underline = (Underline) iterator.next();
            json.put("underline"+i,underline);
            i++;
        }
        out.print(json.toString());
    }
}

