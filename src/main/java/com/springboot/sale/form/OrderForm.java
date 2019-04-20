package com.springboot.sale.form;

import javax.validation.constraints.NotEmpty;

public class OrderForm {
    @NotEmpty(message = "用户名称不能为空")
    private String buyerName;

    @NotEmpty(message = "用户手机号不能为空")
    private String buyerPhone;

    @NotEmpty(message = "用户地址不能为空")
    private String buyerAddress;

    @NotEmpty(message = "用户微信号不能为空")
    private String buyerOpenid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
