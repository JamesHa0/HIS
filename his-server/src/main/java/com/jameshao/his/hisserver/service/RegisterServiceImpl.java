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
    public Object[] search(String key, int page, int size) {
        return dao.queryOneByName(key,page,size);
    }

    @Override
    public Object[] getByBelong(int id, String belong, String state, int page, int size) {
        return dao.queryByBelong(id, belong, state,page,size);
    }

    @Override
    public Object[] searchByBelong(int id, String belong, String state, int page, int size, String searchkey) {
        return dao.querySomeByBelong(id, belong, state,page,size,searchkey);
    }


    @Override
    public int changeState(int id, int state) {
        return dao.updateVisitStateById(id, state);
    }

}
