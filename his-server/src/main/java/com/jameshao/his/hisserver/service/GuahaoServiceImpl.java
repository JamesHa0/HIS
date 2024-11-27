package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.GuahaoDao;
import com.jameshao.his.hisserver.dao.GuahaoDaoImpl;
import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public class GuahaoServiceImpl implements GuahaoService{

    GuahaoDao dao = new GuahaoDaoImpl();
    @Override
    public int add(Register register) {
        return dao.insertOne(register);
    }

    @Override
    public List<Register> getAll() {
        return dao.queryAll();
    }

    @Override
    public List<Register> search(String name) {
        return dao.queryOneByName(name);
    }
}
