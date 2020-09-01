package com.wang.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
public class Orders implements Serializable {
    private String id;//uuid
    private String orderNum;//订单编号
    private Date orderTime;//下单时间
    private String orderTimeStr;
    private int orderStatus;//支付状态
    private int peopleCount;//出行人数
    private Product product;//外键关联产品信息
    private List<Traveller> travellers;//外键关联旅客表
    private Member member;//外键关联会员表
    private Integer payType;//支付方式
    private String payTypeStr;
    private String orderDesc;//订单描述（其他信息）
    private String orderStatusStr;

    public String getOrderStatusStr() {
        return orderStatus==1?"已支付":"未支付";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return dateFormat.format(orderTime);
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
    switch (payType) {
        case 0:
            return "支付宝";
        case 1:
            return "微信";
        default:
            return "其他";
    }
    }


    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
