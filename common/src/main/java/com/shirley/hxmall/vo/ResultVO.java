package com.shirley.hxmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {

    private int code; //响应状态码
    private String msg;
    private Object data;

}
