package com.springboot.sale.enums;

public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_NOT_ENOUGH(11,"库存不足"),

    ORDER_MASTER_NOT_EXIST(12,"订单主表信息不存在"),

    ORDER_DETAIL_NOT_EXIST(13,"订单详情信息不存在"),

    ORDER_STATUS_NOT_RIGHT(14,"订单已取消或者已完成"),

    UPDATE_ORDER_STATUS_FAILED(15,"更改订单状态失败"),

    PARAMETER_ERROR(16,"必填参数缺失"),

    ORDER_DETAIL_IS_EMPTY(17,"订单为空"),

    BUYER_OPENID_IS_EMPTY(18,"用户微信号为空"),

    ORDER_NOT_EXIST(19,"订单不存在"),
    ;
    private Integer code;

    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}