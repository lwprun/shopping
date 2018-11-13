package com.neuedu.controller.common.intercepter;

import com.google.gson.Gson;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);

        if (userInfo == null || userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {

            httpServletResponse.reset();
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = httpServletResponse.getWriter();
            if (userInfo == null) {
                ServerResponse serverResponse = ServerResponse.serverResponseByError("需要登录");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            }
            if (userInfo.getRole() != Const.RoleEnum.ROLE_ADMIN.getCode()) {
                ServerResponse serverResponse = ServerResponse.serverResponseByError("无权限操作");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
