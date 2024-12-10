package com.jameshao.his.hisserver.servlet;

import com.jameshao.his.hisserver.service.MedicalRecordService;
import com.jameshao.his.hisserver.service.MedicalRecordServiceImpl;
import com.jameshao.his.hisserver.utils.ParamUtils;
import com.jameshao.his.hisserver.vo.Medicalrecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/record/*")
public class MedicalRecordServlet extends BaseServlet{
    MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
    public void add_one(HttpServletRequest request, HttpServletResponse response){
        Medicalrecord medicalrecord = ParamUtils.getParam(Medicalrecord.class,request);
        System.out.println(medicalrecord);
        int success = medicalRecordService.addOne(medicalrecord);
        System.out.println("success:"+success);
        if(success >= 1){
            this.writeSuccessJSON(response,"添加诊断成功！");
        }else{
            this.writeErrorJSON(response,"添加诊断失败！");
        }
    }
    public void get_one_by_regist (HttpServletRequest request, HttpServletResponse response){
        String sid = request.getParameter("regist_id");
        Integer RegistId =null;
        if(sid!=null && sid.trim().length() >0){
            RegistId = Integer.parseInt(sid);
            List<Medicalrecord> list = medicalRecordService.getByRegistId(RegistId);
            if(list.size()==0){
                this.writeSuccessJSON(response,null);
            }else{
                this.writeSuccessJSON(response,list.get(0));
            }
        }else {
            this.writeErrorJSON(response,"未获得患者病历！");
        }
    }
    public void update_by_casenumber(HttpServletRequest request, HttpServletResponse response){
        Medicalrecord medicalrecord = ParamUtils.getParam(Medicalrecord.class,request);
        String casenumber = medicalrecord.getCasenumber();
        int success = medicalRecordService.updateByCasenumber(medicalrecord);
        if(success >= 1){
            this.writeSuccessJSON(response,"修改诊断成功！");
        }else {
            this.writeErrorJSON(response,"修改诊断失败！");
        }

    }
}
