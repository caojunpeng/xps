package com.cao.xps.common.utils;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * db文件解析
 */
public class SqlitDBUtils {
    public static Logger logger= LoggerFactory.getLogger(VerifyError.class);
    public static void main(String[] args) {
        String filePath="C:\\Users\\peng\\Desktop\\最新db文件\\phone.db";
        readFile(filePath);
    }

    public static Map<String, Object> readFile(String filePah) {
        Map<String, Object> dataMap=new HashMap<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + filePah);
            stmt = c.createStatement();
            //db文件解析
            dataMap = getDataMap(stmt);
            //文件入库
            stmt.close();
            c.close();
        } catch (Exception e) {
            logger.error("解析db文件异常，异常信息："+e.getMessage(),e);
        }
        return dataMap;
    }

    /**
     * 解析db文件
     *
     * @param
     * @return
     */
    private static Map<String, Object> getDataMap(Statement stmt) {
        Map<String, Object> map=new HashMap<String, Object>();
        try {
            //人员信息
            User user=new User();
            ResultSet rs = stmt.executeQuery("select * from gather_person");
            List populate = populate(rs, User.class);
            if(populate!=null && !populate.isEmpty()) {
                user = (User) populate.get(0);
            }
            // 关闭链接
            if (rs != null) {
                rs.close();
            }
            map.put("user", user);
        } catch (Exception e) {
            logger.error("db文件转实体类异常，异常信息："+e.getMessage(),e);
        }
        return map;
    }
    /*
     * 将rs结果转换成对象列表
     * @param rs jdbc结果集
     * @param clazz 对象的映射类
     * return 封装了对象的结果列表
     */
    public static List populate(ResultSet rs , Class clazz) throws SQLException, InstantiationException, IllegalAccessException{
        //结果集的元素对象
        ResultSetMetaData rsmd = rs.getMetaData();
        //获取结果集的元素个数
        int colCount = rsmd.getColumnCount();
        //返回结果的列表集合
        List list = new ArrayList();
        //业务对象的属性数组
        Field[] fields = clazz.getDeclaredFields();
        while(rs.next()){//对每一条记录进行操作
            Object obj = clazz.newInstance();//构造业务对象实体
            //将每一个字段取出进行赋值
            for(int i = 1;i<=colCount;i++){
                Object value = rs.getObject(i);
                //寻找该列对应的对象属性
                for(int j=0;j<fields.length;j++){
                    Field f = fields[j];
                    //如果匹配进行赋值
                    if(f.getName().equalsIgnoreCase(rsmd.getColumnName(i))){
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                    }
                }
            }
            list.add(obj);
        }
        return list;
    }
}
