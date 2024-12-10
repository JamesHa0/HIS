package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.RegisterService;
import com.jameshao.his.hisserver.service.RegisterServiceImpl;
import com.jameshao.his.hisserver.vo.Register;
import com.jameshao.his.hisserver.utils.ParamUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/register/*")
public class RegisterServlet extends BaseServlet{

    RegisterService registerService = new RegisterServiceImpl();
    public void add(HttpServletRequest request, HttpServletResponse response){
        Register register = ParamUtils.getParam(Register.class,request);
        registerService.add(register);

        this.writeSuccessJSON(response,"okk");
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response){
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        int page = 1;
        int size = 10; // 设置默认每页数量为10
        if (pageStr!= null &&!pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        if (sizeStr!= null &&!sizeStr.isEmpty()) {
            size = Integer.parseInt(sizeStr);
        }
//        System.out.println("page:"+page);
//        System.out.println("size:"+size);
        Object[] result = registerService.getAll(page, size);
        int total = (int) result[0];
        List<Register> list = (List<Register>) result[1];
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("total", total);
        responseData.put("list", list);
        this.writeSuccessJSON(response, responseData);
    }

    public void search(HttpServletRequest request, HttpServletResponse response){
    	String searchkey = request.getParameter("searchkey");
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        int page = 1;
        int size = 10; // 设置默认每页数量为10
        if (pageStr!= null &&!pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        if (sizeStr!= null &&!sizeStr.isEmpty()) {
            size = Integer.parseInt(sizeStr);
        }
//        System.out.println("searchkey:"+searchkey);
//        System.out.println("page:"+page);
//        System.out.println("size:"+size);
        Object[] result = registerService.search(searchkey,page, size);
        int total = (int) result[0];
        List<Register> list = (List<Register>) result[1];
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("total", total);
        responseData.put("list", list);
    	this.writeSuccessJSON(response,responseData);
    }

    public void getByBelong(HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("id");
        String belong = request.getParameter("belong");
        String state = request.getParameter("state");
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        int page = 1;
        int size = 10; // 设置默认每页数量为10
        if (pageStr!= null &&!pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        if (sizeStr!= null &&!sizeStr.isEmpty()) {
            size = Integer.parseInt(sizeStr);
        }
//        System.out.println("----------------");
//        System.out.println("page:"+page);
//        System.out.println("size:"+size);
//        System.out.println("id:"+sid);
//        System.out.println("belong:"+belong);
//        System.out.println("state:"+state);
        Integer id = null;
        if (sid != null && (!"".equals(sid))){
            id = Integer.parseInt(sid);
        }
        Object[] result = registerService.getByBelong(id,belong,state,page,size);
        int total = (int) result[0];
        List<Register> list = (List<Register>) result[1];
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("total", total);
        responseData.put("list", list);
        this.writeSuccessJSON(response, responseData);
    }

    void searchByBelong(HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("id");
        String searchkey = request.getParameter("searchkey");
        String belong = request.getParameter("belong");
        String state = request.getParameter("state");
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        int page = 1;
        int size = 10; // 设置默认每页数量为10
        if (pageStr!= null &&!pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        if (sizeStr!= null &&!sizeStr.isEmpty()) {
            size = Integer.parseInt(sizeStr);
        }
        Integer id = null;
        if (sid != null && (!"".equals(sid))){
            id = Integer.parseInt(sid);
        }
//        System.out.println("page:"+page);
//        System.out.println("size:"+size);
//        System.out.println("id:"+sid);
//        System.out.println("belong:"+belong);
//        System.out.println("state:"+state);
//        System.out.println("searchkey:"+searchkey);
        Object[] result = registerService.searchByBelong(id,belong,state,page,size,searchkey);
        int total = (int) result[0];
        List<Register> list = (List<Register>) result[1];
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("total", total);
        responseData.put("list", list);
        this.writeSuccessJSON(response, responseData);
    }

    void changeState(HttpServletRequest request, HttpServletResponse response){
    	String sid = request.getParameter("regist_id");
    	String sstate = request.getParameter("state");
//    	System.out.println("id:"+sid);
//    	System.out.println("state:"+sstate);

        if(sid==null || "".equals(sid)){
            this.writeErrorJSON(response,"无法修改患者状态：无法获取患者ID");
            return;
        }
        if (sstate==null || "".equals(sstate)){
            this.writeErrorJSON(response,"无法修改患者状态：无法获取当前状态");
            return;
        }

        int state = 0;
        if("未就诊".equals(sstate)){
            state = 1;
    	}else if("未缴费".equals(sstate)){
            state = 2;
        }else if("未检查".equals(sstate)){
            state = 3;
        }else if("已检查".equals(sstate)){
            state = 4;
        }else if("已退费".equals(sstate)){
            state = 5;
        }else{
            this.writeErrorJSON(response,"无法修改患者状态：非法状态码");
            return;
        }

        int id = Integer.parseInt(sid);

        int result = registerService.changeState(id,state);

        if(result==1){
            this.writeSuccessJSON(response,"患者状态已更新");
        }else{
            this.writeErrorJSON(response,"无法修改患者状态：操作失败");
        }
    }

}
