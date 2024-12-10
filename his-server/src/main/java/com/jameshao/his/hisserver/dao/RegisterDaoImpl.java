package com.jameshao.his.hisserver.dao;

import com.jameshao.his.hisserver.vo.Register;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class RegisterDaoImpl extends BaseDao implements RegisterDao {
    public int insertOne(Register register) {
        String sql = "insert into register(" +
                "casenumber,realname,gender,idnumber,age,visitdate,noon,deptid,userid,registleid,settleid,registtime,registerid,visitstate" +
                ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    	int res = this.saveOrUpdate(sql,
                register.getCasenumber(),
                register.getRealname(),
                register.getGender(),
                register.getIdnumber(),
                register.getAge(),
                new Date(),
                "测试",
                register.getDeptid(),
                register.getUserid(),
                1,
                1,
                new Date(),
                2,
                1
        );
        return res;
    }

    @Override
    public Object[] queryAll(int page, int size) {
        int total = 0;
        String countSql = "SELECT COUNT(*) FROM register";
        String dataSql = "SELECT * FROM register ";
        List<Map<String, Object>> list = this.executeQuery(countSql);
        total = Integer.parseInt(list.get(0).get("COUNT(*)").toString());
        return new Object[]{
                total,
                this.queryByPage(dataSql,Register.class,page,size)
        };
    }

    @Override
    public Object[] queryOneByName(String key,int page, int size) {
        int total = 0;
        String countSql = "SELECT COUNT(*) FROM register where RealName like ? ";
        String dataSql = "select * from register where RealName like ? ";
        List<Map<String, Object>> list = this.executeQuery(countSql,"%"+key+"%");
        total = Integer.parseInt(list.get(0).get("COUNT(*)").toString());
        return new Object[]{
                total,
                this.queryByPage(dataSql,Register.class,page,size,"%"+key+"%")
        };
    }

    @Override
    public Object[] queryByBelong(int id, String belong, String state,int page, int size) {
        int total = 0;
        StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM register where ");
        StringBuffer dataSql = new StringBuffer("select * from register where ");
        if("doc".equals(belong)){
            countSql.append("UserID = "+id);
            dataSql.append("UserID = "+id);
        }else if("dept".equals(belong)){
            countSql.append("DeptID = "+id);
            dataSql.append("DeptID = "+id);
        }
        countSql.append(" and ");
        dataSql.append(" and ");
        if("visit".equals(state)){
            countSql.append("VisitState > 1");
            dataSql.append("VisitState > 1");
        }else if("unvisit".equals(state)){
            countSql.append("VisitState = 1");
            dataSql.append("VisitState = 1");
        }
        System.out.println(countSql.toString());
        System.out.println(dataSql.toString());
        List<Map<String, Object>> list = this.executeQuery(countSql.toString());
        total = Integer.parseInt(list.get(0).get("COUNT(*)").toString());
        return new Object[]{
                total,
                this.queryByPage(dataSql.toString(),Register.class,page,size)
        };
    }

    @Override
    public Object[] querySomeByBelong(int id, String belong, String state, int page, int size, String searchkey) {
        int total = 0;
        StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM register where ");
        StringBuffer dataSql = new StringBuffer("select * from register where ");
        String and = " and ";
        if("doc".equals(belong)){
            countSql.append("UserID = "+id);
            dataSql.append("UserID = "+id);
        }else if("dept".equals(belong)){
            countSql.append("DeptID = "+id);
            dataSql.append("DeptID = "+id);
        }
        countSql.append(and);
        dataSql.append(and);
        if("visit".equals(state)){
            countSql.append("VisitState > 1");
            dataSql.append("VisitState > 1");
        }else if("unvisit".equals(state)){
            countSql.append("VisitState = 1");
            dataSql.append("VisitState = 1");
        }
        countSql.append(and);
        dataSql.append(and);
        countSql.append("RealName like ? ");
        dataSql.append("RealName like ? ");
        System.out.println(countSql.toString());
        System.out.println(dataSql.toString());
        List<Map<String, Object>> list = this.executeQuery(countSql.toString(),"%"+searchkey+"%");
        total = Integer.parseInt(list.get(0).get("COUNT(*)").toString());
        return new Object[]{
                total,
                this.queryByPage(dataSql.toString(),Register.class,page,size,"%"+searchkey+"%")
        };
    }

    @Override
    public int updateVisitStateById(int id, int state) {
        String sql = "update register set VisitState = ? where ID = ?";
        return this.saveOrUpdate(sql,state,id);
    }
}
