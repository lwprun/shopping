package com.neuedu.controller.backend;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false , defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false ,defaultValue = "10")Integer pageSize){



        return orderService.list(null,pageNum,pageSize);
    }



    /**
     * 按订单号查询
     * */

    @RequestMapping(value = "/detail.do")
    public ServerResponse detail(HttpSession session,Long orderNo ){



        return orderService.detail(orderNo);
    }


    //订单发货
    @RequestMapping(value = "/send_goods.do")
    public ServerResponse send_goods(HttpSession session,Long orderNo ){

        return orderService.send_goods(orderNo);
    }
}
