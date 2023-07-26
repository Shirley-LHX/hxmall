package com.shirley.hxmall.controller;


import com.shirley.hxmall.service.CategoryService;
import com.shirley.hxmall.service.IndexImgService;
import com.shirley.hxmall.service.ProductService;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/index")
@Tag(name = "IndexController")
public class IndexController {

    @Autowired
    private IndexImgService indexImgService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/indeximg")
    public ResultVO listIndexImgs(){
        return indexImgService.listIndexImgs();
    }

    @GetMapping("/category-list")
    public ResultVO listCatetory(){
        return categoryService.listCategories();
    }


    @GetMapping("/list-recommends")
    @Operation(summary = "新品推荐接口")
    public ResultVO listRecommendProducts() {
        return productService.listRecommendProducts();
    }

    @GetMapping("/category-recommends")
    @Operation(summary = "分类推荐接口")
    public ResultVO listRecommendProductsByCategory(){
        return categoryService.listFirstLevelCategories();
    }

}
