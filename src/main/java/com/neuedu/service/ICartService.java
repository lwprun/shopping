package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface ICartService {
    public ServerResponse add(Integer userId, Integer productId, Integer count);

    public ServerResponse list(Integer userId);


    public  ServerResponse update(Integer userId, Integer productId, Integer count);


    public ServerResponse deleteProduct(Integer userId, String productIds);


    public ServerResponse checked(Integer userId, Integer productId, Integer check);

    public ServerResponse getCartProductCount(Integer userId);
}
