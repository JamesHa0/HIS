package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.User;

public interface UserDao {
    public User getByUsername(String username);
    
}
