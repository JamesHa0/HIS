package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.User;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> getRoutes(String username) {
        String sql = "select m.* from user u,role r,user_role ur,menu m,role_menu rm where u.ID = ur.user_id and ur.role_id = r.id and r.id = rm.role_id and rm.menu_id = m.id and username = ?"+username;
        return this.executeQuery(sql,username);
    }


}
