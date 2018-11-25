package com.neuedu.controller.backend;


import com.neuedu.common.ServerResponse;
import com.neuedu.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/shipping")
public class AddressManageController {

    @Autowired
    IAddressService addressService;

    /**
     * 分页具体地址
     * */
    @RequestMapping(value = "/listAll.do")
    public ServerResponse listAll(HttpSession session,
                                   @RequestParam(required = false ,defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false ,defaultValue = "5") Integer pageSize
    ){

        return addressService.list(null,pageNum,pageSize);
    }
}
