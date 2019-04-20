package com.springboot.sale.exception;

import com.springboot.sale.enums.ResultEnum;

public class SaleException extends RuntimeException
{
    private Integer code;


    public SaleException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SaleException(Integer code,String msg) {
        super(msg);
        this.code = code;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
