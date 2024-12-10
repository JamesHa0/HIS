package com.jameshao.his.hisserver.service;


import com.jameshao.his.hisserver.vo.Register;

import java.util.List;

public interface RegisterService {

    int add(Register register);

    Object[] getAll(int page, int size);

    Object[] search(String searchkey,int page, int size);

    Object[] getByBelong(int id,String belong,String state,int page, int size);

    Object[] searchByBelong(int id,String belong,String state,int page, int size,String searchkey);

    int changeState(int id,int state);

}
