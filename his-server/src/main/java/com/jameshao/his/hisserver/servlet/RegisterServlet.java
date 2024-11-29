package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.RegisterService;
import com.jameshao.his.hisserver.service.RegisterServiceImpl;
import com.jameshao.his.hisserver.vo.Register;
import com.jameshao.his.hisserver.utils.ParamUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/register/*")
public class RegisterServlet extends BaseServlet{

    RegisterService registerService = new RegisterServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response){
        Register register = ParamUtils.getParam(Register.class,request);
        registerService.add(register);

        this.writeSuccessJSON(response,"okk");
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response){
        List<Register> list = registerService.getAll();
        this.writeSuccessJSON(response,list);
    }

    public void search(HttpServletRequest request, HttpServletResponse response){
    	String search_name = request.getParameter("search_name");
        System.out.println("search_name:"+search_name);
    	List<Register> list = registerService.search(search_name);
    	this.writeSuccessJSON(response,list);
    }

    public void getByBelong(HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("id");
        String belong = request.getParameter("belong");
        String state = request.getParameter("state");
        System.out.println("----------------");
        System.out.println("id:"+sid);
        System.out.println("belong:"+belong);
        System.out.println("state:"+state);
        Integer id = null;
        if (sid != null && (!"".equals(sid))){
            id = Integer.parseInt(sid);
        }
        List<Register> list = registerService.getByBelong(id,belong,state);
        this.writeSuccessJSON(response,list);
    }

}
