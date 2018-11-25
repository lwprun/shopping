package com.neuedu.service.impl;

import com.google.common.collect.Lists;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CartMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.utils.BigDecimalUtils;
import com.neuedu.vo.CartProductVo;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;


    /**
     * 添加到购物车
     * */
    @Override
    public ServerResponse add(Integer userId,Integer productId, Integer count) {
        //参数非空校验
        if(productId==null||count==null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product==null){
            ServerResponse.serverResponseByError("要添加的商品不存在");
        }
        //根据productId和userId查询购物信息
        Cart cart=cartMapper.selectCartByUseridAndProductId(userId,productId);
        if(cart==null){
            //添加
            Cart cart1 = new Cart();
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(count);
            cart1.setChecked(Const.CartCheckedEnum.PRODUCT_CHECKED.getCode());
            cartMapper.insert(cart1);
        }else{
            //更新
            Cart cart1 =new Cart();
            cart1.setId(cart.getId());
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(cart.getQuantity()+count);
            cart1.setChecked(cart.getChecked());
            cartMapper.updateByPrimaryKey(cart1);

        }
        CartVO cartVO =getCartVoLimit(userId);
        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    /**
     * 查询购物车
     * */
    @Override
    public ServerResponse list(Integer userId) {
        CartVO cartVO =getCartVoLimit(userId);
        return ServerResponse.serverResponseBySuccess(cartVO);
    }

    /**
     * 更新购物车数量
     * */
    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer count) {

        //1.参数判断
        if(productId==null||count==null){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        //2.查询购物车中的商品
        Cart cart = cartMapper.selectCartByUseridAndProductId(userId, productId);
        if(cart!=null){
            //3.更新数量
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKey(cart);
        }
        //4.返回结果
        return ServerResponse.serverResponseBySuccess(getCartVoLimit(userId));
    }


    /**
     * 移除购物车
     * */
    @Override
    public ServerResponse deleteProduct(Integer userId, String productIds) {
        //1.参数判断
        if(productIds==null||productIds.equals("")){
            return ServerResponse.serverResponseByError("参数不能为空");
        }
        //2.productIds --》list<Integer>
        List<Integer> productIdlist = Lists.newArrayList();
        String[] productIdarr=productIds.split(",");
        if(productIdarr!=null&&productIdarr.length>0){
            for (String productIdstr:productIdarr
                 ) {
               Integer productId = Integer.parseInt(productIdstr);
                productIdlist.add(productId);
            }
        }
        //3.调用dao
        cartMapper.deleteByUserIdAndProductIds(userId,productIdlist);
        //4.返回结果
        return ServerResponse.serverResponseBySuccess(getCartVoLimit(userId));
    }

    /**
     * 选中购物车某件商品
     * */
    @Override
    public ServerResponse checked(Integer userId, Integer productId,Integer check) {

        //1.dao 接口
        cartMapper.checked(userId,productId,check);
        //2.返回结果
        return ServerResponse.serverResponseBySuccess(getCartVoLimit(userId));
    }

    /**
     * 获取购物车中商品的数量
     * */
    @Override
    public ServerResponse getCartProductCount(Integer userId) {
        int quantity =cartMapper.getCartProductCount(userId);
        return ServerResponse.serverResponseBySuccess(quantity);
    }


    //
    private CartVO getCartVoLimit(Integer userId){
        CartVO cartVO =new CartVO();
        // 根据userId查询购物信息==》list<cart>

        List<Cart> cartList = cartMapper.selectCartByUserid(userId);
        //list<cart>-->list<cartProductVo>
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        //购物车总价格
        BigDecimal carttotalprice =new BigDecimal("0");
        if(cartList!=null&&cartList.size()>0){
            for (Cart cart:cartList){
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cart.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductChecked(cart.getChecked());
                Product product = productMapper.selectByPrimaryKey(cart.getProductId());
                if(product!=null){
                cartProductVo.setProductId(cart.getProductId());
                cartProductVo.setProductMainImage(product.getMainImage());
                cartProductVo.setProductName(product.getName());
                cartProductVo.setProductPrice(product.getPrice());
                cartProductVo.setProductStatus(product.getStatus());
                cartProductVo.setProductSubtitle(product.getSubtitle());
                int stock  = product.getStock();
                int LimitproductCount=0;
                if(stock>=cart.getQuantity()){
                    LimitproductCount=cart.getQuantity();
                    cartProductVo.setLimitQuqntity("LIST_NUM_SUCCESS");
                }else{//商品库存不足
                    LimitproductCount=stock;
                    //更新购物车中商品的数量
                    Cart cart1 = new Cart();
                    cart1.setId(cart.getId());
                    cart1.setQuantity(stock);
                    cart1.setProductId(cart.getProductId());
                    cart1.setChecked(cart.getChecked());
                    cart1.setUserId(userId);
                    cartMapper.updateByPrimaryKey(cart1);
                    cartProductVo.setLimitQuqntity("LIMIT_NUM_FAIL");
                }
                cartProductVo.setQuantity(LimitproductCount);
                cartProductVo.setProductTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),Double.valueOf(cartProductVo.getQuantity())));
                }
                if(cartProductVo.getProductChecked()==Const.CartCheckedEnum.PRODUCT_CHECKED.getCode()){
                    carttotalprice=BigDecimalUtils.add(carttotalprice.doubleValue(),cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        cartVO.setCartProductVoList(cartProductVoList);
        //计算总价格
        cartVO.setCattotalprice(carttotalprice);

        //判断购物车是否全选
        int count =cartMapper.isCheckedAll(userId);
        List<Cart> carts=cartMapper.selectCartByUserid(userId);
        if(count>0||carts==null||carts.size()==0){
            cartVO.setAllchecked(false);
        }else {
            cartVO.setAllchecked(true);

        }
        //返回结果

        return cartVO;
    }
}
