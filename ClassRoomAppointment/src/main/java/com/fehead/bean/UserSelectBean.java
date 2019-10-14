package com.fehead.bean;

/**
 * Created by xiaoaxiao on 2019/5/26
 * Description:
 */
public class UserSelectBean {

    private Integer query_id;
    private String build;
    private String buildnumber;
    private Integer buildlevel;
    private Integer day;
    private Integer time;
    private Integer week;
    private Integer classroom;


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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public Integer getQuery_id() {
        return query_id;
    }

    public void setQuery_id(Integer query_id) {
        this.query_id = query_id;
    }

    @Override
    public String toString() {
        return "UserSelectBean{" +
                "query_id=" + query_id +
                ", build='" + build + '\'' +
                ", buildnumber='" + buildnumber + '\'' +
                ", buildlevel=" + buildlevel +
                ", day=" + day +
                ", time=" + time +
                ", week=" + week +
                ", classroom=" + classroom +
                '}';
    }
}
