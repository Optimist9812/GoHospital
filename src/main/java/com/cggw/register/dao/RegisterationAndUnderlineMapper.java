package com.cggw.register.dao;

import com.cggw.register.domain.Registeration;
import com.cggw.register.domain.Underline;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2018/7/15.
 */
@Repository
public interface RegisterationAndUnderlineMapper {

    //插入线上预约表
    boolean insertIntoRegisteration(@Param("registeration") Registeration registeration);

    //插入线下预约表
    boolean insertIntoUnderline(@Param("underline")Underline underline);

    //删除线下预约表
    boolean deleteUnderline(Integer rId);

    //删除线上预约表
    boolean deleteRegisteration(Integer rId);

    //根据用户id查线上预约记录数（前端显示）
    List<Registeration> queryRegisteration(Integer uId);

    //根据用户id查线下预约记录数
    List<Underline> queryUnderline(Integer id);
}
