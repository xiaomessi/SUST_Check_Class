package com.fehead.service.Impl;

import com.fehead.bean.ClassroomInsertBean;
import com.fehead.bean.ClassroomSelectBean;
import com.fehead.dao.ApplyClassroomsMapper;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.ApplyClassroomsService;
import com.fehead.service.model.ClassroomModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
@Service
public class ApplyClassroomsServiceImpl implements ApplyClassroomsService {

    //    //定义一个insertSuccess：只要有一个插入成功，则显示插入成功
    private Integer insertSuccess = 0;

    @Autowired
    private ApplyClassroomsMapper applyClassroomsMapper;


    @Override
    public List<ClassroomModel> applyClassrooms(String build, String buildnumber, int buildlevel, int week, int day, int time, String[] classrooms,
                                                String organization,String name,String telphone,String description) throws BussinessException {
        if (build == null || buildnumber == null) {
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //创建返回Classroom的集合
        List<ClassroomModel> classroomModels = new ArrayList<>();

        for (String classroom : classrooms) {
            //使用创建一个classroomInsertBean的方式：MySQL获得数据库存储过程中的OUT返回的值
            ClassroomInsertBean classroomInsertBean = new ClassroomInsertBean();
            classroomInsertBean.setBuild(build);
            classroomInsertBean.setBuildlevel(buildlevel);
            classroomInsertBean.setBuildnumber(buildnumber);
            classroomInsertBean.setClassroom(Integer.valueOf(classroom));
            classroomInsertBean.setDay(day);
            classroomInsertBean.setTime(time);
            classroomInsertBean.setWeek(week);
            classroomInsertBean.setOrganization(organization);
            classroomInsertBean.setName(name);
            classroomInsertBean.setTelphone(telphone);
            classroomInsertBean.setDescription(description);

            applyClassroomsMapper.insertAllTables(classroomInsertBean);

//            //将classroomInsertBean赋值给returnClassroomInsertBean，否则returnClassroomInsertBean是null
//            BeanUtils.copyProperties(classroomInsertBean,returnClassroomInsertBean);

            ClassroomModel classroomModel = convertClassroomModelFromClassroomInsertBean(classroomInsertBean);
            classroomModels.add(classroomModel);
//            if(classroomInsertBean.getInsert_count()>0){
//                insertSuccess = 1;
//            }
        }
//        if (insertSuccess == 0) {
//            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "重复插入");
//        }

        return classroomModels;
    }

    private ClassroomModel convertClassroomModelFromClassroomInsertBean(ClassroomInsertBean classroomInsertBean) throws BussinessException {
        if(classroomInsertBean==null){
            return null;
        }

        ClassroomModel classroomModel = new ClassroomModel();
        BeanUtils.copyProperties(classroomInsertBean,classroomModel);
        //int->String
        classroomModel.setClassroom(String.valueOf(classroomInsertBean.getClassroom()));

        if(classroomInsertBean.getInsert_count()==0){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"重复插入");
        }else {
            classroomModel.setStatus(true);
        }

        return classroomModel;
    }
}
