package com.springboot.sale.dto;

import com.springboot.sale.entity.OrderDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO  {

    private String orderId;

    /**用户名称 */
    private String buyerName;

    /**用户手机号*/
    private String buyerTelphone;

    /**用户地址*/
    private String buyerAddress;

    /**用户微信号 */
    private String buyerOpenid;

    /**订单金额 */
    private BigDecimal orderAmount;

    /**订单状态  0新订单，1已完成订单，2已取消的订单,默认新订单*/
    private Integer orderStatus ;

    /**支付状态 * 0待支付，1已完成支付，2取消支付，默认0待支付 */
    private Integer payStatus ;

    private Date creatTime;

    private Date updateTime;

    private OrderDetail orderDetail;

    List<OrderDetail> orderDetailList;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTelphone() {
        return buyerTelphone;
    }

    public void setBuyerTelphone(String buyerTelphone) {
        this.buyerTelphone = buyerTelphone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }


}

