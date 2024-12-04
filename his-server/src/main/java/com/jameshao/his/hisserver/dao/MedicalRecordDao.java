package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Medicalrecord;

import java.util.List;

public interface MedicalRecordDao {
    int insertOne(Medicalrecord medicalrecord);

    List<Medicalrecord> getByRegistId(int RegistId);

    int updateByCasenumber(Medicalrecord medicalrecord);
}
