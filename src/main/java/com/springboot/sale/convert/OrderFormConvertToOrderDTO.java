package com.springboot.sale.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.entity.OrderDetail;
import com.springboot.sale.enums.ResultEnum;
import com.springboot.sale.exception.SaleException;
import com.springboot.sale.form.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderFormConvertToOrderDTO {
    public static final Logger logger = LoggerFactory.getLogger(OrderFormConvertToOrderDTO.class);
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getBuyerName());
        orderDTO.setBuyerTelphone(orderForm.getBuyerPhone());
        orderDTO.setBuyerAddress(orderForm.getBuyerAddress());
        orderDTO.setBuyerOpenid(orderForm.getBuyerOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            //orderForm.items转换为List<OrderDetail>
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){
            }.getType());
        }catch (Exception e){
            logger.error("重要参数缺失"+orderForm.getItems());
            throw new SaleException(ResultEnum.PARAMETER_ERROR);
        }

       orderDTO.setOrderDetailList(orderDetailList);
       return orderDTO;
    }
}
