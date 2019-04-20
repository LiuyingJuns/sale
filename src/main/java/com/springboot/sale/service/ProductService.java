package com.springboot.sale.service;

import com.springboot.sale.dto.CartDTO;
import com.springboot.sale.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 通过id查商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();


    /**
     * 查询所有商品，做分页展示
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 创建商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /** 加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /** 减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);


}
