package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.List;

public interface ConstantTypeDao {
    List<Constanttype> getAll();

    Constanttype getById(int id);

    void add(Constanttype type);

    void update(Constanttype type);

    void delById(int id);

    List<Constanttype> queryByKey(String key);

}
