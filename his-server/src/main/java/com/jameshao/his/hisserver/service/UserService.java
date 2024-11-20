package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.vo.User;

public interface UserService {
    public int login( String username,String password);
    public User getUserByUsername(String username);
}
