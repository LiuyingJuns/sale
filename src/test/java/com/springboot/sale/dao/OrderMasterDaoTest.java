package com.springboot.sale.dao;

import com.springboot.sale.entity.OrderMaster;
import com.springboot.sale.enums.OrderMasterEnum;
import com.springboot.sale.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void findOrderMasterByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasterList = orderMasterDao.findOrderMasterByBuyerOpenid("666666666",pageRequest);
    Assert.assertNotNull(orderMasterList);
    }

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("刘帅气");
        orderMaster.setBuyerAddress("我爱我家");
        orderMaster.setBuyerTelphone("15266398563");
        orderMaster.setOrderAmount(new BigDecimal(66));
        orderMaster.setBuyerOpenid("666666666");
        orderMaster.setOrderStatus(OrderMasterEnum.NEW_ORDER.getCode());
        orderMaster.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());
        orderMasterDao.save(orderMaster);

        Assert.assertNotNull(orderMasterDao);
    }
}