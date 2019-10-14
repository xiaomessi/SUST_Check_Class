package com.fehead.dao;

import java.util.List;
import com.fehead.bean.*;
import org.apache.ibatis.annotations.Param;


/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface SelectClassroomsMapper {

    /**
     * 查询某层楼的所有教室，默认usage（使用情况为false）
     */
    List<ClassroomSelectBean> selectAllClassroomsFromLocation(@Param("build") String build,
                                                              @Param("buildumber") String buildumber,
                                                              @Param("buildlevel") int buildlevel);


    List<ClassroomSelectBean> selectClassroom(@Param("build") String build,
                                        @Param("buildnumber") String buildnumber,
                                        @Param("buildlevel") int buildlevel,
                                        @Param("week") int week,
                                        @Param("day") int day,
                                        @Param("time") int time);

}
