package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.MedicalRecordDao;
import com.jameshao.his.hisserver.dao.MedicalRecordDaoImpl;
import com.jameshao.his.hisserver.vo.Medicalrecord;

import java.util.List;

public class MedicalRecordServiceImpl implements MedicalRecordService{

    MedicalRecordDao MedicalRecordDao = new MedicalRecordDaoImpl();
    @Override
    public int addOne(Medicalrecord medicalrecord) {
        return MedicalRecordDao.insertOne(medicalrecord);
    }

    @Override
    public List<Medicalrecord> getByRegistId(int RegistId) {
        return MedicalRecordDao.getByRegistId(RegistId);
    }

    @Override
    public int updateByCasenumber(Medicalrecord medicalrecord) {
        return MedicalRecordDao.updateByCasenumber(medicalrecord);
    }
}
