package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.UserDao;
import com.jameshao.his.hisserver.dao.UserDaoImpl;
import com.jameshao.his.hisserver.vo.User;

public class UserServiceImpl implements UserService{

    UserDao dao = new UserDaoImpl();
    @Override
    public int login(String username, String password) {
        User user = dao.getByUsername(username);
        if(user == null){
            return -1;//用户名不存在
        }else {
            if(user.getPassword().equals(password)){
                System.out.println("用户名密码正确");
                return 0;//用户名密码正确
            }else {
                return -2;//密码错误
            }
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return dao.getByUsername(username);
    }
}
