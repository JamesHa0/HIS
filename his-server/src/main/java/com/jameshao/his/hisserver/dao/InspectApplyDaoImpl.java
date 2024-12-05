package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.InspectApply;

import java.util.List;

public class InspectApplyDaoImpl extends BaseDao implements InspectApplyDao{
    @Override
    public int insertMany(List<InspectApply> list) {
        int result = 0;
        for(int i = 0; i < list.size(); i++){
            InspectApply entity = list.get(i);

            StringBuffer sql = new StringBuffer("insert into inspect_apply(" +
                    "inspectid, name, registid, price, status" +
                    ") values (?, ?, ?, ?, ?)");
            this.saveOrUpdate(sql.toString(),
                    entity.getInspectid(),
                    entity.getName(),
                    entity.getRegistid(),
                    entity.getPrice(),
                    entity.getStatus()
            );
            result ++;
        }
        return result;
    }

    @Override
    public List<InspectApply> queryByCondition(InspectApply apply) {
        StringBuffer sql = new StringBuffer("select * from inspect_apply where 1=1 ");

        Integer inspectid = apply.getInspectid();
        if(inspectid != null){
            sql.append(" and inspectid= ").append(inspectid);
        }

        String name = apply.getName();
        if(name != null && (!name.isEmpty())){
            sql.append(" and name like '%").append(name).append("%'");
        }

        Integer registid = apply.getRegistid();
        if(registid != null){
            sql.append(" and registid= ").append(registid);
        }

        if(apply.getPrice() != null){
            Float price = apply.getPrice().floatValue();
            sql.append(" and price= ").append(price);
        }

        String status = apply.getStatus();
        if(status != null && (!status.isEmpty())){
            sql.append(" and status like '%").append(status).append("%'");
        }

        String result = apply.getResult();
        if(result != null && (!result.isEmpty())){
            sql.append(" and result like '%").append(result).append("%'");
        }

        System.out.println(sql.toString());
        return this.executeQuery(sql.toString(), InspectApply.class);
    }

    @Override
    public int delMany(List<Integer> list) {
        int result = 0;
        StringBuffer sql = new StringBuffer("update inspect_apply set status = 0 where itemid = ?");
        for(Integer itemid : list){
            this.saveOrUpdate(sql.toString(),itemid);
            result ++;
        }
        return result;
    }
}
