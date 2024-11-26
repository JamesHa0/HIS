package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.dao.BaseDao;
import com.jameshao.his.hisserver.service.ConstantTypeService;
import com.jameshao.his.hisserver.service.ConstantTypeServiceImpl;
import com.jameshao.his.hisserver.vo.Constanttype;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/constanttype/*")
public class ConstantTypeServlet extends BaseServlet {
    ConstantTypeService service = new ConstantTypeServiceImpl();
    void getAll(HttpServletRequest request, HttpServletResponse response){
        List<Constanttype> list = service.getAllTypes();
        this.writeSuccessJSON(response,list);
    }
    void update(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String constanttypecode = request.getParameter("constanttypecode");
        String constanttypename = request.getParameter("constanttypename");
        Integer iid = 0;
        if(id!=null && id.trim().length() >0){
            iid = Integer.parseInt(id);
        }
        Constanttype type = new Constanttype(iid,constanttypecode,constanttypename,1);
        service.update(type);

        this.writeSuccessJSON(response,null);
    }
    void add(HttpServletRequest request, HttpServletResponse response){
        String constanttypecode = request.getParameter("constanttypecode");
        String constanttypename = request.getParameter("constanttypename");
        System.out.println("constanttypecode:"+constanttypecode);
        System.out.println("constanttypename:"+constanttypename);
        Constanttype type = new Constanttype(null,constanttypecode,constanttypename,1);
        service.add(type);

        this.writeSuccessJSON(response,"ok");
    }
    void delete(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Integer iid = 0;
        if(id!=null && id.trim().length() >0){
            iid = Integer.parseInt(id);
        }
        service.delById(iid);

        this.writeSuccessJSON(response,null);
    }
}
