package com.springboot.sale.dao;

import com.springboot.sale.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductCateGoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn (List<Integer> CateGoryTypeList);
}
