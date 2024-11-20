package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao{

    @Override
    public User getByUsername(String username) {
        List<User> users = this.executeQuery("select * from user where username=?",User.class,username);
        if(users == null || users.size() == 0){
            return null;
        } else {
            return users.get(0);
        }
    }


}
