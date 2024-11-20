package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.UserService;
import com.jameshao.his.hisserver.service.UserServiceImpl;
import com.jameshao.his.hisserver.vo.ResultCode;
import com.jameshao.his.hisserver.vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/user/*")
public class UserServlet extends BaseServlet{
    UserService service = new UserServiceImpl();

    void me(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        if(username == null || username.trim().length()<=0){
            this.writeErrorJSON(response,"请求参数不合法");
        }else {
            User user = service.getUserByUsername(username);
            if(user == null){
                this.writeErrorJSON(response,"用户名不存在，无法查询用户信息");
            }else {
                this.writeSuccessJSON(response,user);
            }
        }
    }
    void login(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException,IllegalAccessException {
        System.out.println("【】进入login方法");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int ret = service.login(username,password);

        switch (ret){
            case 0:
//                this.writeJSON(response, ResultCode.SUCCESS,"",null);
                this.writeSuccessJSON(response,null);
                break;
            case -1:
//                this.writeJSON(response,ResultCode.ERROR,"用户名不存在",null);
                this.writeErrorJSON(response,"用户名不存在");
                break;
            case -2:
//                this.writeJSON(response,ResultCode.ERROR,"密码错误",null);
                this.writeErrorJSON(response,"密码错误");
                break;
        }

    }
}
