package com.shirley.hxmall.controller;

import com.shirley.hxmall.entity.ShoppingCart;
import com.shirley.hxmall.service.ShoppingCartService;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Tag(name = "提供购物车业务相关接口")
public class ShopcartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart cart, @RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.addShoppingCart(cart);
        return resultVO;
    }

    @GetMapping("/list")
    @Parameter(name = "userId", description = "用户ID",required = true)
    public ResultVO list(Integer userId,@RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartsByUserId(userId);
        return resultVO;
    }

    @PutMapping("/update/{cid}/{cnum}")
    public ResultVO updateNum(@PathVariable("cid") Integer cartId,
                              @PathVariable("cnum") Integer cartNum,
                                @RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.updateCartNum(cartId, cartNum);
        return resultVO;
    }

    @GetMapping("/listbycids")
    @Parameter(name = "cids", description = "选择的购物车记录的id",required = true)
    public ResultVO listByCids(String cids, @RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartsByCids(cids);
        return resultVO;
    }



}
