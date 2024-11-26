package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.GuahaoService;
import com.jameshao.his.hisserver.service.GuahaoServiceImpl;
import com.jameshao.his.hisserver.vo.Register;
import com.jameshao.his.hisserver.utils.ParamUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/guahao/*")
public class GuahaoServlet extends BaseServlet{

    GuahaoService guahaoService = new GuahaoServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response){
        Register register = ParamUtils.getParam(Register.class,request);
        guahaoService.add(register);

        this.writeSuccessJSON(response,"okk");
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response){
        List<Register> list = guahaoService.getAll();
        this.writeSuccessJSON(response,list);
    }

}
