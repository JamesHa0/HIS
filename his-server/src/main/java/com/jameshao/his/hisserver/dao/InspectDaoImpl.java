package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Inspectitem;

import java.util.List;

public class InspectDaoImpl extends BaseDao implements InspectDao{
    @Override
    public List<Inspectitem> queryAll() {
        String sql = "select * from inspectitem";
        return executeQuery(sql,Inspectitem.class);
    }
}
