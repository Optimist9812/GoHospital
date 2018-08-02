package com.cggw.register.dao;

import com.cggw.register.domain.Appointment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2018/7/15.
 */
@Repository
public interface AppointmentMapper {
    //根据医生id查询医生预约情况（前端展示）
    List<Appointment> queryDoc(Integer dId);

    //根据医院、科别查所有医生信息情况
    List<Appointment> queryDept(@Param("hId") String hId,@Param("hDept") String hDept);

    //更新医生预约表,剩余量增加或减少(增加用true,减少用false)
    boolean updateAppointment(@Param("appointment") Appointment appointment, @Param("flag") boolean flag);

}
