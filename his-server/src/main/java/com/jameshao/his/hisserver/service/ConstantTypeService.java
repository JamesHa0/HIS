package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.List;

public interface ConstantTypeService {
    public List<Constanttype> getAllTypes();
    public void update(Constanttype type);
}
