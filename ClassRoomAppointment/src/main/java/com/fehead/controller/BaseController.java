package com.fehead.controller;

import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public class BaseController {

    //声明一个常量用于@RequestMapping中的consumes的返回
    public static final String CONTENT_TYPE_FORWARD="application/x-www-form-urlencoded";

    //定义exceptionHandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    //设置返回结果为200，不会返回其他结果
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request,Exception ex){

        Map<String,Object> responseData = new HashMap<String, Object>();
        //ex对象是否是BussinessException的一个实例（ex可能报的不是BussinessException异常）
        if(ex instanceof BussinessException){
            BussinessException bussinessException = (BussinessException)ex;
            responseData.put("errCode",bussinessException.getErrCode());
            responseData.put("errMsg",bussinessException.getErrMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }


        return CommonReturnType.create(responseData,"fail");
    }
}

