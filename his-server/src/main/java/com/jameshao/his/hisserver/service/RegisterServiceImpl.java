package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.RegisterDao;
import com.jameshao.his.hisserver.dao.RegisterDaoImpl;
import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public class RegisterServiceImpl implements RegisterService {

    RegisterDao dao = new RegisterDaoImpl();
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

    @Override
    public List<Register> getByBelong(int id, String belong, String state) {
        return dao.queryByBelong(id, belong, state);
    }

}
