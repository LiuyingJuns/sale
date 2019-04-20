package com.springboot.sale.dao;

import com.springboot.sale.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    /**通过用户id查找订单主表信息 并且对订单进行分页*/
   Page<OrderMaster> findOrderMasterByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
