package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public interface GuahaoDao {
    int insertOne(Register register);

    List<Register> queryAll();
}
