package com.springboot.sale.enums;

public enum PayStatusEnum {
    PAY_WAIT(0,"待支付"),
    PAY_SUCCESS(1,"已支付"),
    PAY_CANCEL(2,"取消支付")
    ;

    private Integer code;

    private String msg;

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

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
