package com.fehead.service.Impl;

import com.fehead.bean.UserBean;
import com.fehead.bean.UserSelectBean;
import com.fehead.dao.UserMapper;
import com.fehead.dao.UserSelectMapper;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSelectMapper userSelectMapper;

    @Override
    public void insertUser(String organization, String name, String telphone) {

        UserModel userModel = new UserModel();
        userModel.setOrganization(organization);
        userModel.setName(name);
        userModel.setTelphone(telphone);

        Integer result = userMapper.selectUser(userModel);

        if(result==null){
            userMapper.insertUser(userModel);
        }

    }

    @Override
    public List<UserModel> getUser(String organization, String name, String telphone, String description) {

        List<UserModel> userModels = new ArrayList<UserModel>();

        UserBean userBean = new UserBean();
        userBean.setOrganization(organization);
        userBean.setName(name);
        userBean.setTelphone(telphone);
        userBean.setDescription(description);

        //通过社团信息建立子查询获取该社团对应的教室信息
        List<Integer> query_ids = userSelectMapper.selectClassroomsByUser(userBean);

        if(query_ids.size()==0){
            return null;
        }

        //遍历该社团对应的所有教室信息，并创建UserModel，将UserModel加入List<UserModel>中返回给Controller
        for(Integer query_id:query_ids){
            System.out.println(query_id);
            UserSelectBean userSelectBean = new UserSelectBean();
            userSelectBean.setQuery_id(query_id);

            userSelectMapper.selectUserMessage(userSelectBean);

//            //将userBean中的社团信息赋值给userSelectBean
//            BeanUtils.copyProperties(userBean,userSelectBean);
            System.out.println(userSelectBean);
            UserModel userModel = convertUserModelFromUserSelectBeanAndUserBean(userSelectBean,userBean);

            userModels.add(userModel);
        }

//        UserSelectBean userSelectBean = new UserSelectBean();
//        userSelectBean.setOrganization(organization);
//        userSelectBean.setName(name);
//        userSelectBean.setTelphone(telphone);
//        userSelectBean.setDescription(description);
//
//        List<UserSelectBean> userSelectBeans = userSelectMapper.selectUserMessage(userSelectBean);
//        List<UserModel> userModels = new ArrayList<>();
//
//        for(UserSelectBean userSelectBean1:userSelectBeans){
//            UserModel userModel = convertUserModelFromUserSelectBean(userSelectBean1);
//            userModels.add(userModel);
//        }
//
//
        return userModels;
    }

    private UserModel convertUserModelFromUserSelectBeanAndUserBean(UserSelectBean userSelectBean,UserBean userBean){
        if(userSelectBean==null||userBean==null){
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userSelectBean,userModel);
        BeanUtils.copyProperties(userBean,userModel);

        return userModel;
    }
}
