package com.cggw.register.dao;

import com.cggw.register.domain.Hospital;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cgw on 2018/7/24.
 */
@Repository
public interface HospitalMapper {

    //推荐医院，属性为医院名称，医院图片，地址，分数.(待修改，待测试）
    List<Hospital> getHospByCommand();

    //根据医院列出所有科室
    List<String> getHospdept(Integer hId);

    //根据医院、科别列出所有科室
    List<String> getHospdeptRoom(@Param("hId") Integer hId,@Param("hDept") String hDept);



}
