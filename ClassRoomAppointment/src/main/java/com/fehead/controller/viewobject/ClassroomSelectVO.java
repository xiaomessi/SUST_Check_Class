package com.fehead.controller.viewobject;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public class ClassroomSelectVO {

    //最终显示给页面VO中的教室就需要带上楼层了
    private String classroom;
    private Boolean usage;

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Boolean getUsage() {
        return usage;
    }

    public void setUsage(Boolean usage) {
        this.usage = usage;
    }
}
