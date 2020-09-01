package com.lucky_home.domain;

import java.sql.Date;

public class Cargo {
    private int cuserid;//用户名编号
    private int cid;//产品id
    private String cname;//产品名称
    private int cnumber;//现有量
    private int callnumber;//全部库存
    private Date caddtime;//添加时间
    private String uoq;//计量单位
    private String tname;//类型名称

    public int getCuserid() {
        return cuserid;
    }

    public void setCuserid(int cuserid) {
        this.cuserid = cuserid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCnumber() {
        return cnumber;
    }

    public void setCnumber(int cnumber) {
        this.cnumber = cnumber;
    }

    public int getCallnumber() {
        return callnumber;
    }

    public void setCallnumber(int callnumber) {
        this.callnumber = callnumber;
    }

    public Date getCaddtime() {
        return caddtime;
    }

    public void setCaddtime(Date caddtime) {
        this.caddtime = caddtime;
    }

    public String getUoq() {
        return uoq;
    }

    public void setUoq(String uoq) {
        this.uoq = uoq;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "cargo{" +
                "cuserid=" + cuserid +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cnumber=" + cnumber +
                ", callnumber=" + callnumber +
                ", caddtime=" + caddtime +
                ", uoq='" + uoq + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
