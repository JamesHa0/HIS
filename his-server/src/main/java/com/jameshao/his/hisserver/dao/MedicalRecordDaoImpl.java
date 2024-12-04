package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Medicalrecord;

import java.util.List;

public class MedicalRecordDaoImpl extends BaseDao implements MedicalRecordDao{
    @Override
    public int insertOne(Medicalrecord medicalrecord) {
        StringBuffer sql = new StringBuffer("insert into MedicalRecord(" +
                "CaseNumber,RegistID,Readme,Present,PresentTreat,History,Allergy, Physique,Proposal,Careful,CheckResult,Diagnosis,Handling,CaseState" +
                ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        int count = this.saveOrUpdate(sql.toString(),medicalrecord.getCasenumber(),
                medicalrecord.getRegistid(),
                medicalrecord.getReadme(),
                medicalrecord.getPresent(),
                medicalrecord.getPresenttreat(),
                medicalrecord.getHistory(),
                medicalrecord.getAllergy(),
                medicalrecord.getPhysique(),
                medicalrecord.getProposal(),
                medicalrecord.getCareful(),
                medicalrecord.getCheckresult(),
                medicalrecord.getDiagnosis(),
                medicalrecord.getHandling(),
                medicalrecord.getCasestate());
        System.out.println("add:"+count);
        return count;
    }

    @Override
    public List<Medicalrecord> getByRegistId(int RegistId) {
        StringBuffer sql = new StringBuffer("select * from MedicalRecord where RegistID=?");
        List<Medicalrecord> list = this.executeQuery(sql.toString(),Medicalrecord.class,RegistId);
        return list;
    }

    @Override
    public int updateByCasenumber(Medicalrecord medicalrecord) {
        StringBuffer sql = new StringBuffer("update MedicalRecord set " +
                "RegistID=?,Readme=?,Present=?,PresentTreat=?,History=?,Allergy=?, Physique=?,Proposal=?,Careful=?,CheckResult=?,Diagnosis=?,Handling=?,CaseState=? " +
                "where CaseNumber=?");
        int count = this.saveOrUpdate(sql.toString(),
                medicalrecord.getRegistid(),
                medicalrecord.getReadme(),
                medicalrecord.getPresent(),
                medicalrecord.getPresenttreat(),
                medicalrecord.getHistory(),
                medicalrecord.getAllergy(),
                medicalrecord.getPhysique(),
                medicalrecord.getProposal(),
                medicalrecord.getCareful(),
                medicalrecord.getCheckresult(),
                medicalrecord.getDiagnosis(),
                medicalrecord.getHandling(),
                medicalrecord.getCasestate(),
                medicalrecord.getCasenumber()
        );
        return count;
    }
}
