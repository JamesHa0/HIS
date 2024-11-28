package com.jameshao.his.hisserver.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
    private Connection conn = null;
    //private ResultSet rs = null;
    //private PreparedStatement pst = null;

	public List<Map<String, Object>> executeQuery(String sql,Object... args)  {
        conn = DbHelper.getConnection();
        List<Map<String, Object>> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            int index = 1;
            pst = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1,args[i]);
            }
            rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int cols_len = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap();
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = metaData.getColumnName(i + 1);
                    Object cols_value = rs.getObject(cols_name);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbHelper.closeConn(conn,rs,pst);
        }
        return list;
    }
    /**
     * 查询，返回List
     */

    public <T> List<T> executeQuery(String sql,Class<T> clazz,Object... args){
        conn = DbHelper.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try{

            pst = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1,args[i]);
            }
            rs = pst.executeQuery();
            Field[] fs = clazz.getDeclaredFields();
            String[] colNames = new String[fs.length];
            String[] rTypes = new String[fs.length];
            Method[] methods = clazz.getMethods();
            while(rs.next()){
                for (int i = 0; i < fs.length; i++) {
                    Field f = fs[i];
                    String colName = f.getName().substring(0,1).toUpperCase() + f.getName().substring(1);
                    colNames[i] = colName;
                    String rType = f.getType().getSimpleName();
                    rTypes[i] = rType;
                }
                Object obj = (T) clazz.newInstance();
                for (int i = 0; i < colNames.length; i++) {
                    String colName = colNames[i];
                    String methodName = "set"+colName;
                    //System.out.println("methodName="+methodName);
                    //查找并调用对应的setter方法
                    for( Method m : methods ){
                        if(methodName.equals(m.getName())){
                            if("int".equals(rTypes[i]) || "Integer".equals(rTypes[i]) ){
//                                System.out.println("int类型:列名="+colName+"设置他的值为"+rs.getInt(colName));
//                                System.out.println(m);
//                                System.out.println(obj);
//                                System.out.println(rs);
                                m.invoke(obj,rs.getInt(colName));
                            }else if("Date".equals(rTypes[i])){
                                m.invoke(obj,rs.getDate(colName));
                            }else if("Timestamp".equals(rTypes[i]))
                                m.invoke(obj,rs.getTimestamp(colName));
                            else{
//                                System.out.println(colName);
                                m.invoke(obj,rs.getObject(colName));
                            }

                            break;
                        }

                    }
                }
                list.add(obj);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbHelper.closeConn(conn,rs,pst);
        }

        return null;
    }


    public int saveOrUpdate(String sql,Object... args){
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = DbHelper.getConnection();
        try{
           pst = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1,args[i]);
            }
            return  pst.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbHelper.closeConn(conn,null,pst);
        }
        return 0;
    }


}
