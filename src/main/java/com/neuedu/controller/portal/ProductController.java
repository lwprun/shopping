package com.neuedu.controller.portal;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/product")
public class ProductController {


    @Autowired
    IProductService productService;
    /**
     * 商品详情
     * */
    @RequestMapping(value = "/detail/productId/{productId}")
    public ServerResponse detail(@PathVariable("productId") Integer productId){
        return  productService.detailPortal(productId);
    }

    /**
     * 前台-按类别或关键字搜索商品并排序
     *
     * */
    @RequestMapping(value = "/list.do")
    public ServerResponse list(@RequestParam(required = false) Integer categoryId,
                               @RequestParam(required = false)     String keyword,
                               @RequestParam(required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                               @RequestParam(required = false,defaultValue = "")String orderBy){


        return  productService.listPortal(categoryId,keyword,pageNum,pageSize,orderBy);
    }
    @RequestMapping(value = "/listAll.do")
    public ServerResponse list(HttpSession session,
                               @RequestParam(value = "pageNum" ,required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize" ,required = false,defaultValue = "10")Integer pageSize){


        return productService.list(pageNum,pageSize);
    }



    @RequestMapping(value = "/list/categoryId/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse listRestfulByCategoryId(@PathVariable("categoryId") Integer categoryId,

                                                  @PathVariable("pageNum") Integer pageNum,
                                                  @PathVariable("pageSize") Integer pageSize,
                                                  @PathVariable("orderBy") String orderBy){


        return  productService.listPortal(categoryId,null,pageNum,pageSize,orderBy);
    }
    @RequestMapping(value = "/list/keyword/{keyword}/{pageNum}/{pageSize}/{orderBy}")
    public ServerResponse listRestfulByKeyword(
            @PathVariable("keyword")     String keyword,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize,
            @PathVariable("orderBy") String orderBy){

        System.out.println(keyword);
        return  productService.listPortal(null,keyword,pageNum,pageSize,orderBy);
    }



}