package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.ConstantItemService;
import com.jameshao.his.hisserver.service.ConstantItemServiceImpl;
import com.jameshao.his.hisserver.vo.Constantitem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/constantitem/*")
public class ConstantItemServlet extends BaseServlet{
    ConstantItemService constantItemService = new ConstantItemServiceImpl();
    void get_gender (HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("id");
        Integer id =null;
        if(sid!=null && sid.trim().length() >0){
            id = Integer.parseInt(sid);
        }
        List<Constantitem> list = new ArrayList();


        this.writeSuccessJSON(response,constantItemService.getByTypeId(id));
    }
}
