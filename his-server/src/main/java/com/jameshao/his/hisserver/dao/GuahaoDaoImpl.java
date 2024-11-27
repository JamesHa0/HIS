package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.Date;
import java.util.List;

public class GuahaoDaoImpl extends BaseDao implements GuahaoDao{
    public int insertOne(Register register) {
        String sql = "insert into register(" +
                "casenumber,realname,gender,idnumber,age,visitdate,noon,deptid,userid,registleid,settleid,registtime,registerid" +
                ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    	int res = this.saveOrUpdate(sql,
                register.getCasenumber(),
                register.getRealname(),
                register.getGender(),
                register.getIdnumber(),
                register.getAge(),
                new Date(),
                "晚上",
                2,
                11,
                1,
                1,
                new Date(),
                2
        );
        return res;
    }

    @Override
    public List<Register> queryAll() {
        return this.executeQuery("select * from register",Register.class);
    }

    @Override
    public List<Register> queryOneByName(String name) {
        return this.executeQuery("select * from register where RealName like ?",Register.class,"%"+name+"%");
    }
}
