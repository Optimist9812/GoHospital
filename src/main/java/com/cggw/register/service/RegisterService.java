package com.cggw.register.service;

import com.cggw.register.dao.AppointmentMapper;
import com.cggw.register.dao.DoctorMapper;
import com.cggw.register.dao.HospitalMapper;
import com.cggw.register.dao.RegisterationAndUnderlineMapper;
import com.cggw.register.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cgw on 2018/7/24.
 */
@Service("registerService")
public class RegisterService {

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private RegisterationAndUnderlineMapper registerationAndUnderlineMapper;

    /**
     * 根据地理位置获取推荐医院(mapper待完成)
     *  List<Hospital> getHospByCommand();
     */
    public List<Hospital> getHospByCommand(){
        return hospitalMapper.getHospByCommand();
    }

    /**
     * 根据医院获取科室
     *  List<String> getHospdept(Integer hId);
     */
    public List<String> getHospdept(Integer hId){
        return hospitalMapper.getHospdept(hId);
    }

    /**
     * 根据医院、科室获取科别
     * List<String> getHospdeptRoom(Integer hId,Integer hDept);
     */
    public List<String> getHospdeptRoom(Integer hId,String hDept){
        return hospitalMapper.getHospdeptRoom(hId,hDept);
    }

    /**
     * 根据科室获取医生
     */
    public List<Doctor> getDocByDept(Integer hId, String hDept,String hRoom){
        return doctorMapper.getDocByDept(hId,hDept,hRoom);
    }

    /**
     *根据医生id查询医生预约情况（前端展示）
     List<Appointment> queryDoc(Integer dId);
     */
    public List<Appointment> queryDoc(Integer dId){
        return appointmentMapper.queryDoc(dId);
    }

    /**
     * 根据科别查所有医生信息情况
     List<Appointment> queryDept(String hDept);
     */
    public List<Appointment> queryDept(String hId,String hDept){
        return appointmentMapper.queryDept(hId,hDept);
    }
    /**
     *
     * 更新医生预约表,剩余量增加或减少(增加用true,减少用false)
     * boolean updateAppointment(@Param("appointment") Appointment appointment, @Param("flag") boolean flag);
     */
    public boolean updateAppointment(Appointment appointment, boolean flag){
        return appointmentMapper.updateAppointment(appointment,flag);
        //return false;
    }

    /**
     * //插入线上预约表
     *boolean insertIntoRegisteration(@Param("registeration") Registeration registeration);
     *
     */
    public boolean insertIntoRegisteration( Registeration registeration){
        return registerationAndUnderlineMapper.insertIntoRegisteration(registeration);
    }

     /**
     *插入线下预约表
     *boolean insertIntoUnderline(@Param("underline")Underline underline);
     */
     public boolean insertIntoUnderline(Underline underline){
         return registerationAndUnderlineMapper.insertIntoUnderline(underline);
     }

     /**
     *
     *    删除线下预约表
     *  boolean deleteUnderline(Integer rId);
     */
     public boolean deleteUnderline(Integer rId){
         return registerationAndUnderlineMapper.deleteUnderline(rId);
     }
    /**
     *
     *删除线上预约表
     *boolean deleteRegisteration(Integer rId);
     *
     */
    public boolean deleteRegisteration(Integer rId){
        return registerationAndUnderlineMapper.deleteRegisteration(rId);
    }
    /**
     *
     * 根据用户id查线上预约记录数（前端显示）
     *List<Registeration> queryRegisteration(Integer uId);
     *
     */
    public List<Registeration> queryRegisteration(Integer uId){
        return registerationAndUnderlineMapper.queryRegisteration(uId);
    }
    /**
     *
     * 根据用户id查线下预约记录数
     *List<Underline> queryUnderline(Integer id);
     *
     */
    public List<Underline> queryUnderline(Integer id){
        return registerationAndUnderlineMapper.queryUnderline(id);
    }

    /**
     * 完成预约
     */
    public boolean changeState(Integer id){
        return registerationAndUnderlineMapper.changeState(id);
    }

    //根据医生id进行搜索医生
    public Doctor getDocById(Integer dId){
        return doctorMapper.getDocById(dId);
    }
}
