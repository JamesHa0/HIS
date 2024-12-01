package com.jameshao.his.hisserver.service;


import com.jameshao.his.hisserver.dao.InspectDao;
import com.jameshao.his.hisserver.dao.InspectDaoImpl;
import com.jameshao.his.hisserver.vo.Inspectitem;

import java.util.List;

public class InspectServiceImpl implements InspectService{
    InspectDao inspectDao = new InspectDaoImpl();


    @Override
    public List<Inspectitem> getAll() {
        return inspectDao.queryAll();
    }
}
