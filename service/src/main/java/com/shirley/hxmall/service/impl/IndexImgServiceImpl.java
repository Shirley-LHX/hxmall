package com.shirley.hxmall.service.impl;


import com.shirley.hxmall.dao.IndexImgMapper;
import com.shirley.hxmall.entity.IndexImg;
import com.shirley.hxmall.service.IndexImgService;
import com.shirley.hxmall.vo.ResStatus;
import com.shirley.hxmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexImgServiceImpl implements IndexImgService {

    @Autowired
    private IndexImgMapper indexImgMapper;

    public ResultVO listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        System.out.println(indexImgs);
        if(indexImgs.size()==0){
            return new ResultVO(ResStatus.FAILED,"fail",null);
        }else{
            return new ResultVO(ResStatus.OK,"success",indexImgs);
        }
    }
}
