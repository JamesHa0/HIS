package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.MedicalRecordService;
import com.jameshao.his.hisserver.service.MedicalRecordServiceImpl;
import com.jameshao.his.hisserver.utils.ParamUtils;
import com.jameshao.his.hisserver.vo.Medicalrecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/record/*")
public class MedicalRecordServlet extends BaseServlet{
    MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
    public void add_one(HttpServletRequest request, HttpServletResponse response){
        Medicalrecord medicalrecord = ParamUtils.getParam(Medicalrecord.class,request);
        System.out.println(medicalrecord);
        int success = medicalRecordService.addOne(medicalrecord);
        System.out.println("success:"+success);
        if(success >= 1){
            this.writeSuccessJSON(response,"添加成功！");
        }else{
            this.writeErrorJSON(response,"添加失败！");
        }
    }
}
