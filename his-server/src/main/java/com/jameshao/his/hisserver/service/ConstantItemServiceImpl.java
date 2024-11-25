package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.ConstantItemDao;
import com.jameshao.his.hisserver.dao.ConstantItemDaoImpl;

import java.util.List;

public class ConstantItemServiceImpl implements ConstantItemService{
    ConstantItemDao dao = new ConstantItemDaoImpl();
    @Override
    public List getByTypeId(int id) {
        return dao.getByTypeId(id);
    }
}
