package com.fehead.response;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:通过构建Controller层的viewobject给前端返回数据（不好，太片面）
 *             所以需要构建给前端返回的标准返回格式state+object
 */
public class CommonReturnType {

    //表明对应请求的返回处理结果"success"或"fail"
    private String status;

    //若state=success，则data内返回前端需要的json数据
    //若state=fail，则data内使用通用的错误码格式
    private Object data;

    //通过方法重载的方式定义返回不同结果的内容
    //将这两个方法定义为static，不用new就可以使用
    /**
     * 默认只给该方法传一个Object参数，该方法会调用另一个同名方法，并将默认的state设置为success
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    /**
     * 既可以接收上面同名方法的请求（默认state="success"），也可以由外部调用，state就的设定就由外部决定
     * @param data
     * @param status
     * @return
     */
    public static CommonReturnType create(Object data,String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(data);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
