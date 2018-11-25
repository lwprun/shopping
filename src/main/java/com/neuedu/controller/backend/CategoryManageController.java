package com.neuedu.controller.backend;


import com.neuedu.common.ServerResponse;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/category")
public class CategoryManageController {
    @Autowired
    ICategoryService categoryService;

    /**
     * 获取品类子节点(平级)
     * */
    @RequestMapping(value = "/getCategory/categoryId/{categoryId}")
    public ServerResponse getCategory(@PathVariable("categoryId") Integer categoryId){

        return  categoryService.getCategory(categoryId);
    }

    /**
     * 增加节点
     * */
    @RequestMapping(value = "/addCategory.do")
    public ServerResponse addCategory(@RequestParam(required = false,defaultValue = "0") Integer parentId,
                                       String categoryName){

        return  categoryService.addCategory(parentId,categoryName);
    }

    /**
     *  修改节点
     * */
    @RequestMapping(value = "/setCategoryName/categoryId/{categoryId}/categoryName/{categoryName}")
    public ServerResponse setCategoryName(@PathVariable("categoryId")Integer categoryId,
                                          @PathVariable("categoryName")String categoryName){

        return  categoryService.setCategoryName(categoryId,categoryName);
    }


    /**
     * 获取当前分类id及递归子节点categoryId
     * */
    @RequestMapping(value = "/getDeepCategory/categoryId/{categoryId}")
    public ServerResponse getDeepCategory(@PathVariable("categoryId") Integer categoryId){

        return  categoryService.getDeepCategory(categoryId);
    }


}