package com.jameshao.his.hisserver.servlet;

import com.alibaba.fastjson2.JSON;
import com.jameshao.his.hisserver.vo.Result;
import com.jameshao.his.hisserver.vo.ResultCode;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        //设置跨域访问
        /* 允许跨域的主机地址 */
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "5000");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "token");
        resp.setHeader("Access-Control-Expose-Headers", "token");




            //Access-Control-Expose-Headers否可以向请求额外暴露的响应头

        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");


        //解决BeanUtils无法将前端传入的"yyyy-MM-dd"格式的日期转换为javabean中的java.util.Date
        DateConverter converter = new DateConverter();
        converter.setPattern(new String("yyyy-MM-dd"));
        ConvertUtils.register(converter, Date.class);


        // 1. 这里获取URL或者URI都可以，URI: /user/login    URL: http://localhost:8080/user/login
        String requestURI = req.getRequestURI();
        // 2. 获取最后`/`的索引
        int beginIndex = requestURI.lastIndexOf("/");
        // 3. 使用substring，获取方法名称
        String methodName = requestURI.substring(beginIndex + 1);
        try {
            /***
             * 记住谁调用了“service”方法，this就是谁，
             * 因为我们自己编写的UserServlet继承了BaseServlet
             * 因此，这个service方法也是属于UserServlet的
             * 而前端访问的是UserServlet
             * 因此 this 是 UserServlet的一个对象
             *
             * 这里 根据 方法名称 和 方法参数的class类型 ，利用反射获取UserServlet的该方法。
             ***/
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 5. 使用this调用该方法。
            method.invoke(this, req, resp);

        }
        //java7新特性 ： 同时捕获多个异常（这些异常不能有父子关系）
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    void writeJSON(HttpServletResponse resp,Integer code,String message,Object data) {
        //res.code  res.message   res.data
        Result r = new Result(code,message,data);
        try{
            resp.getWriter().println(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void writeSuccessJSON(HttpServletResponse resp,Object data){
        Result r = new Result(ResultCode.SUCCESS,"",data);
        try{
            resp.getWriter().println(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeErrorJSON(HttpServletResponse resp, String msg){
        Result r = new Result(ResultCode.ERROR,msg,null);
        try{
            resp.getWriter().println(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
