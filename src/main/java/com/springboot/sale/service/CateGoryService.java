package com.springboot.sale.service;

import com.springboot.sale.entity.ProductCategory;

import java.util.List;

/**
 * 类目service
 */
public interface CateGoryService {
    /**
     * 通过类目id查询类目
     * @param productCategoryId
     * @return
     */
    ProductCategory findOne(Integer productCategoryId);

    /**
     * 查找所有的类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 通过传入指定的list查询类目
     * @param cateGoryType
     * @return
     */
    List<ProductCategory> findBycategoryTypeIn(List<Integer> cateGoryType);

    /**
     * 薪资类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

}
