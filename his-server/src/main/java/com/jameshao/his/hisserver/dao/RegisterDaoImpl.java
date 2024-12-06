package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class RegisterDaoImpl extends BaseDao implements RegisterDao {
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
                "上午或下午？？？还在测试中",
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
    public Object[] queryAll(int page, int size) {
        int total = 0;
        // 计算偏移量，用于分页查询
        int offset = (page - 1) * size;
        String countSql = "SELECT COUNT(*) FROM register";
        String dataSql = "SELECT * FROM register LIMIT ?,?";
        List<Map<String, Object>> list = this.executeQuery(countSql);
        total = Integer.parseInt(list.get(0).get("COUNT(*)").toString());
        return new Object[]{
                total,
                this.executeQuery(dataSql,Register.class,offset,size)
        };
    }

    @Override
    public List<Register> queryOneByName(String name) {
        return this.executeQuery("select * from register where RealName like ?",Register.class,"%"+name+"%");
    }

    @Override
    public List<Register> queryByBelong(int id, String belong, String state) {
        System.out.println("id:"+id);
        System.out.println("belong:"+belong);
        System.out.println("state:"+state);
        StringBuffer sql = new StringBuffer("select * from register where ");
        if("doc".equals(belong)){
            sql.append("UserID = "+id);
        }else if("dept".equals(belong)){
            sql.append("DeptID = "+id);
        }
        sql.append(" and ");
        if("visit".equals(state)){
            sql.append("VisitState > 1");
        }else if("unvisit".equals(state)){
            sql.append("VisitState = 1");
        }
        System.out.println(sql.toString());
        return this.executeQuery(sql.toString(),Register.class);
    }

    @Override
    public int updateVisitStateById(int id, int state) {
        String sql = "update register set VisitState = ? where ID = ?";
        return this.saveOrUpdate(sql,state,id);
    }
}
