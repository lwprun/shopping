package com.neuedu.controller.portal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    IUserService userService;
/**
 * 登录
 * */
@RequestMapping(value = "/login.do")
public ServerResponse login(HttpSession session, String username, String password){


                //serverResponse是一个包含data的对象（data为密码为空其他信息都存在的userInfo对象）
    ServerResponse serverResponse =userService.login(username,password);
    if(serverResponse.isSsuccess()){
                //通过的serverResponse.getData()方法将userInfo对象信息返回，返回值为泛型T需转换为userInfo
        UserInfo userInfo = (UserInfo) serverResponse.getData();
                //将userInfo对象保存到session中（保存当前登录用户）。
        session.setAttribute(Const.CURRENTUSER,userInfo);
    }
                //ServerResponse(ResponseCode.SUCESS,data);
    return serverResponse;
    }
/**
 * 注册
 * */
    @RequestMapping(value = "/register.do")
public ServerResponse register(UserInfo userInfo){

    ServerResponse serverResponse =userService.register(userInfo);
    return serverResponse;
}


/**
 * 根据用户名查询密保问题
 * */
@RequestMapping(value = "/forgetGetQuestion.do")
public ServerResponse forgetGetQuestion(String username) {

    ServerResponse serverResponse = userService.forgetGetQuestion(username);

    return serverResponse;

    }


    /**
     * 根据问题提交答案
     * */
    @RequestMapping(value = "/forgetCheckAnwser.do")
    public ServerResponse forgetCheckAnwser(String username,String question,String anwser) {

        ServerResponse serverResponse = userService.forgetCheckAnwser(username,question,anwser);

        return serverResponse;

    }

    /**
     * 重置密码
     * */
    @RequestMapping(value = "/forgetResetAnwser.do")
    public ServerResponse forgetResetAnwser(String username,String passwordNew,String forgetToken){
        ServerResponse serverResponse = userService.forgetResetAnwser(username,passwordNew,forgetToken);
        return serverResponse;
    }
    /**
     * 检查用户名或者邮箱是否有效
     * */
    @RequestMapping(value = "/checkValid.do")
    public ServerResponse checkValid(String str,String type){
        ServerResponse serverResponse=userService.checkValid(str,type);
        return  serverResponse;
    }

    /**
     * 获取登录用户信息
     * */
    @RequestMapping(value = "/getUserInfo.do")
    public ServerResponse getUserInfo(HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        UserInfo newuserInfo = new UserInfo();
        newuserInfo.setUsername(userInfo.getUsername());
        newuserInfo.setId(userInfo.getId());
        newuserInfo.setCreateTime(userInfo.getCreateTime());
        newuserInfo.setEmail(userInfo.getEmail());
        newuserInfo.setPhone(userInfo.getPhone());
        newuserInfo.setUpdateTime(userInfo.getUpdateTime());
        newuserInfo.setRole(userInfo.getRole());

        return  ServerResponse.serverResponseBySuccess(newuserInfo);
    }

    /**
     * 登录中状态重置密码
     * */
    @RequestMapping(value = "/resetPassword.do")
    public ServerResponse resetPassword(HttpSession session,String passwordOld,String passwordNew){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        return  userService.resetPassword(userInfo.getUsername(),passwordOld,passwordNew);
    }


    /**
     * 登录状态更新个人信息
     * */
    @RequestMapping(value = "/updateInformation.do")
    public ServerResponse updateInformation(HttpSession session,UserInfo user){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
        user.setId(userInfo.getId());
        ServerResponse serverResponse=  userService.updateInformation(user);
        if(serverResponse.isSsuccess()){
            //更新session中用户信息

            UserInfo userInfo1=   userService.findUserInfoByUserid(userInfo.getId());
            userInfo1.setPassword(null);
            session.setAttribute(Const.CURRENTUSER,userInfo1);
        }
        return  serverResponse;
    }

    /**
     * 获取登录用户详细信息
     * */
    @RequestMapping(value = "/getInforamtion.do")
    public ServerResponse getInforamtion(HttpSession session){
        UserInfo userInfo=(UserInfo) session.getAttribute(Const.CURRENTUSER);
        if(userInfo==null){
            return ServerResponse.serverResponseByError("用户未登录");
        }
//
        return  ServerResponse.serverResponseBySuccess(userInfo);
    }
    /**
     * 退出登录
     * */
    @RequestMapping(value = "/logout.do")
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENTUSER);
        return  ServerResponse.serverResponseBySuccess();
    }
}
