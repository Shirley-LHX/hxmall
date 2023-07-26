package com.shirley.hxmall.controller;

import com.shirley.hxmall.service.ProductCommontsService;
import com.shirley.hxmall.service.ProductService;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Tag(name = "提供商品信息相关的接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCommontsService productCommontsService;

    @Operation(summary = "商品基本信息查询接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVO getProductBasicInfo(@PathVariable("pid") String pid){
        return productService.getProductBasicInfo(pid);
    }

    @Operation(summary = "商品参数信息查询接口")
    @GetMapping("/detail-params/{pid}")
    public ResultVO getProductParams(@PathVariable("pid") String pid){
        return productService.getProductParamsById(pid);
    }

    @Operation(summary = "商品评论信息查询接口")
    @GetMapping("/detail-commonts/{pid}")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页码",required = true),
            @Parameter(name = "limit", description = "每页显示条数",required = true)
    })
    public ResultVO getProductCommonts(@PathVariable("pid") String pid,int pageNum,int limit){
        return productCommontsService.listCommontsByProductId(pid,pageNum,limit);
    }

    @Operation(summary = "商品评价统计查询接口")
    @GetMapping("/detail-commontscount/{pid}")
    public ResultVO getProductCommontsCount(@PathVariable("pid") String pid){
        return productCommontsService.getCommentsCountByProductId(pid);
    }

//
//    @ApiOperation("根据类别查询商品接口")
//    @GetMapping("/listbycid/{cid}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
//            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)
//    })
//    public ResultVO getProductsByCategoryId(@PathVariable("cid") int cid,int pageNum,int limit){
//        return productService.getProductsByCategoryId(cid,pageNum,limit);
//    }
//
//    @ApiOperation("根据类别查询商品品牌接口")
//    @GetMapping("/listbrands/{cid}")
//    public ResultVO getBrandsByCategoryId(@PathVariable("cid") int cid){
//        return productService.listBrands(cid);
//    }
//
//    @ApiOperation("根据关键字查询商品接口")
//    @GetMapping("/listbykeyword")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "string",name = "keyword", value = "搜索关键字",required = true),
//            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
//            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)
//    })
//    public ResultVO searchProducts(String keyword,int pageNum,int limit){
//        return productService.searchProduct(keyword,pageNum,limit);
//    }
//
//    @ApiOperation("根据关键字查询商品品牌接口")
//    @GetMapping("/listbrands-keyword")
//    @ApiImplicitParam(dataType = "string",name = "keyword", value = "搜索关键字",required = true)
//    public ResultVO getBrandsByKeyword(String keyword){
//        return productService.listBrands(keyword);
//    }

}

