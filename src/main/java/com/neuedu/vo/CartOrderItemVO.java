package com.neuedu.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.BackgroundImage;

import java.math.BigDecimal;
import java.util.List;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class CartOrderItemVO {

    private List<OrderItemVO> orderItemVOList;
    private String imageHost;
    private BigDecimal totalPrice;

    public List<OrderItemVO> getOrderItemVOList() {
        return orderItemVOList;
    }

    public void setOrderItemVOList(List<OrderItemVO> orderItemVOList) {
        this.orderItemVOList = orderItemVOList;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
