package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public class BussinessException extends Exception implements CommonError {

    private CommonError commonError;

    //通过设置两个构造器来应对两种情况（直接抛出错误  抛出错误+修改错误）
    public BussinessException(CommonError commonError){
        //对应Exception自身会有一些初始化的机制
        super();
        this.commonError = commonError;
    }

    public BussinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
