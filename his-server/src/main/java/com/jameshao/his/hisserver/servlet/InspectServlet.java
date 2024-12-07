package com.jameshao.his.hisserver.servlet;

import com.alibaba.fastjson2.JSONArray;
import com.jameshao.his.hisserver.service.InspectApplyService;
import com.jameshao.his.hisserver.service.InspectApplyServiceImpl;
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
    InspectApplyService inspectApplyService = new InspectApplyServiceImpl();

    void get_all(HttpServletRequest request, HttpServletResponse response){
        List<Inspectitem> list = inspectService.getAll();
        this.writeSuccessJSON(response,list);
    }

    void get_by_registid(HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("regist_id");
        Integer registId =null;
        if(sid!=null && sid.trim().length() >0){
            registId = Integer.parseInt(sid);
            List<InspectApply> list = inspectApplyService.getByRegistId(registId);
            this.writeSuccessJSON(response,list);
        }else {
            this.writeErrorJSON(response,"未获得患者检查申请！");
        }
    }
    void add_many_apply(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("data");
        System.out.println(param);
        JSONArray applies = JSONArray.parseArray(param);
        // 将数据转为对象存入list
        List<InspectApply> list = new ArrayList();
        for(int i = 0;i < applies.size(); i++){
            InspectApply apply = applies.getObject(i, InspectApply.class);
            list.add(apply);
        }
        int result = inspectApplyService.addManyApply(list);
        this.writeSuccessJSON(response,result);
    }
    void delete_many_apply(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("data");
        JSONArray applies = JSONArray.parseArray(param);
        // 将数据存入list
        List<Integer> itemIdList = new ArrayList<>();
        for (int i = 0; i < applies.size(); i++) {
            itemIdList.add((Integer) applies.get(i));
        }
        int result = inspectApplyService.delManyApply(itemIdList);
        this.writeSuccessJSON(response, result);
    }

    void update_result(HttpServletRequest request, HttpServletResponse response){
        String result = request.getParameter("result");
        String id = request.getParameter("itemid");
        Integer iid = 0;
        if(id!=null && id.trim().length() >0){
            iid = Integer.parseInt(id);
        }
        int res = inspectApplyService.updateResult(iid,result);
        if (res == 1){
            this.writeSuccessJSON(response,"检查结果更新成功！");
        }else {
            this.writeErrorJSON(response,"检查结果更新失败！");
        }
    }

    void pay (HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("regist_id");
        Integer iid = 0;
        if(id!=null && id.trim().length() >0){
            iid = Integer.parseInt(id);
        }
        int res = inspectApplyService.updateStatusById(iid);
        if (res >= 1){
            this.writeSuccessJSON(response,"支付成功！");
        }else {
            this.writeErrorJSON(response,"支付失败！");
        }
    }
}
