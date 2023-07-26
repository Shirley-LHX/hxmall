package com.shirley.hxmall.dao;

import com.shirley.hxmall.entity.ShoppingCart;
import com.shirley.hxmall.entity.ShoppingCartVO;
import com.shirley.hxmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart> {
    public List<ShoppingCartVO> selectShopcartByUserId(int userId);

    public int updateCartnumByCartid(@Param("cartId") int cartId,
                                     @Param("cartNum") int cartNum);
    //1,8
    public List<ShoppingCartVO> selectShopcartByCids(List<Integer> cids);
}