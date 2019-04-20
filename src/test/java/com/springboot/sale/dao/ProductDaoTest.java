package com.springboot.sale.dao;

import com.springboot.sale.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productDao.findByProductStatus(0);

           Assert.assertNotNull(list);

    }

    @Test
    public void addProduct(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("541515151123");
        productInfo.setProductName("平板电脑啊啊");
        productInfo.setProductPrice(new BigDecimal(1000));
        productInfo.setProductStock(5554);
        productInfo.setProductDescription("平板电脑啊啊啊");
        productInfo.setCategoryType(4);
        productInfo.setProductStatus(1);

       ProductInfo productInfo1 = productDao.save(productInfo);

        Assert.assertNotNull(productInfo1);
    }

    @Test
    public void findByProductStatusTest(){
       List<ProductInfo> list = productDao.findByProductStatus(1);
       Assert.assertNotNull(list);
    }
}