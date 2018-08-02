package com.cggw.register.test;

import com.cggw.register.dao.AppointmentMapper;
import com.cggw.register.dao.DoctorMapper;
import com.cggw.register.dao.HospitalMapper;
import com.cggw.register.dao.RegisterationAndUnderlineMapper;
import com.cggw.register.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 2018/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    RegisterationAndUnderlineMapper registerationAndUnderlineMapper;

    @Autowired
    DoctorMapper doctorMapper ;

    @Autowired
    HospitalMapper hospitalMapper;

    @Test
    public void testQueryDoc(){
        List<Appointment> a = appointmentMapper.queryDoc(1);
        Iterator<Appointment> iterator  = a .iterator();
        while (iterator.hasNext()){
            Appointment appointment = iterator.next();
            System.out.println(appointment);
        }

    }

    @Test
    public void testQueryDept(){
        List<Appointment> a = appointmentMapper.queryDept("1","儿科");
        Iterator<Appointment> iterator  = a .iterator();
        while (iterator.hasNext()){
            Appointment appointment = iterator.next();
            System.out.println(appointment);
        }
    }

    /**
     * 错误,time问题,必须加上8：00
     * @throws ParseException
     */
    @Test
    public void testUpdateAppointment() throws ParseException {
        String date = "2018-07-11 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(date);
        System.out.println(date1.toString());
        Appointment appointment = new Appointment(1,date1,"1",null,null);
        appointmentMapper.updateAppointment(appointment,false);
       // System.out.println(appointmentMapper.updateAppointment(appointment,true));
        //appointmentMapper.updateAppointment();
        testQueryDept();
    }

        @Test
    public void testInsertIntoRegisteration() throws ParseException {
        String date = "20180710";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse(date);
        Registeration r = new Registeration(12,1,1,"1","1",date1);
        //Underline underline = new Underline(10,1,1,"1",1,"1","1","1");
        System.out.println(registerationAndUnderlineMapper.insertIntoRegisteration(r));
        //System.out.println(registerationAndUnderlineMapper.insertIntoUnderline(underline));
    }

    @Test
    public void testDeleteRegisteration(){
        System.out.println(registerationAndUnderlineMapper.deleteRegisteration(3));
    }

    @Test
    public void testDeleteUnderline(){
        System.out.println(registerationAndUnderlineMapper.deleteUnderline(3));
    }

    @Test
    public void testQueryRegisteration(){
        List<Registeration> list = registerationAndUnderlineMapper.queryRegisteration(1);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            Registeration registeration = (Registeration) iterator.next();
            System.out.println(registeration);

        }
    }


    @Test
    public void testQueryUnderline(){
        List<Underline> list = registerationAndUnderlineMapper.queryUnderline(1);
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            Underline underline = (Underline) iterator.next();
            System.out.println(underline);

        }
    }

    //搜索科室搜索医生（列出医生的头像、职称）
    //List<Doctor> getDocByDept(Integer hId, String hDept);
    @Test
    public void testGetDocByDept(){
        List<Doctor> list = doctorMapper.getDocByDept(1, "儿科");
        Iterator iterator =list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //推荐医院，属性为医院名称，医院图片，地址，分数.(待修改，待测试）
    //List<Hospital> getHospByCommand();
    @Test
    public void testGetHospByCommand(){
        List<Hospital> list = hospitalMapper.getHospByCommand();
        Iterator iterator =list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //根据医院列出所有科室
    //List<String> getHospdept(Integer hId);
    @Test
    public void testGetHospdept(){
        List<String> list = hospitalMapper.getHospdept(1);
        Iterator iterator =list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //根据医院、科别列出所有科室
    //List<String> getHospdeptRoom(Integer hId,String hDept);
    @Test
    public void testGetHospdeptRoom(){
        List<String> list = hospitalMapper.getHospdeptRoom(1,"1");
        Iterator iterator =list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
