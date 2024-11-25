package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Constantitem;

import java.util.List;

public class ConstantItemDaoImpl extends BaseDao implements ConstantItemDao{
    @Override
    public List getByTypeId(int id) {
        List<Constantitem> list = this.executeQuery("select * from constantitem where delmark=1 and constanttypeid=?",Constantitem.class,id);
        return list;
    }
}
