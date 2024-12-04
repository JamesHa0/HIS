package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.InspectApply;

import java.util.List;

public interface InspectApplyDao {
    int insertMany(List<InspectApply> list);
}
