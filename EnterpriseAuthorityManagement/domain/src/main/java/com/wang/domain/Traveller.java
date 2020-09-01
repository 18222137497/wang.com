package com.wang.domain;

import java.io.Serializable;

/**
 * 游客表
 */
public class Traveller implements Serializable {
    private String id;//uuid
    private String name;//姓名
    private String sex;//性别
    private String phoneNum;//电话
    private Integer credentialsType;//证件类型
    private String credentialsTypeStr;
    private String credentialsNum;//证件号
    private Integer travellerType;//旅客类型
    private String travellerTypeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeStr() {
        switch (credentialsType){
            case 0:
                return "身份证";
            case 1:
                return "护照";
            default:
                return "军官证";
        }
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
    }

    public String getTravellerTypeStr() {
        return travellerType==0?"成人":"儿童";
    }

}
