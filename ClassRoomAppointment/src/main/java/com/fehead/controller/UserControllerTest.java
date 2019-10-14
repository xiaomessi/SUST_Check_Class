//package com.fehead.controller;
//
//import com.fehead.response.CommonReturnType;
//import com.fehead.service.UserService;
//import com.fehead.service.model.UserModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by xiaoaxiao on 2019/5/23
// * Description:
// */
//@RestController
//@RequestMapping(value = "/usertest")
////解决跨域问题
//@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
//public class UserControllerTest extends BaseController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private HttpServletRequest request;
//
//    /**
//     * 前端发送请求，在cookie中查找是否有已登录的社团信息，如果没有就跳到社团信息输入页面，如果有就直接跳到选择具体位置和时间的页面
//     */
//    @RequestMapping(value = "/isUserExist",method = RequestMethod.GET)
//    public CommonReturnType isUserExist() {
//        Cookie[] cookies = request.getCookies();
//        if(cookies==null&&cookies.length==0){
//            return CommonReturnType.create(0);
//        }else {
//            UserModel userModel = new UserModel();
//            //遍历cookie数组，将最初设定的cookie数组的信息取出来
//            for(Cookie cookie:cookies){
//                if(cookie.getName().equals("organization")){
//                    userModel.setOrganization(cookie.getValue());
//                }else if(cookie.getName().equals("name")){
//                    userModel.setName(cookie.getValue());
//                }else if(cookie.getName().equals("telphone")){
//                    userModel.setTelphone(cookie.getValue());
//                }
//            }
//            System.out.println(userModel);
//            return CommonReturnType.create(1);
//        }
//
//    }
//
//    /**
//     * 将user信息放在数据库和Cookie中
//     */
//    @RequestMapping(value = "/setUserAndCookie")
//    public CommonReturnType setUserAndCookie(@RequestParam("organization") String organization,
//                                 @RequestParam("name") String name,
//                                 @RequestParam("telphone")String telphone,
//                                 @RequestParam("description")String description) {
//
//            System.out.println("description:"+description);
//            UserModel user = userService.insertUser(organization,name,telphone);
//
//            //新建cookie
//            Cookie cookie_organization = new Cookie("organization",user.getOrganization());
//            Cookie cookie_name = new Cookie("name",name);
//            Cookie cookie_telphone = new Cookie("telphone",telphone);
//            Cookie cookie_description = new Cookie("description",description);
//
//            //不要设置Cookie的maxAge——这样一旦关闭浏览器，cookie就消失了，下次再用就得重新注册cookie，保证小程序中每次只有一个cookie
////            cookie_organization.setMaxAge(60*60*5);
////            cookie_name.setMaxAge(60*60*5);
////            cookie_telphone.setMaxAge(60*60*5);
////            cookie_description.setMaxAge(60*60*5);
//            //如果maxAge为负数，则表示该Cookie仅在本浏览器窗口以及本窗口打开的子窗口内有效，关闭窗口后该Cookie即失效。
//        // maxAge为负数的Cookie，为临时性Cookie，不会被持久化，不会被写到Cookie文件中。Cookie信息保存在浏览器内存中，
//        // 因此关闭浏览器该Cookie就消失了。Cookie默认的maxAge值为–1。
//
//            //设置cookie的路径——所有程序都有可以使用cookie
//            cookie_organization.setPath("/");
//            cookie_name.setPath("/");
//            cookie_telphone.setPath("/");
//            cookie_description.setPath("/");
//
//            //获取response（客户端）对象
//            HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//
//            //向response（客户端）中添加Cookie
//            response.addCookie(cookie_organization);
//            response.addCookie(cookie_name);
//            response.addCookie(cookie_telphone);
//            response.addCookie(cookie_description);
//
//            return CommonReturnType.create("登录成功");
//    }
//
//}
