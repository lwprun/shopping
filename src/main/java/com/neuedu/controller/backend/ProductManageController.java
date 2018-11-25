package com.neuedu.controller.backend;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/product")
public class ProductManageController {

    @Autowired
    IProductService productService;
    /**
     * 新增OR更新产品
     * */
    @RequestMapping(value = "/save.do")
    public ServerResponse saveOrUpdate(Product product){


        return productService.saveOrUpdate(product);
    }

    /**
     * 产品上下架
     * */
    @RequestMapping(value = "/setSaleStatus/productId/{productId}/status/{status}")
    public ServerResponse setSaleStatus(@PathVariable("productId") Integer productId,
                                          @PathVariable("status") Integer status){


        return productService.setSaleStatus(productId,status);
    }

    /**
     * 查看商品详情
     * */
    @RequestMapping(value = "/detail/productId/{productId}")
    public ServerResponse detail(@PathVariable("productId") Integer productId){


        return productService.detail(productId);
    }

    /**
     * 查看商品列表
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(@RequestParam(value = "pageNum" ,required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")Integer pageSize){


        return productService.list(pageNum,pageSize);
    }

    /**
     * 产品搜索
     * */
    @RequestMapping(value = "/search.do")
    public ServerResponse search(
                                 @RequestParam(value = "productId" ,required = false)Integer productId,
                                 @RequestParam(value = "productName" ,required = false)String productName,
                               @RequestParam(value = "pageNum" ,required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")Integer pageSize){

        return productService.search(productId,productName,pageNum,pageSize);
    }



}