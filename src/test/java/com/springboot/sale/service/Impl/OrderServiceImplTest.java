package com.springboot.sale.service.Impl;

import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.entity.OrderDetail;
import com.springboot.sale.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)

public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    public final String openid = "888888";
    public final String productId = "5656562561123";

    @Test
    public void creatOrder() {
        //TODO
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(openid);
        orderDTO.setBuyerName("秦牧");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(productId);
        orderDetail.setProductQuantity(10);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

       OrderDTO result = orderService.creatOrder(orderDTO);
       Assert.assertNotEquals(0,result);
    }

    @Test
    public void findOne(){
       OrderDTO orderDTO = orderService.findOne(openid);
       if(orderDTO == null){
           return;
       }
       Assert.assertNotEquals(0,orderDTO);
    }



    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(openid,pageRequest);
        Assert.assertNotEquals(0,orderDTOPage);
    }


    @Test
    public void cancelOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1554453575605473154");
        orderDTO.setOrderStatus(0);
        orderDTO.setBuyerOpenid("888888");
        orderDTO.setBuyerName("秦牧");
        orderDTO.setPayStatus(0);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("5656562561123");
        orderDetail.setDetailId("1554453575798362254");
        orderDetail.setOrderId(openid);
        orderDetail.setProductName("iPhoneX");
        orderDetail.setProductQuantity(10);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);

       OrderDTO orderDTO1 = orderService.cancelOrder(orderDTO);
       Assert.assertNotEquals(0,orderDTO1);
    }
}