package com.springboot.sale.controller;

import com.springboot.sale.convert.OrderFormConvertToOrderDTO;
import com.springboot.sale.dto.OrderDTO;
import com.springboot.sale.enums.ResultEnum;
import com.springboot.sale.exception.SaleException;
import com.springboot.sale.form.OrderForm;
import com.springboot.sale.service.OrderService;
import com.springboot.sale.utils.ResultUtils;
import com.springboot.sale.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    /**创建订单*/
    @PostMapping("/creatOrder")
    public ResultVO<Map<String,String>> creatOrder(@Valid OrderForm orderForm,
     BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new SaleException(ResultEnum.PARAMETER_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
       OrderDTO orderDTO  = OrderFormConvertToOrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            logger.error("订单为空"+orderDTO.getOrderDetailList());
            throw new SaleException(ResultEnum.ORDER_DETAIL_IS_EMPTY);
        }
       OrderDTO result = orderService.creatOrder(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderid",result.getOrderId());

        return ResultUtils.success(map);
    }
    /**查询订单列表*/
    @GetMapping("/findOrderList")
    public ResultVO<List<OrderDTO>> findOrderList(
            @RequestParam(name = "buyerOpenid") String buyerOpenid,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size){

        if (StringUtils.isEmpty(buyerOpenid)){
            logger.error("用户微信号为空"+buyerOpenid);
            throw new SaleException(ResultEnum.BUYER_OPENID_IS_EMPTY);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findOrderList(buyerOpenid,pageRequest);

        return ResultUtils.success(orderDTOPage);
    }
    /**查询订单详情*/

    @GetMapping("/findOrderDetail")
    public ResultVO<List<OrderDTO>> findOrderDetail(
            @RequestParam(name = "orderId" ) String orderId,
            @RequestParam(name = "buyerOpenid") String buyerOpenid
    ){
        //TODO 不安全的操作
        OrderDTO orderDTO = orderService.findOne(buyerOpenid);
        return ResultUtils.success(orderDTO);
    }

    /**取消订单*/
    @PostMapping("cancelOrder")
    public ResultVO<OrderDTO> cancelOrder(
            @RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "buyerOpenid") String buyerOpenid
    ){
        //TODO 不安全的操作
       OrderDTO orderDTO = orderService.findOne(buyerOpenid);
        orderService.cancelOrder(orderDTO);
        return ResultUtils.success();
    }

}
