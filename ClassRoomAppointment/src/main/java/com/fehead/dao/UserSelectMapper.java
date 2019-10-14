package com.fehead.dao;

import com.fehead.bean.UserBean;
import com.fehead.bean.UserSelectBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/26
 * Description:
 */
public interface UserSelectMapper {


    //直接设置为void，查询出来的数据就通过MyBatis自动赋值给对应的属性了
    void selectUserMessage(UserSelectBean userSelectBean);

    List<Integer> selectClassroomsByUser(UserBean userBean);
}
