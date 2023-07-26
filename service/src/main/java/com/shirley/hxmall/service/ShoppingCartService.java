package com.shirley.hxmall.service;

import com.shirley.hxmall.entity.ShoppingCart;
import com.shirley.hxmall.vo.ResultVO;

import java.util.List;

public interface ShoppingCartService {

    public ResultVO addShoppingCart(ShoppingCart cart);

    public ResultVO listShoppingCartsByUserId(int userId);

    public ResultVO updateCartNum(int cartId,int cartNum);

    public ResultVO listShoppingCartsByCids(String cids);

}
