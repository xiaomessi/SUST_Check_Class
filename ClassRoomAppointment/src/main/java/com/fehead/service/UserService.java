package com.fehead.service;


import com.fehead.service.model.UserModel;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
public interface UserService {

    void insertUser(String organization,String name,String telphone);

    List<UserModel> getUser(String organization, String name, String telphone, String description);
}
