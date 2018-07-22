package com.cggw.forum.test;

import com.cggw.login.dao.LoginMapper;
import com.cggw.register.dao.AppointmentMapper;
import com.cggw.register.dao.RegisterationAndUnderlineMapper;
import com.cggw.register.domain.Appointment;
import com.cggw.register.domain.Registeration;
import com.cggw.register.domain.Underline;
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
        List<Appointment> a = appointmentMapper.queryDept("儿科");
        Iterator<Appointment> iterator  = a .iterator();
        while (iterator.hasNext()){
            Appointment appointment = iterator.next();
            System.out.println(appointment);
        }
    }

    @Test
    public void testUpdateAppointment() throws ParseException {
        String date = "20180711";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse(date);
        Appointment appointment = new Appointment(1,date1,"1",0,0);
        appointmentMapper.updateAppointment(appointment,false);
        testQueryDept();
    }

  @Test
    public void testInsertIntoRegisteration(){
      //Registeration r = new Registeration(3,1,1,"1","1");
      Underline underline = new Underline(1,1,1,"1",1,"1","1","1");
     // System.out.println(registerationAndUnderlineMapper.insertIntoRegisteration(r));
      System.out.println(registerationAndUnderlineMapper.insertIntoUnderline(underline));
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
}
