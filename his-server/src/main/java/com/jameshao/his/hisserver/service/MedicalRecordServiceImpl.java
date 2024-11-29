package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.MedicalRecordDao;
import com.jameshao.his.hisserver.dao.MedicalRecordDaoImpl;
import com.jameshao.his.hisserver.vo.Medicalrecord;

public class MedicalRecordServiceImpl implements MedicalRecordService{

    MedicalRecordDao MedicalRecordDao = new MedicalRecordDaoImpl();
    @Override
    public int addOne(Medicalrecord medicalrecord) {
        return MedicalRecordDao.insertOne(medicalrecord);
    }
}
