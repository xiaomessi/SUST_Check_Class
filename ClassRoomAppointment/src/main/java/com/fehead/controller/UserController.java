package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/26
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
//解决跨域问题
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 将user信息放在数据库中
     */
    @RequestMapping(value = "/setUserAndCookie")
    public CommonReturnType setUserAndCookie(@RequestParam("organization") String organization,
                                             @RequestParam("name") String name,
                                             @RequestParam("telphone")String telphone,
                                             @RequestParam("description")String description) {

        System.out.println("description:"+description);

        userService.insertUser(organization,name,telphone);

        return CommonReturnType.create("社团信息验证成功");
    }

    /**
     * 获取社团占用教室的信息
     */
	@RequestMapping(value = "/getUserMessage")
    public CommonReturnType getUser(@RequestParam("organization") String organization,
                                    @RequestParam("name") String name,
                                    @RequestParam("telphone")String telphone,
                                    @RequestParam("description")String description){

        List<UserModel> userModels = userService.getUser(organization,name,telphone,description);

        return CommonReturnType.create(userModels);
    }
}
