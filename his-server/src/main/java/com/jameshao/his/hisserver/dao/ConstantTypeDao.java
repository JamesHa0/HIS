package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.List;

public interface ConstantTypeDao {
    public List<Constanttype> getAll();

    public Constanttype getById(int id);

    public void add(Constanttype type);

    public void update(Constanttype type);

    public void delById(int id);



}
