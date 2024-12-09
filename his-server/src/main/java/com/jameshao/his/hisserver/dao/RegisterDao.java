package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public interface RegisterDao {
    int insertOne(Register register);

    Object[] queryAll(int page, int size);

    Object[] queryOneByName(String key, int page, int size);

    List<Register> queryByBelong(int id,String belong,String state);

    int updateVisitStateById(int id,int state);
}
