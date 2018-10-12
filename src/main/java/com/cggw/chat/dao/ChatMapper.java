package com.cggw.chat.dao;

import com.cggw.register.domain.Doctor;

import java.util.List;

/**
 * Created by cgw on 2018/10/11.
 */
public interface ChatMapper {

    //推荐高评分，状态为空闲的某科室医生
    List<Doctor> getRecommendDocToChat(String hDept);

    //开始咨询，修改医生空闲表
    boolean changeDocStatus(int dId,int uId);

    //咨询结束
    boolean changeDocStatus2(int dId);



}
