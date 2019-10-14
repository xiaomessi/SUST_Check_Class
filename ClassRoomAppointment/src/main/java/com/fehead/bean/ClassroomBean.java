package com.fehead.bean;

/**
 * Created by xiaoaxiao on 2019/5/28
 * Description:
 */
public class ClassroomBean {

    private String build;
    private String buildnumber;
    private Integer buildlevel;
    private Integer classroom;
    private Integer week;
    private Integer day;
    private Integer time;

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(String buildnumber) {
        this.buildnumber = buildnumber;
    }

    public Integer getBuildlevel() {
        return buildlevel;
    }

    public void setBuildlevel(Integer buildlevel) {
        this.buildlevel = buildlevel;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ClassroomBean{" +
                "build='" + build + '\'' +
                ", buildnumber='" + buildnumber + '\'' +
                ", buildlevel=" + buildlevel +
                ", classroom=" + classroom +
                ", week=" + week +
                ", day=" + day +
                ", time=" + time +
                '}';
    }
}
