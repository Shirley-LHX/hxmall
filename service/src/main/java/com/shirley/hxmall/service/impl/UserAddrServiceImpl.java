package com.shirley.hxmall.service.impl;

import com.shirley.hxmall.dao.UserAddrMapper;
import com.shirley.hxmall.entity.UserAddr;
import com.shirley.hxmall.service.UserAddrService;
import com.shirley.hxmall.vo.ResStatus;
import com.shirley.hxmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserAddrServiceImpl implements UserAddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listAddrsByUid(int userId) {
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("status",1);

        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", userAddrs);
        return resultVO;
    }
}
