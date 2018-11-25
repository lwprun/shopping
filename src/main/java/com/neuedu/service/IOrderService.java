package com.neuedu.service;

import com.neuedu.common.ServerResponse;

import java.io.IOException;
import java.util.Map;

public interface IOrderService {

    /**
     * 创建订单
     */
    ServerResponse createOrder(Integer userId, Integer shippingId);


    /**
     * 取消订单
     */
    ServerResponse cancel(Integer userId, Long orderNo);
    /**
     * 获取订单商品信息
     * */
    ServerResponse getOrderCartProduct(Integer userId);
    /**
     * 订单list
     * */
    ServerResponse list(Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 订单详情
     * */
    ServerResponse detail(Long orderNo);



    /**
     * 订单发货
     **/
    ServerResponse sendGoods(Long orderNo);


    /***
     *
     * 支付接口
     */
    ServerResponse pay(Integer userId, Long orderNo) throws IOException;


    /**
     * 支付宝回调接口
     * */

    ServerResponse alipayCallback(Map<String, String> map);


    /**
     * 查询订单的支付状态
     * */
    ServerResponse queryOrderPayStatus(Long orderNo);

    /**
     * 关闭订单
     * */
    void closeOrder(String time);
}
