package com.fehead.bean;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public class ClassroomSelectBean {

    private Integer classroom;

    private Boolean usage = false;

    public Boolean getUsage() {
        return usage;
    }

    public void setUsage(Boolean usage) {
        this.usage = usage;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }

        if(obj==null){
            return false;
        }

        if(getClass()!=obj.getClass()){
            return false;
        }

        ClassroomSelectBean other = (ClassroomSelectBean)obj;

        return classroom.equals(other.classroom) &&usage.equals(other.usage);
    }
}
