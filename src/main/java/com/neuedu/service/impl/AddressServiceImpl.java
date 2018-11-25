package com.neuedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    ShippingMapper shippingMapper;


    /**
     * 新增或更新地址
     * */
    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {

        //1.参数校验
        if (shipping == null) {
            return ServerResponse.serverResponseByError("参数错误");
        }
        //2.添加
        shipping.setUserId(userId);
        shippingMapper.insert(shipping);
        //3.返回结果

        Map<String,Integer> map =new HashMap<>();
        map.put("shippingId",shipping.getId());
        return ServerResponse.serverResponseBySuccess(map);
    }

    /**
     * 删除收获地址
     * @param userId
     * @param shippingId
     */
    @Override
    public ServerResponse del(Integer userId, Integer shippingId) {
        //1.参数校验
        if (shippingId == null) {
            return ServerResponse.serverResponseByError("参数错误");
        }
        //2.删除
        int result = shippingMapper.deleteByUseridAndShippingId(userId,shippingId);

        //3.返回结果
        if(result>0){
            return ServerResponse.serverResponseBySuccess();
        }
        return ServerResponse.serverResponseByError("删除失败");
    }

    /**
     * 登录状态更新地址
     * @param shipping
     */
    @Override
    public ServerResponse update(Shipping shipping) {
        //1.参数校验
        if (shipping == null) {
            return ServerResponse.serverResponseByError("参数错误");
        }
        //2.更新
        int result = shippingMapper.updateBySelectiveKey(shipping);

        //3.返回结果
        if(result>0){
            return ServerResponse.serverResponseBySuccess();
        }
        return ServerResponse.serverResponseByError("更新失败");
    }

    /**
     * 查看选中的具体地址
     *
     * @param shippingId
     */
    @Override
    public ServerResponse select(Integer shippingId) {
        //1.参数校验
        if (shippingId == null) {
            return ServerResponse.serverResponseByError("参数错误");
        }
        //2.查看
        Shipping shipping = shippingMapper.selectByPrimaryKey(shippingId);


        //3.返回结果

        if(shipping!=null){
            return ServerResponse.serverResponseBySuccess(shipping);

        }
        return ServerResponse.serverResponseByError("地址不存在");


    }

    /**
     * 分页自己具体地址
     * @param userId
     * @param pageNum
     * @param pageSize
     */
    @Override
    public ServerResponse list(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList =null;
        if(userId==null){
            shippingList= shippingMapper.selectAll();
        }else {
             shippingList = shippingMapper.selectByUserId(userId);
            if(shippingList==null||shippingList.size()==0){
                return ServerResponse.serverResponseByError("你还没有自己的地址哦，快去添加吧");
            }
        }
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.serverResponseBySuccess(pageInfo);
    }




}
