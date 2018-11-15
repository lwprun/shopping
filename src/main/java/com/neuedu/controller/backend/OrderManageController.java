package com.neuedu.controller.backend;


import com.neuedu.common.ServerResponse;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/order")


public class OrderManageController {

    @Autowired
    IOrderService orderService;


    /**
     * 订单list
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(
                               @RequestParam(required = false , defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false ,defaultValue = "10")Integer pageSize){



        return orderService.list(null,pageNum,pageSize);
    }



    /**
     * 按订单号查询
     * */

    @RequestMapping(value = "/detail/orderNo/{orderNo}")
    public ServerResponse detail(@PathVariable("orderNo") Long orderNo ){



        return orderService.detail(orderNo);
    }


    //订单发货
    @RequestMapping(value = "/send_goods/orderNo/{orderNo}")
    public ServerResponse send_goods(@PathVariable("orderNo") Long orderNo ){

        return orderService.send_goods(orderNo);
    }
}
