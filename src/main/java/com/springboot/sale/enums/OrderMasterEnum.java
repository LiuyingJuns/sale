package com.springboot.sale.enums;

public enum OrderMasterEnum {
        NEW_ORDER(0,"新的订单"),
        FINUSHED_ORDER(1,"已完成的订单"),
        CANCER_ORDER(2,"已取消的订单")
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

    OrderMasterEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

 OrderMasterEnum getNewOrderMasterEnum(Integer enums){
        switch (enums){
            case 0:
               return NEW_ORDER;
            case 1:
                return FINUSHED_ORDER;
            case 2:
               return CANCER_ORDER;
               default:
                   return null;
        }
    }
}
