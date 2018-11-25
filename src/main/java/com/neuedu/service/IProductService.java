package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Product;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface IProductService {

   ServerResponse saveOrUpdate(Product product);
   /**
    * 商品上下架
    * @param productId 商品id
    * @param  status 商品状态
    * */
   ServerResponse setSaleStatus(Integer productId, Integer status);
   /**
    * 后台-商品详情
    * */
   ServerResponse detail(Integer productId);

   /**
    * 后台-商品列表，分页
    * */
  ServerResponse list(Integer pageNum, Integer pageSize);
  /**
   * 后台-搜索商品
   * */
 ServerResponse search(Integer productId, String productName, Integer pageNum, Integer pageSize);

  /**
   * 图片上传
   * */
  ServerResponse upload(MultipartFile file, String path);


   ServerResponse detailPortal(Integer productId);

   /**
    * 前台商品搜索
    * @param  categoryId
    * @param keyword
    * @param  pageNum
    * @param  pageSize
    * @param  orderBy 排序字段
    * */

  ServerResponse  listPortal(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy);


   }