package com.shirley.hxmall.service.impl;

import com.shirley.hxmall.dao.UsersMapper;
import com.shirley.hxmall.entity.Users;
import com.shirley.hxmall.service.UserService;
import com.shirley.hxmall.utils.Base64Utils;
import com.shirley.hxmall.utils.MD5Utils;
import com.shirley.hxmall.vo.ResStatus;
import com.shirley.hxmall.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultVO checkLogin(String name, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        List<Users> users = usersMapper.selectByExample(example);
        if(users.size() == 0){
            return new ResultVO(ResStatus.FAILED,"登录失败，用户名不存在!",null);
        }else{
            String md5Pwd = MD5Utils.md5(password);
            if(md5Pwd.equals(users.get(0).getPassword())){
                // 如果登录成功，需要生成JWT token进行用户认证
                JwtBuilder builder = Jwts.builder();
                HashMap<String,Object> map = new HashMap<>();
                map.put("key1","value1");
                map.put("key2","value2");

                String token = builder.setSubject(name)                     //主题，就是token中携带的数据
                        .setIssuedAt(new Date())                            //设置token的生成时间
                        .setId(users.get(0).getUserId() + "")               //设置用户id为token  id
                        .setClaims(map)                                     //map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) //设置token过期时间
                        .signWith(SignatureAlgorithm.HS256, "shirleyui6666")     //设置加密方式和加密密码
                        .compact();

                return new ResultVO(ResStatus.OK,token,users.get(0));
            }else{
                return new ResultVO(ResStatus.FAILED,"登录失败，密码错误!",null);
            }
        }
    }

    @Transactional
    public ResultVO userRegist(String name, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        List<Users> users = usersMapper.selectByExample(example);

        if(users.size() == 0){
            String md5Pwd = MD5Utils.md5(password);
            Users user = new Users();
            user.setUsername(name);
            user.setPassword(md5Pwd);
            user.setUserImg("img/default.png");
            user.setUserRegtime(new Date());
            user.setUserModtime(new Date());
            int i = usersMapper.insertUseGeneratedKeys(user);
            if (i > 0) {
                return new ResultVO(ResStatus.OK, "注册成功!", user);
            } else {
                return new ResultVO(ResStatus.FAILED, "注册失败!", null);
            }
        }else {
            return new ResultVO(ResStatus.FAILED, "用户名已经被注册!", null);
        }
    }
}
