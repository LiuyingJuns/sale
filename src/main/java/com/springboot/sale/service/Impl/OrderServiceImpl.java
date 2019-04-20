package com.springboot.sale.service.Impl;

import com.springboot.sale.convert.OrderMasterConvetToOrderDTO;
import com.springboot.sale.dao.OrderDetailDao;
import com.springboot.sale.dao.OrderMasterDao;
import com.springboot.sale.dto.CartDTO;
import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.entity.OrderDetail;
import com.springboot.sale.entity.OrderMaster;
import com.springboot.sale.entity.ProductInfo;
import com.springboot.sale.enums.OrderMasterEnum;
import com.springboot.sale.enums.PayStatusEnum;
import com.springboot.sale.enums.ResultEnum;
import com.springboot.sale.exception.SaleException;
import com.springboot.sale.service.OrderService;
import com.springboot.sale.service.ProductService;
import com.springboot.sale.utils.KeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
   @Autowired
   private ProductService productService;

   @Autowired
   private OrderDetailDao orderDetailDao;

   @Autowired
   private OrderMasterDao orderMasterDao;


   /**c创建订单*/
    @Override
    @Transactional(
            rollbackFor = Exception.class
    )
    public OrderDTO creatOrder(OrderDTO orderDTO) {
        /**创建订单id*/
        String orderid = KeyUtils.setKey();

        BigDecimal orderAmount = new BigDecimal(0);

        List<OrderDTO> orderDTOList = new ArrayList<>();

     /** 首先判断商品是否存在? 商品和订单详情挂钩*/
    List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

    /** 遍历订单详情list*/
    for (OrderDetail orderDetail : orderDetailList){
     ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
     if (productInfo == null){
         logger.error("订单不存在"+productInfo);
         throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
     }
     /** 计算订单总价*/
     orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

     /** 订单详情入库*/
        BeanUtils.copyProperties(productInfo,orderDetail);
        orderDetail.setOrderId(orderid);
        orderDetail.setDetailId(KeyUtils.setKey());
        orderDetailDao.save(orderDetail);

        /** 订单主表入库*/
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setBuyerOpenid(KeyUtils.setKey());
        orderDTO.setOrderId(orderid);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderMasterEnum.NEW_ORDER.getCode());
        orderMaster.setPayStatus(PayStatusEnum.PAY_WAIT.getCode());
        orderMasterDao.save(orderMaster);

        /**减库存 */
        List<CartDTO> cartDTOList = new ArrayList<>();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(orderDetail.getProductId());
        cartDTO.setProductQuantity(orderDetail.getProductQuantity());
        cartDTOList.add(cartDTO);
        productService.decreaseStock(cartDTOList);
    }
    return orderDTO;
    }


    /**
     * 查询订单详情
     */
    @Override
    public OrderDTO findOne(String openid) {

        //查询主表详情
        OrderMaster orderMaster = orderMasterDao.findById(openid).orElse(null);
        if (orderMaster == null) {
            logger.error("订单信息不存在" + orderMaster);
            throw new SaleException(ResultEnum.ORDER_MASTER_NOT_EXIST);
        }
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.findOrderDetailByOrderId(openid);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            logger.error("订单详情不存在" + orderDetailList);
            throw new SaleException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**查询订单列表，分页显示*/
    @Override
    public Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterDao.findOrderMasterByBuyerOpenid(buyerOpenid,pageable);
        //page转化为list
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();

        OrderMasterConvetToOrderDTO masterConvetToOrderDTO = new OrderMasterConvetToOrderDTO();
       //调用转换器
        List<OrderDTO> orderDTOList = masterConvetToOrderDTO.convert(orderMasterList);

        //实现page分页，传入三个参数
       Page<OrderDTO> page = new PageImpl(orderDTOList,pageable,orderMasterPage.getTotalElements());

       return page;
    }



    /**取消订单*/
    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderMasterEnum.NEW_ORDER.getCode())) {
            logger.error("订单状态不为0,0为新订单"+orderDTO.getOrderStatus());
            throw new SaleException(ResultEnum.ORDER_STATUS_NOT_RIGHT);
        }

        //更改订单状态
        orderDTO.setOrderStatus(OrderMasterEnum.CANCER_ORDER.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
       OrderMaster updateResult = orderMasterDao.save(orderMaster);
       if (!updateResult.getOrderStatus().equals(OrderMasterEnum.CANCER_ORDER.getCode())){
           logger.error("设置订单状态失败"+updateResult);
           throw new SaleException(ResultEnum.UPDATE_ORDER_STATUS_FAILED);
       }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("订单详情为空");
            throw new SaleException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList = orderDTO.getOrderDetailList().stream().map(orderDetail -> {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setProductId(orderDetail.getProductId());
                cartDTO.setProductQuantity(orderDetail.getProductQuantity());
                return cartDTO;
               }
               ).collect(Collectors.toList());
         productService.increaseStock(cartDTOList);

         return orderDTO;
    }
        //退款


}
