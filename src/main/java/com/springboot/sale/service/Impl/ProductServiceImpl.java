package com.springboot.sale.service.Impl;

import com.springboot.sale.dao.ProductDao;
import com.springboot.sale.dto.CartDTO;
import com.springboot.sale.entity.ProductInfo;
import com.springboot.sale.enums.ProductEnum;
import com.springboot.sale.enums.ResultEnum;
import com.springboot.sale.exception.SaleException;
import com.springboot.sale.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productDao.findById(productId).orElse(null);
    }

    /**
     * 按商品上架上架状态进行查询,0上架中，1下架中
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productDao.findByProductStatus(ProductEnum.UP.getCode());
    }

    /**
     * 分页显示商品
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductInfo save(ProductInfo productInfo) {
        return productDao.save(productInfo);
    }


    /**加库存*/
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
           ProductInfo productInfo = productDao.findById(cartDTO.getProductId()).orElse(null);
           if(productInfo == null){
               logger.error("商品不存在"+productInfo);
               throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
           }
           int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
           productInfo.setProductStock(result);
           productDao.save(productInfo);
        }
    }


    /**扣库存*/
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
         ProductInfo productInfo = productDao.findById(cartDTO.getProductId()).orElse(null);

        int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
        if (result < 0){
            logger.error("商品库存不足"+productInfo);
            throw new SaleException(ResultEnum.PRODUCT_NOT_ENOUGH);
        }else {
            productInfo.setProductStock(result);
            productDao.save(productInfo);
        }
        }
    }



}

