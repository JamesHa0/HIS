package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.List;

public interface ConstantTypeService {
    List<Constanttype> getAllTypes();
    void update(Constanttype type);

    void add(Constanttype type);

    void delById(int id);
}
