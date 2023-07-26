package com.shirley.hxmall.controller;

import com.github.wxpay.sdk.WXPay;
import com.shirley.hxmall.config.MyPayConfig;
import com.shirley.hxmall.entity.Orders;
import com.shirley.hxmall.service.OrderService;
import com.shirley.hxmall.vo.ResStatus;
import com.shirley.hxmall.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Tag(name = "提供订单相关的操作接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVO add(String cids, @RequestBody Orders order){
        ResultVO resultVO = null;
        try {
            Map<String, String> orderInfo = orderService.addOrder(cids, order);
            if(orderInfo!=null){
                String orderId = orderInfo.get("orderId");
                //设置当前订单信息
                HashMap<String,String> data = new HashMap<>();
                data.put("body",orderInfo.get("productNames"));  //商品描述
                data.put("out_trade_no",orderId);               //使用当前用户订单的编号作为当前支付交易的交易号
                data.put("fee_type","CNY");                     //支付币种
                //data.put("total_fee",order.getActualAmount()*100+"");          //支付金额
                data.put("total_fee","1");
                data.put("trade_type","NATIVE");                //交易类型
                data.put("notify_url","/pay/callback");           //设置支付完成时的回调方法接口

                //发送请求，获取响应
                //微信支付：申请支付连接
                WXPay wxPay = new WXPay(new MyPayConfig());
                Map<String, String> resp = wxPay.unifiedOrder(data);
                orderInfo.put("payUrl",resp.get("code_url"));
                //orderInfo中包含：订单编号，购买的商品名称，支付链接
                resultVO = new ResultVO(ResStatus.OK,"提交订单成功！",orderInfo);
            }else{
                resultVO = new ResultVO(ResStatus.FAILED,"提交订单失败！",null);
            }
        } catch (SQLException e) {
            resultVO = new ResultVO(ResStatus.FAILED,"提交订单失败！",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVO;
    }

    @GetMapping("/status/{oid}")
    public ResultVO getOrderStatus(@PathVariable("oid") String orderId,@RequestHeader("token")String token){
        ResultVO resultVO = orderService.getOrderById(orderId);
        return resultVO;
    }

    @GetMapping("/list")
    @Operation(summary = "订单查询接口")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID",required = true),
            @Parameter(name = "status", description = "订单状态",required = false),
            @Parameter(name = "pageNum", description = "页码",required = true),
            @Parameter(name = "limit", description = "每页条数",required = true)
    })
    public ResultVO list(@RequestHeader("token")String token,
                         String userId,String status,int pageNum,int limit){
        ResultVO resultVO = orderService.listOrders(userId, status, pageNum, limit);
        return resultVO;
    }

}