package com.neuedu.controller.portal;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    ICartService cartService;
    /**
     * 购物车添加商品
     * */
    @RequestMapping(value = "/add/productId/{productId}/count/{count}")
    public ServerResponse add(HttpSession session, @PathVariable("productId")  Integer productId,@PathVariable("count") Integer count){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.add(userInfo.getId(), productId,count);

    }


    /**
     * 购物车列表
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(HttpSession session){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.list(userInfo.getId());

    }


    /**
     * 更新购物车中某个商品数量
     * */
    @RequestMapping(value = "/update/productId/{productId}/count/{count}")
    public ServerResponse update(HttpSession session,@PathVariable("productId") Integer productId,@PathVariable("count") Integer count){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.update(userInfo.getId(),productId,count);

    }



    /**
     * 移除购物车中某个商品
     * */
    @RequestMapping(value = "/deletProduct/productIds/{productIds}")
    public ServerResponse deletProduct(HttpSession session, @PathVariable("productIds") String  productIds){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.deleteProduct(userInfo.getId(),productIds);

    }



    /**
     * 选中购物车中某个商品
     * */
    @RequestMapping(value = "/checked/productId/{productId}")
    public ServerResponse checked(HttpSession session, @PathVariable("productId") Integer productId){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.checked(userInfo.getId(),productId,Const.CartCheckedEnum.PRODUCT_CHECKED.getCode());

    }


    /**
     * 取消选中购物车中某个商品
     * */
    @RequestMapping(value = "/unChecked/productId/{productId}")
    public ServerResponse unSelect(HttpSession session,@PathVariable("productId") Integer productId){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.checked(userInfo.getId(),productId,Const.CartCheckedEnum.PRODUCT_UNCHECKED.getCode());

    }


    /**
     * 全选
     * */
    @RequestMapping(value = "/checkedAll.do")
    public ServerResponse CheckedAll(HttpSession session){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.checked(userInfo.getId(),null,Const.CartCheckedEnum.PRODUCT_CHECKED.getCode());

    }



    /**
     * 取消全选
     * */
    @RequestMapping(value = "/unCheckedAll.do")
    public ServerResponse unCheckedAll(HttpSession session){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.checked(userInfo.getId(),null,Const.CartCheckedEnum.PRODUCT_UNCHECKED.getCode());

    }


    /**
     * 查询购物车中商品数量
     * */
    @RequestMapping(value = "/getCartProductCount.do")
    public ServerResponse getCartProductCount(HttpSession session){

        UserInfo userInfo =(UserInfo)  session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        return cartService.getCartProductCount(userInfo.getId());

    }
}
