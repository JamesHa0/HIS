package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.ConstantTypeDao;
import com.jameshao.his.hisserver.dao.ConstantTypeDaoImpl;
import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.List;

public class ConstantTypeServiceImpl implements ConstantTypeService{

    ConstantTypeDao dao = new ConstantTypeDaoImpl();
    @Override
    public List<Constanttype> getAllTypes() {
        return dao.getAll();
    }

    @Override
    public void update(Constanttype type) {
        dao.update(type);
    }
}
