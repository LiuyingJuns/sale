package com.springboot.sale.vo;

import java.util.List;

public class CateGoryVO {
    private String cateGoryName;

    private Integer cateGoryType;

    private List<ProductVO> categoryProductList;

    public String getCateGoryName() {
        return cateGoryName;
    }

    public void setCateGoryName(String cateGoryName) {
        this.cateGoryName = cateGoryName;
    }

    public Integer getCateGoryType() {
        return cateGoryType;
    }

    public void setCateGoryType(Integer cateGoryType) {
        this.cateGoryType = cateGoryType;
    }

    public List<ProductVO> getCategoryProductList() {
        return categoryProductList;
    }

    public void setCategoryProductList(List<ProductVO> categoryProductList) {
        this.categoryProductList = categoryProductList;
    }
}
