package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/shipping")
public class AddressController {


     @Autowired
    IAddressService addressService;

    /**
     *
     * 删除地址
     * */

    @RequestMapping(value = "/del/shippingId/{shippingId}")
    public ServerResponse del(HttpSession session, @PathVariable("shippingId") Integer shippingId){
        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return addressService.del(userInfo.getId(),shippingId);
    }



    /**
     *
     * 添加地址
     * */
    @RequestMapping(value = "/add.do")
    public ServerResponse add(HttpSession session, Shipping shipping){
        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return addressService.add(userInfo.getId(),shipping);
    }


    /**
     * 登录状态更新地址
     * */

    @RequestMapping(value = "/update.do")
    public ServerResponse update(HttpSession session, Shipping shipping){
        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        shipping.setUserId(userInfo.getId());

        return addressService.update(shipping);
    }


    /**
     * 查看选中的具体地址
     * */
    @RequestMapping(value = "/select/shippingId/{shippingId}")
    public ServerResponse select(HttpSession session, @PathVariable("shippingId") Integer shippingId){
        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return addressService.select(shippingId);
    }


    /**
     * 分页显示自己具体地址
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(required = false ,defaultValue = "1")  Integer pageNum,
                               @RequestParam(required = false ,defaultValue = "5")  Integer pageSize
                               ){
        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }

        return addressService.list(userInfo.getId(),pageNum,pageSize);
    }


}
