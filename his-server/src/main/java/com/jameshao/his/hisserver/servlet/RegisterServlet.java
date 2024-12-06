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

    void changeState(HttpServletRequest request, HttpServletResponse response){
    	String sid = request.getParameter("regist_id");
    	String sstate = request.getParameter("state");
    	System.out.println("id:"+sid);
    	System.out.println("state:"+sstate);

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
