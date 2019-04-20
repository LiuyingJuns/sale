package com.springboot.sale.service;

import com.springboot.sale.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**创建订单 */
   public OrderDTO creatOrder(OrderDTO orderDTO);

    /** 查询订单详情*/
    public OrderDTO findOne(String openid);

    /** 查询订单列表*/
    Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable);

    /**取消订单 */
   public OrderDTO cancelOrder(OrderDTO orderDTO);

    /** 支付订单*/

    /** */

}
