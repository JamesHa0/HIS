package com.jameshao.his.hisserver.service;


import com.jameshao.his.hisserver.vo.InspectApply;

import java.util.List;

public interface InspectApplyService {
    int addManyApply(List<InspectApply> list);

    List<InspectApply> getByRegistId (Integer registId);

    int delManyApply(List<Integer> list);

    int updateResult(int id,String result);

    int updateStatusById(int id);
}
