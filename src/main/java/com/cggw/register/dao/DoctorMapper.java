package com.cggw.register.dao;

import com.cggw.register.domain.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cgw on 2018/7/24.
 */
@Repository
public interface DoctorMapper {

    //搜索科室搜索医生（列出医生的头像、职称）
    List<Doctor> getDocByDept(Integer hId,String hDept);
}
