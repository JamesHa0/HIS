package com.jameshao.his.hisserver.service;


import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public interface RegisterService {

    int add(Register register);

    Object[] getAll(int page, int size);

    Object[] search(String searchkey,int page, int size);

    List<Register> getByBelong(int id,String belong,String state);

    int changeState(int id,int state);

}
