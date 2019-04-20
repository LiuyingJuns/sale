package com.springboot.sale.service.Impl;

import com.springboot.sale.entity.ProductInfo;
import com.springboot.sale.enums.ProductEnum;
import com.springboot.sale.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findOne() {
       ProductInfo productInfo = productService.findOne("541515151123");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
       List<ProductInfo> list = productService.findUpAll();
       Assert.assertNotNull(list);
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
       Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage);
        Assert.assertNotNull(productInfoPage);
    }

    @Test
    public void save() throws Exception{
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("551548484");
        productInfo.setProductName("手表");
        productInfo.setProductPrice(new BigDecimal(666));
        productInfo.setProductStock(55);
        //商品状态：上架
        productInfo.setProductStatus(ProductEnum.UP.getCode());
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("this is watch...");
       ProductInfo result = productService.save(productInfo);
       Assert.assertNotNull(result);
    }
}