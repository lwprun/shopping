package com.neuedu.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * 创建订单
     */

    @RequestMapping(value = "/create/shippingId/{shippingId}")
    public ServerResponse createOrder(HttpSession session,@PathVariable("shippingId") Integer shippingId){

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.createOrder(userInfo.getId(),shippingId);
    }


    /**
     * 取消订单
     */

    @RequestMapping(value = "/cancel/orderNo/{orderNo}")
    public ServerResponse cancel(HttpSession session,@PathVariable("orderNo") Long orderNo){

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.cancel(userInfo.getId(),orderNo);
    }

    /**
     * 确认订单信息
     */

    @RequestMapping(value = "/get_order_cart_product.do")
    public ServerResponse get_order_cart_product(HttpSession session){

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.get_order_cart_product(userInfo.getId());
    }


    /**
     * 订单list
     */

    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false , defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false ,defaultValue = "10")Integer pageSize){

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.list(userInfo.getId(),pageNum,pageSize);
    }

    /**
     * 订单详情detail
     */

    @RequestMapping(value = "/detail/orderNo/{orderNo}")
    public ServerResponse detail(@PathVariable("orderNo") Long orderNo ){


        return orderService.detail(orderNo);
    }
    /**
     * 支付接口
     */
    @RequestMapping(value = "/pay/orderNo/{orderNo}")
    public ServerResponse pay(HttpSession session,@PathVariable("orderNo") Long orderNo) throws IOException {

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.pay(userInfo.getId(),orderNo);
    }

    /**
     * 支付宝服务器回调应用服务器接口
     * */

    @RequestMapping(value = "/alipay_callback.do")
    public ServerResponse callback(HttpServletRequest request){
        System.out.println("支付宝服务器回调应用服务器接口");
        Map<String,String> requestparams= Maps.newHashMap();
        Map<String,String[]> params =request.getParameterMap();
        Iterator<String> it =params.keySet().iterator();
        while (it.hasNext()){
            String key =it.next();
            String [] strarr =params.get(key);
            String vaule="";
            for(int i=0;i<strarr.length;i++){
                vaule=(i==strarr.length-1)?vaule+strarr[i]:vaule+strarr[i]+",";
            }
            requestparams.put(key,vaule);
        }
        //支付宝验签
        try {
            requestparams.remove("sign_type");
            boolean result=AlipaySignature.rsaCheckV2(requestparams, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            if(!result){
                return ServerResponse.serverResponseByError("非法请求");
            }



        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //处理业务逻辑
        return orderService.alipay_callback(requestparams);

    }

    /**
     * 查询订单的支付状态
     */
    @RequestMapping(value = "/query_order_pay_status/orderNo/{orderNo}")
    public ServerResponse query_order_pay_status(HttpSession session,@PathVariable("orderNo") Long orderNo){

        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return orderService.query_order_pay_status(orderNo);
    }
}
