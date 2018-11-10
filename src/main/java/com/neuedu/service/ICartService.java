package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface ICartService {
    public ServerResponse add(Integer userId,Integer productId, Integer count);

    public ServerResponse list(Integer userId);


    public  ServerResponse update(Integer userId,Integer productId,Integer count);


    public ServerResponse delete_product(Integer userId,String productIds);


    public ServerResponse select(Integer userId,Integer productId,Integer check);

    public ServerResponse get_cart_product_count(Integer userId);
}
