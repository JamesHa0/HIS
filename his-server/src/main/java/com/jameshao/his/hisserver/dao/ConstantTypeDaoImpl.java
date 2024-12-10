package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Constanttype;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ConstantTypeDaoImpl extends BaseDao implements ConstantTypeDao{

    @Override
    public List<Constanttype> getAll() {
        return this.executeQuery("select * from constanttype where delmark=1",Constanttype.class);
    }

    @Override
    public Constanttype getById(int id) {
        List<Constanttype> list = this.executeQuery("select * from constanttype where delmark=1 and id=?",Constanttype.class,id);
        if(list!=null && list.size()>0){
            return list.get(0);
        } else {
            return null;
        }

    }

    @Override
    public void add(Constanttype type) {
        String sql = "insert into constanttype values(null,?,?,1)";
        this.saveOrUpdate(sql,type.getConstanttypecode(),type.getConstanttypename());
    }

    @Override
    public void update(Constanttype type) {
        String sql = "update constanttype set ConstantTypeCode=?,ConstantTypeName=? where id=?";
        this.saveOrUpdate(sql,type.getConstanttypecode(),type.getConstanttypename(),type.getId());
    }

    @Override
    public void delById(int id) {
        String sql = "update constanttype set delmark=0 where id=?";
        this.saveOrUpdate(sql,id);
    }

    @Override
    public List<Constanttype> queryByKey(String key) {
        String queryByName = "select * from constanttype where delmark=1 and ConstantTypeName like ?";
        String queryByCode = "select * from constanttype where delmark=1 and ConstantTypeCode like ?";
        List<Constanttype> list1 = this.executeQuery(queryByName,Constanttype.class,"%"+key+"%");
        List<Constanttype> list2 = this.executeQuery(queryByCode,Constanttype.class,"%"+key+"%");
        list1.addAll(list2);
        return new ArrayList<Constanttype>(new LinkedHashSet<Constanttype>(list1));
    }
}
