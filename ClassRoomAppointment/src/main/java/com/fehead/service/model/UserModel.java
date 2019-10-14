package com.fehead.service.model;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
public class UserModel {

    private String organization;
    private String name;
    private String telphone;
    private String description;
    private String build;
    private String buildnumber;
    private Integer buildlevel;
    private Integer day;
    private Integer time;
    private Integer week;
    private Integer classroom;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

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

    @Override
    public String toString() {
        return "UserModel{" +
                "organization='" + organization + '\'' +
                ", name='" + name + '\'' +
                ", telphone='" + telphone + '\'' +
                ", description='" + description + '\'' +
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
