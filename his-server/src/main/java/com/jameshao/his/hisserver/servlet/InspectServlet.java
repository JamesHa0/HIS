package com.jameshao.his.hisserver.servlet;

import com.alibaba.fastjson2.JSONArray;
import com.jameshao.his.hisserver.service.InspectService;
import com.jameshao.his.hisserver.service.InspectServiceImpl;
import com.jameshao.his.hisserver.vo.InspectApply;
import com.jameshao.his.hisserver.vo.Inspectitem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/inspect/*")
public class InspectServlet extends BaseServlet{
    InspectService inspectService = new InspectServiceImpl();

    void get_all(HttpServletRequest request, HttpServletResponse response){
        List<Inspectitem> list = inspectService.getAll();
        this.writeSuccessJSON(response,list);
    }
    void add_many_apply(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("data");
        System.out.println(param);
        JSONArray applies = JSONArray.parseArray(param);
        // 将数据转为对象存入list
        List<InspectApply> list = new ArrayList();
        for(int i = 0;i < applies.size(); i++){
            InspectApply apply = applies.getObject(i, InspectApply.class);
        }

        //todo:service
        this.writeSuccessJSON(response,"添加成功！");
    }
}
