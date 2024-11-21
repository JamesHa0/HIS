package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User getByUsername(String username);
    public List< Map<String,Object> > getRoutes(String username);  //返回菜单权限
}
