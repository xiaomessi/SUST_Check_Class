package com.fehead.service;

import com.fehead.error.BussinessException;
import com.fehead.service.model.ClassroomModel;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface SelectClassroomsService {

    List<ClassroomModel> selectClassrooms(String build, String buildnumber, int buildlevel,
                                          int week, int day, int time) throws BussinessException;
}
