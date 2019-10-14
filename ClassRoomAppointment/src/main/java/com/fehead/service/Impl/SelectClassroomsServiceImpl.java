package com.fehead.service.Impl;

import com.fehead.dao.*;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.*;
import com.fehead.service.model.*;
import com.fehead.bean.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
@Service
public class SelectClassroomsServiceImpl implements SelectClassroomsService {

    @Autowired
    private SelectClassroomsMapper selectClassroomsMapper;

    /**
     * 获取某层楼中所有的教室以及这些教室是否被占用的信息
     */
    @Override
    public List<ClassroomModel> selectClassrooms(String build, String buildnumber, int buildlevel,int week,int day,int time) throws BussinessException {

        if(build==null||buildnumber==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ClassroomSelectBean> classroomSelectBeans = selectClassroomsMapper.selectAllClassroomsFromLocation(build,buildnumber,buildlevel);

        if(classroomSelectBeans.size()==0){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"查询失败，位置错误");
        }

        List<ClassroomSelectBean> selectedBeans = selectClassroomsMapper.selectClassroom(build,buildnumber,buildlevel,week,day,time);

        //使用Collection.sort的排序算法，按照元素的classroom属性的升序对集合进行排序
        Collections.sort(classroomSelectBeans, new Comparator<ClassroomSelectBean>() {
            @Override
            public int compare(ClassroomSelectBean o1, ClassroomSelectBean o2) {
                return o1.getClassroom() - o2.getClassroom();
            }
        });

        //创建一个List<Model>
        List<ClassroomModel> classroomModels = new ArrayList<ClassroomModel>();

        //因为classroomSelectBeans和selectedBeans存储的元素内容可能会相等，但实际存储绝对不相等
        //所以需要重写ClassroomSelectBean的equals方法，只判断字段内容是否相等即可（不需要hashCode也相同）
        //将修改完的dataObject->Model
        for(ClassroomSelectBean c:classroomSelectBeans){
            if(selectedBeans.contains(c)){
                c.setUsage(true);
            }
            ClassroomModel classroomModel = convertClassroomModelFromDataObject(c);
            classroomModel.setBuildlevel(buildlevel);
            classroomModel.setBuildnumber(buildnumber);
            classroomModel.setBuild(build);
            classroomModel.setWeek(week);
            classroomModel.setDay(day);
            classroomModel.setTime(time);
            classroomModels.add(classroomModel);
        }
        return classroomModels;


//        //因为classroomSelectBeans和selectedBeans存储的元素内容可能会相等，但实际存储绝对不相等
//        //所以需要重写ClassroomSelectBean的equals方法，只判断字段内容是否相等即可（不需要hashCode也相同）
//        for(ClassroomSelectBean c:classroomSelectBeans){
//            if(selectedBeans.contains(c)){
//              c.setUsage(true);
//            }
//        }
//
//        //使用Collection.sort的排序算法，按照元素的classroom属性的升序对集合进行排序
//        Collections.sort(classroomSelectBeans, new Comparator<ClassroomSelectBean>() {
//            @Override
//            public int compare(ClassroomSelectBean o1, ClassroomSelectBean o2) {
//                return Integer.valueOf(o1.getClassroom()) - Integer.valueOf(o2.getClassroom());
//            }
//        });
//
//        //从1->x0，需要带上楼层信息
//        for(ClassroomSelectBean c:classroomSelectBeans){
//            if(c.getClassroom().equals("10")){
//                c.setClassroom(buildlevel+c.getClassroom());
//            }else {
//                c.setClassroom(buildlevel+"0"+c.getClassroom());
//            }
//        }
//
//        return classroomSelectBeans;
    }

    /**
     * dataObject->Model
     */
    private ClassroomModel convertClassroomModelFromDataObject(ClassroomSelectBean classroomSelectBean){
        if(classroomSelectBean==null){
            return null;
        }
        ClassroomModel classroomModel = new ClassroomModel();
        //将int转换为string
        classroomModel.setClassroom(String.valueOf(classroomSelectBean.getClassroom()));
        classroomModel.setStatus(classroomSelectBean.getUsage());
        return classroomModel;
    }
}
