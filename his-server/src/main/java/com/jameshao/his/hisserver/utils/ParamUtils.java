package com.jameshao.his.hisserver.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 项目：      his
 * 类名:       ParamUtils
 * 创建时间：  2023/12/8 11：05
 * 描述 :     \
 * 作者 :     张金山
 * QQ :     314649444
 * Site:      https://jshand.gitee.io
 */
public class ParamUtils {




    static{

        String dateTimePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\s(0[0-9]|1\\d|2[0-3])[0-5]\\d:[0-5]\\d$";
        String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

        Pattern p1 = Pattern.compile(dateTimePattern);
        Pattern p2 = Pattern.compile(datePattern);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd");


        //注册 字符到 日期的转换器
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if(value.getClass() != String.class){
                    return (T) value;
                }

                try {
                    String strDate = (String) value;
                    if(p1.matcher(strDate).matches()) {
                        return (T) sdf1.parse(strDate);
                    } else if (p2.matcher(strDate).matches()) {
                        return (T) sdf2.parse(strDate);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
          
                return null;
            }
        },Date.class);
    }





    public static <T> T getParam(Class<T> clazz , HttpServletRequest request) {


        Object obj = null;
        try {
            obj = clazz.newInstance();
            //从request中获取参数
            Map map = request.getParameterMap();

            Set<Map.Entry<String,Object[]>> set = map.entrySet();

            Map paramMap = new HashMap();
            for (Map.Entry<String,Object[]> entry : set) {
                Object[] value = (Object[]) entry.getValue();
                if(value == null || "".equals(value)   || (value.length == 1 && (value[0] == null || "".equals(value[0]) ) ) ){
                }else{ //只放有  value 对应的 参数
                    paramMap.put(entry.getKey(),entry.getValue());
                }
            }

            BeanUtils.populate(obj ,paramMap);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) obj;
    }




}
