package com.fehead.dao;

import com.fehead.service.model.UserModel;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
public interface UserMapper {

    Integer selectUser(UserModel userModel);

    void insertUser(UserModel userModel);
}
