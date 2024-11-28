package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public interface RegisterDao {
    int insertOne(Register register);

    List<Register> queryAll();

    List<Register> queryOneByName(String name);
}
