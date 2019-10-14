package com.fehead.controller;

import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.controller.viewobject.*;
import com.fehead.service.*;
import com.fehead.service.model.ClassroomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
@Controller("select")
@RequestMapping("/select")
//解决跨域问题
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class SelectClassroomsController extends BaseController{

    @Autowired
    private SelectClassroomsService selectClassroomsService;

    @RequestMapping(value = "/selectClassrooms",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType selectClassrooms(@RequestParam("build") String build,
                                             @RequestParam("buildnumber") String buildnumber,
                                             @RequestParam("buildlevel") int buildlevel,
                                             @RequestParam("week") int week,
                                             @RequestParam("day") int day,
                                             @RequestParam("time") int time) throws BussinessException {

        long startTime=System.currentTimeMillis();  //获取开始时间

        if(build==null||buildnumber==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ClassroomModel> classroomModels = selectClassroomsService.selectClassrooms(build,buildnumber,buildlevel,week,day,time);

        List<ClassroomSelectVO> classroomSelectVOS = convertClassroomSelectVOFromclassroomModel(classroomModels);

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("startTime="+startTime+";endTime="+endTime+";runtime:"+(endTime-startTime)+"ms");
        return CommonReturnType.create(classroomSelectVOS);
    }

    private List<ClassroomSelectVO> convertClassroomSelectVOFromclassroomModel(List<ClassroomModel> classroomModels){
        if(classroomModels==null||classroomModels.size()==0){
            return null;
        }
        List<ClassroomSelectVO> classroomSelectVOS = new ArrayList<ClassroomSelectVO>();

        for(ClassroomModel classroomModel:classroomModels){
            ClassroomSelectVO classroomSelectVO = new ClassroomSelectVO();
            //将最终呈现的结果返回给前端即可
            if(classroomModel.getClassroom().equals("10")){
                classroomSelectVO.setClassroom(classroomModel.getBuildlevel()+classroomModel.getClassroom());
            }else {
                classroomSelectVO.setClassroom(classroomModel.getBuildlevel()+"0"+classroomModel.getClassroom());
            }

            classroomSelectVO.setUsage(classroomModel.getStatus());
            classroomSelectVOS.add(classroomSelectVO);
        }

        return classroomSelectVOS;

    }

}
