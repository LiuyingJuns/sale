package com.springboot.sale.enums;

public enum ProductEnum {
    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductEnum(Integer code, String msg) {
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

    public static ProductEnum getProductEnum(int integer) {
        switch (integer) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            default:
                return null;
        }

    }
}