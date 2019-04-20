package com.springboot.sale.service.Impl;

import com.springboot.sale.dao.ProductCateGoryDao;
import com.springboot.sale.entity.ProductCategory;
import com.springboot.sale.service.CateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CateGoryServiceImpl implements CateGoryService {
    @Autowired
    private ProductCateGoryDao productCateGoryDao;

    @Override
    public ProductCategory findOne(Integer productCategoryId) {
        return productCateGoryDao.findById(productCategoryId).orelse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCateGoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findBycategoryTypeIn(List<Integer> cateGoryType) {

        return productCateGoryDao.findByCategoryTypeIn(cateGoryType);
    }

    @Override
    @Transactional(
            rollbackFor = Exception.class
    )
    public ProductCategory save(ProductCategory productCategory) {
        return productCateGoryDao.save(productCategory);
    }
}
