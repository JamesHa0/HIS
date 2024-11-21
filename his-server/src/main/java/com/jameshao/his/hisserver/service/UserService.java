package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.vo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public int login( String username,String password);
    public User getUserByUsername(String username);
    public List<Map<String,Object>> getRoutersByUsername(String username);
}
