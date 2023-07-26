package com.shirley.hxmall.controller;


import com.shirley.hxmall.service.UserAddrService;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "提供收货地址相关接口")
@RequestMapping("/useraddr")
public class UserAddrController {

    @Autowired
    private UserAddrService userAddrService;

    @GetMapping("/list")
    @Parameter(name = "userId", description = "用户ID",required = true)
    public ResultVO listAddr(Integer userId, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.listAddrsByUid(userId);
        return resultVO;
    }


}
