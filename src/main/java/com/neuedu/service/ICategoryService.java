package com.neuedu.service;

import com.neuedu.common.ServerResponse;

public interface ICategoryService {


    /**
     *  获取品类子节点(平级)
     * */
    ServerResponse getCategory(Integer categoryId);

    /**
     * 增加节点
     * */
    ServerResponse addCategory(Integer parentId, String categoryName);
    /**
     * 修改节点
     * */
    ServerResponse setCategoryName(Integer categoryId, String categoryName);

    /**
     * 获取当前分类id及递归子节点categoryId
     * */
   ServerResponse   getDeepCategory(Integer categoryId);
}