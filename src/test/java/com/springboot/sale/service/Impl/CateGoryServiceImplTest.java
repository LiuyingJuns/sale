package com.springboot.sale.service.Impl;

import com.springboot.sale.dao.ProductCateGoryDao;
import com.springboot.sale.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CateGoryServiceImplTest {
    @Autowired
    private ProductCateGoryDao productCateGoryDao;
    @Test
    public void findOne() {
       Optional<ProductCategory> productCategoryDO = productCateGoryDao.findById(2);
    }

    @Test
    public void findAll() {
       List<ProductCategory> list = productCateGoryDao.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void findBycategoryTypeIn() {
    }

    @Test
    public void save() {
    }
}