package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.UserDao;
import com.jameshao.his.hisserver.dao.UserDaoImpl;
import com.jameshao.his.hisserver.vo.RouteMeta;
import com.jameshao.his.hisserver.vo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> getRoutesByUsername(String username) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> dataes = dao.getRoutes(username);
        for (Map<String, Object> data : dataes) {
            Long parent_id = (Long) data.get("parent_id");
            if ( parent_id == 0 ) { //一级菜单
                Map<String,Object> tmp = new HashMap<>();
                tmp.put("component", data.get("component"));
                RouteMeta meta = new RouteMeta((String)data.get("name"),(String)data.get("icon"),!(Boolean) data.get("visible"),true,(String)data.get("params"));
                tmp.put("meta", meta);
                tmp.put("name", data.get("route_name"));
                tmp.put("path", data.get("route_path"));
                tmp.put("redirect", data.get("redirect"));
                //children:在查询结果中找parent_id=当前id的记录作为children
                List<Map<String, Object>> children = new ArrayList<>();
                for (Map<String, Object> dd : dataes){
                    if( ((Long) dd.get("parent_id")).intValue() == (Integer) data.get("id")){
                        Map<String, Object> child = new HashMap<>();
                        child.put("component", dd.get("component"));
                        RouteMeta mm = new RouteMeta((String)dd.get("name"),(String)dd.get("icon"),!(Boolean) dd.get("visible"),true,(String)dd.get("params"));
                        child.put("meta", mm);
                        child.put("name", dd.get("route_name"));
                        child.put("path", dd.get("route_path"));
                        children.add(child);
                    }
                }
                tmp.put("children", children);
                list.add(tmp);
            }
        }
        return list;
    }
}
