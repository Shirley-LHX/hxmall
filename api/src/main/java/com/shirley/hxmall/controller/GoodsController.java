package com.shirley.hxmall.controller;

import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/goods")
@Tag(name = "GoodsController")
public class GoodsController {
    @PostMapping(value = "/add")
    public ResultVO addGoods(){
        System.out.println("addGoods");
        return new ResultVO(10000, "SUCCESS",null);
    }

    @DeleteMapping(value = "/{gid}")
    public ResultVO deleteGoods(@PathVariable("gid") int goodsId){
        System.out.println("------"+goodsId);
        return new ResultVO(10000, "SUCCESS",null);
    }

    @PutMapping(value = "/{gid}")
    public ResultVO updateGoods(){
        return null;
    }

    @GetMapping(value = "/{gid}")
    public ResultVO getGoods(){
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO listGoods(){
        return null;
    }
}
