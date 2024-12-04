package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.vo.Medicalrecord;

import java.util.List;

public interface MedicalRecordService {
    int addOne(Medicalrecord medicalrecord);

    List<Medicalrecord> getByRegistId(int RegistId);

    int updateByCasenumber (Medicalrecord medicalrecord);
}
