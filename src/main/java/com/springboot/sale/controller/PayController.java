package com.springboot.sale.controller;

import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.enums.ResultEnum;
import com.springboot.sale.exception.SaleException;
import com.springboot.sale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @PostMapping("creat")
    public void creat(@RequestParam(name = "openId") String openId,
                      @RequestParam(name = "returnUrl") String returnUrl){

        //1.查询订单是否存在
           OrderDTO orderDTO = orderService.findOne(openId);
           if(orderDTO == null){
               throw new SaleException(ResultEnum.ORDER_NOT_EXIST);
           }
        //发起支付
    }
}
