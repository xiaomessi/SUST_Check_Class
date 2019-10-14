package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public enum EmBusinessError implements CommonError {

    //设置错误类型就可以
    //通用的错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误")
    ;

    private int errCode;
    private String errMsg;

    //外部不会调用这个构造器的——Enum的一种特性
    // eg：当外部调用EmBusinessError.USER_NOT_EXIST时，在该类中自动调用该构造器。
    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }}
