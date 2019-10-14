package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface CommonError {

    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
