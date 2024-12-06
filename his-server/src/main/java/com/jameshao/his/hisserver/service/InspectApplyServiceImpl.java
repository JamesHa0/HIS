package com.jameshao.his.hisserver.service;

import com.jameshao.his.hisserver.dao.InspectApplyDao;
import com.jameshao.his.hisserver.dao.InspectApplyDaoImpl;
import com.jameshao.his.hisserver.vo.InspectApply;

import java.util.List;

public class InspectApplyServiceImpl implements InspectApplyService{
    InspectApplyDao dao = new InspectApplyDaoImpl();
    @Override
    public int addManyApply(List<InspectApply> list) {
        return dao.insertMany(list);
    }

    @Override
    public List<InspectApply> getByRegistId(Integer registId) {
        InspectApply apply = new InspectApply();
        apply.setRegistid(registId);
        return dao.queryByCondition(apply);
    }

    @Override
    public int delManyApply(List<Integer> list) {
        return dao.delMany(list);
    }

    @Override
    public int updateResult(int id, String result) {
        return dao.updateResult(id,result);
    }
}
