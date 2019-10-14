package com.fehead.dao;

import com.fehead.bean.ClassroomBean;
import com.fehead.bean.ClassroomInsertBean;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
public interface ApplyClassroomsMapper {

    void insertAllTables(ClassroomInsertBean classroomInsertBean);

    Integer selectClassrooms(ClassroomBean classroomBean);

}
