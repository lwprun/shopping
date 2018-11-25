package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

public interface IUserService {
/**
     * 登录接口
     * */
    ServerResponse login(String username, String password);



    /**
     * 注册接口
     * */
    ServerResponse register(UserInfo userInfo);

    /**
     * 根据用户名查询密保问题
     * */
    ServerResponse  forgetGetQuestion(String username);

    /**
     * 提交问题答案
     * */
    ServerResponse forgetCheckAnwser(String username, String question, String anwser);

    /**
     * 重置密码
     * */
    ServerResponse forgetResetAnwser(String username, String passwordNew, String forgetToken);
    /**
     * 校验用户名或邮箱是否有效
     * */
    ServerResponse checkValid(String str, String type);
    /**
     *  登录中状态重置密码
     * */
    ServerResponse resetPassword(String username, String passwordOld, String passwordNew);

    /**
     * 登录状态下更新个人信息
     * */
    ServerResponse updateInformation(UserInfo user);

    /**
     * 根据userid查询用户信息
     * */
    UserInfo findUserInfoByUserid(Integer userId);


    /**
     * 保存用户token信息
     * */
    int updateTokenByUserId(Integer userId, String token);

    /**
     * 根据token查询用户信息
     * */
    UserInfo findUserInfoByToken(String token);
}
