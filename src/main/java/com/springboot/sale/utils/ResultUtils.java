package com.springboot.sale.utils;

import com.springboot.sale.vo.ResultVO;

public class ResultUtils {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0000);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return null;
    }

    public static ResultVO failed(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1111);
        resultVO.setMsg("failed");
        resultVO.setData(object);
        return resultVO;

    }
}
