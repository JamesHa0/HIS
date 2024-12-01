package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.InspectService;
import com.jameshao.his.hisserver.service.InspectServiceImpl;
import com.jameshao.his.hisserver.vo.Inspectitem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/inspect/*")
public class InspectServlet extends BaseServlet{
    InspectService inspectService = new InspectServiceImpl();

    void get_all(HttpServletRequest request, HttpServletResponse response){
        List<Inspectitem> list = inspectService.getAll();
        this.writeSuccessJSON(response,list);
    }
}
