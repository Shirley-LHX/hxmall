package com.shirley.hxmall.dao;

import com.shirley.hxmall.entity.ProductImg;
import com.shirley.hxmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    //根据商品 id 查询当前商品的图片信息
    public List<ProductImg> selectProductImgByProductId(int productId);
}