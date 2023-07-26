package com.shirley.hxmall.service;


import com.shirley.hxmall.vo.ResultVO;

public interface UserService {
    public ResultVO checkLogin(String name, String password);
    public ResultVO userRegist(String name, String password);
}
