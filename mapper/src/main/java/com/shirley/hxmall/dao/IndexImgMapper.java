package com.shirley.hxmall.dao;

import com.shirley.hxmall.entity.IndexImg;
import com.shirley.hxmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDAO<IndexImg> {
    //1.查询轮播图信息： 查询status=1 且 按照seq进行排序
    public List<IndexImg> listIndexImgs();
}