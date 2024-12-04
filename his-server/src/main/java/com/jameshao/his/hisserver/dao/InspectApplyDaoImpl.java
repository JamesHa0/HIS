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
}
