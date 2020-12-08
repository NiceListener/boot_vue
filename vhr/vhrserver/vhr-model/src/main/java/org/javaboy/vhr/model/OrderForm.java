package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: No Description
 * @Changelog: Here is the change log of the file
 * @Author: lisx
 * @CreateTime: 2020/12/4 16:53
 */
public class OrderForm implements Serializable {

    private Long id;
    private String agentCode; //归属的业务员
    private String nickName; //微信昵称
    private String WeChatID; //微信号
    private String shop; //商品
    private String orderType; //下单方式
    private String orderNumber; //订单编号
    private String supplier; //供货商
    private String agencyPrice; //代理价
    private String sellingPrice; //售价
    private String payment; //客户实付
    private String differencesPrice; //需返差价
    private String courierNumber; //快递单号
    private String receivingName; //收货姓名
    private String receivingPhone; //收货电话
    private String receivingAddress; //收货地址
    private String largess; //赠品详情
    private String largessCourierNumber; //赠品快递单号
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Shanghai")
    private Date orderTime; //下单时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWeChatID() {
        return WeChatID;
    }

    public void setWeChatID(String weChatID) {
        WeChatID = weChatID;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAgencyPrice() {
        return agencyPrice;
    }

    public void setAgencyPrice(String agencyPrice) {
        this.agencyPrice = agencyPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDifferencesPrice() {
        return differencesPrice;
    }

    public void setDifferencesPrice(String differencesPrice) {
        this.differencesPrice = differencesPrice;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getLargess() {
        return largess;
    }

    public void setLargess(String largess) {
        this.largess = largess;
    }

    public String getLargessCourierNumber() {
        return largessCourierNumber;
    }

    public void setLargessCourierNumber(String largessCourierNumber) {
        this.largessCourierNumber = largessCourierNumber;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}