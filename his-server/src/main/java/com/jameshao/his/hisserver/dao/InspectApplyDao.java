package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.InspectApply;

import java.util.List;

public interface InspectApplyDao {
    int insertMany(List<InspectApply> list);

    List<InspectApply> queryByCondition(InspectApply apply);

    int delMany(List<Integer> list);

    int updateResult(int id,String result);
}
