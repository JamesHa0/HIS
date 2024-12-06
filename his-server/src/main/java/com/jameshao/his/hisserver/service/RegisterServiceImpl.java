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
    public Object[] getAll(int page, int size) {
        return dao.queryAll(page, size);
    }

    @Override
    public List<Register> search(String name) {
        return dao.queryOneByName(name);
    }

    @Override
    public List<Register> getByBelong(int id, String belong, String state) {
        return dao.queryByBelong(id, belong, state);
    }

    @Override
    public int changeState(int id, int state) {
        return dao.updateVisitStateById(id, state);
    }

}
