package com.springboot.sale.dao;

import com.springboot.sale.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

//    @Test
//    public void findOrderDetailByOrderId() {
//        PageRequest pageRequest = new PageRequest(0,2);
//       List<OrderDetail> orderDetails = orderDetailDao.findOrderDetailByOrderId("340828",pageRequest);
//        Assert.assertNotNull(orderDetails);
//    }

    @Test
    public void saveOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("9999999");
        orderDetail.setOrderId("340828");
        orderDetail.setProductId("19960702");
        orderDetail.setProductName("电脑");
        orderDetail.setProductQuantity(100);
        orderDetail.setProductPrice(new BigDecimal(6666.666));
        orderDetail.setProductIcon("http://xxx.com");
        orderDetailDao.save(orderDetail);

        Assert.assertNotNull(orderDetailDao);
    }
}