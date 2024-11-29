package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Medicalrecord;

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
}
