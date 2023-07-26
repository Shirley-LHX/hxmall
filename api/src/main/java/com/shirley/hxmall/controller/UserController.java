package com.shirley.hxmall.controller;

import com.shirley.hxmall.entity.Users;
import com.shirley.hxmall.service.UserService;
import com.shirley.hxmall.utils.Base64Utils;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @Operation(summary = "foo", description = "bar")
    @GetMapping(value = "/login")
    public ResultVO login(@RequestParam("username") String name,
                          @RequestParam("password") String pwd){
        ResultVO resultVO = userService.checkLogin(name, pwd);
        return resultVO;
    }

    @PostMapping(value = "/regist")
    public ResultVO regist(@RequestBody Users user){
        ResultVO resultVO = userService.userRegist(user.getUsername(), user.getPassword());
        return resultVO;
    }
}
