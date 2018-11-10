package com.neuedu.vo;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

//购物车实体类
public class CartVO implements Serializable {
    //购物信息集合
    private List<CartProductVo> cartProductVoList;
    //是否全选
    private boolean isAllchecked;
    //总价
    private BigDecimal cattotalprice;

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public boolean isAllchecked() {
        return isAllchecked;
    }

    public void setAllchecked(boolean allchecked) {
        isAllchecked = allchecked;
    }

    public BigDecimal getCattotalprice() {
        return cattotalprice;
    }

    public void setCattotalprice(BigDecimal cattotalprice) {
        this.cattotalprice = cattotalprice;
    }
}
