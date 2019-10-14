package com.fehead.controller;

import com.fehead.error.BussinessException;
import com.fehead.response.CommonReturnType;
import com.fehead.service.ApplyClassroomsService;
import com.fehead.service.model.ClassroomModel;
import com.fehead.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
@Controller("apply")
@RequestMapping("/apply")
//解决跨域问题
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ApplyClassroomsController extends BaseController {

    @Autowired
    private ApplyClassroomsService applyClassroomsService;

    //获取cookie信息
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/applyClassrooms",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public CommonReturnType applyClassrooms(@RequestParam("build") String build,
                                            @RequestParam("buildnumber") String buildnumber,
                                            @RequestParam("buildlevel") int buildlevel,
                                            @RequestParam("week") int week,
                                            @RequestParam("day") int day,
                                            @RequestParam("time") int time,
                                            @RequestParam("classroom") String[] classrooms,
                                            @RequestParam("organization") String organization,
                                            @RequestParam("name") String name,
                                            @RequestParam("telphone")String telphone,
                                            @RequestParam("description")String description) throws BussinessException {

        long startTime = System.currentTimeMillis();//获取开始时间

//        Integer[] arr = new Integer[100];
//        System.out.println("build"+build+",buildnumber"+buildnumber+",buildlevel"+buildlevel+",week"+week+",day"+day+",time"+time+",classroom"+classrooms[0]);
        // 将"11"]  ["8"  "9"  "10"   转换为正确的格式->8,9,10,11

        //本机测设将这段先注释掉
        if(classrooms.length==1){
            classrooms[classrooms.length-1] = classrooms[0].substring(2,4);
        }else {
            for(int i=0;i<classrooms.length;i++){
                if(i==0){
                    classrooms[i] = classrooms[i].substring(2,4);
                }else{
                    classrooms[i] = classrooms[i].substring(1,3);
                }
//            arr[i] = Integer.valueOf(classrooms[i]);
            }
        }

//    //获取用户信息
//        UserModel userModel = new UserModel();

//        //获取cookie数组
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null){
//            //遍历cookie数组，将最初设定的cookie数组的信息取出来
//            for(Cookie cookie:cookies){
//                if(cookie.getName().equals("organization")){
//                    userModel.setOrganization(cookie.getValue());
//                }else if(cookie.getName().equals("name")){
//                    userModel.setName(cookie.getValue());
//                }else if(cookie.getName().equals("telphone")){
//                    userModel.setTelphone(cookie.getValue());
//                }else if(cookie.getName().equals("description")){
//                    userModel.setDescription(cookie.getValue());
//                }
//            }
//        }
//        System.out.println(userModel);


//        System.out.println("build"+build+",buildnumber"+buildnumber+",buildlevel"+buildlevel+",week"+week+",day"+day+",time"+time+",classroom[0]:"+classrooms[0]);

        //将数据插入到对应query表中
        List<ClassroomModel> classroomModels =  applyClassroomsService.applyClassrooms(build,buildnumber,buildlevel,week,day,time,classrooms,organization,name,telphone,description);

        //将社团信息与教室预约信息插入到总表中，实现绑定

        long endTime = System.currentTimeMillis();//获取结束时间
        System.out.println("startTime="+startTime+";endTime="+endTime+";runtime:"+(endTime-startTime)+"ms");

        return CommonReturnType.create(classroomModels);
    }

}
