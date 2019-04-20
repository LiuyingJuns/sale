package com.springboot.sale.controller;

import com.springboot.sale.vo.CateGoryVO;
import com.springboot.sale.vo.ProductVO;
import com.springboot.sale.utils.ResultUtils;
import com.springboot.sale.vo.ResultVO;
import com.springboot.sale.entity.ProductCategory;
import com.springboot.sale.entity.ProductInfo;
import com.springboot.sale.service.CateGoryService;
import com.springboot.sale.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CateGoryService cateGoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有的商品
       List<ProductInfo> productInfoList = productService.findUpAll();

       //遍历商品
        List<Integer> integerList = new ArrayList<>();
        for (ProductInfo productInfo:productInfoList){
            integerList.add(productInfo.getCategoryType());
        }

        //查询所有的类目
       List<ProductCategory> categoryList = cateGoryService.findBycategoryTypeIn(integerList);

        //拼接数据
        List<CateGoryVO> cateGoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList){
            CateGoryVO cateGoryVO= new CateGoryVO();
            cateGoryVO.setCateGoryType(productCategory.getCategoryType());
            cateGoryVO.setCateGoryName(productCategory.getCategoryName());


            List<ProductVO> productVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(productInfo,productVO);
                    productVOList.add(productVO);
                }
            }
            cateGoryVO.setCategoryProductList(productVOList);
            cateGoryVOList.add(cateGoryVO);
        }

      return ResultUtils.success(cateGoryVOList);
    }
}