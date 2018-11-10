package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Shipping;

import java.security.SecureRandom;


/**
 * 添加收获地址
 * */
public interface IAddressService {
    /**
     * 添加收获地址
     * */
    public ServerResponse add(Integer userId, Shipping shipping);


    /**
     * 删除收获地址
     * */
    public ServerResponse del(Integer userId,Integer shippingId);


    /**
     * 登录状态更新地址
     * */
    public ServerResponse update(Shipping shipping);


    /**
     * 查看选中的具体地址
     * */
    public ServerResponse select(Integer shippingId);

    /**
     * 分页具体地址
     * */
    public ServerResponse list(Integer pageNum,Integer pageSize);

}
