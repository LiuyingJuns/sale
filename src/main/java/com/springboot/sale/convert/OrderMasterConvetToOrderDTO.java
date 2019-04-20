package com.springboot.sale.convert;

import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterConvetToOrderDTO {
    /**ordermaster转换为orderDTO */
    public OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    /**List<OrderMaster>转换为List<OrderDTO>*/
    public List<OrderDTO> convert(List<OrderMaster> orderMasterList){
       List<OrderDTO> orderDTOList = new ArrayList<>();
       orderDTOList = orderMasterList.stream().map(orderMaster ->
                this.convert(orderMaster)
                ).collect(Collectors.toList());
       return orderDTOList;
    }
}
