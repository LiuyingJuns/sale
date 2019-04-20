package com.springboot.sale.dao;

import com.springboot.sale.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCateGoryDaoTest {
    @Autowired
    private ProductCateGoryDao productCateGoryDao;
    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2,3);
       List<ProductCategory> result = productCateGoryDao.findByCategoryTypeIn(list);
        Assert.assertNotNull(result);
    }
}