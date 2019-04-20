package com.springboot.sale.dao;

import com.springboot.sale.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
    public List<OrderDetail> findOrderDetailByOrderId(String orderId);
}
