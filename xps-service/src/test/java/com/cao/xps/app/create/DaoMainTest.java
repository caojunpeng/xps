package com.cao.xps.app.create;

import com.alibaba.fastjson.JSON;
import com.cao.xps.service.user.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoMainTest {
    public static void main(String[] args) {
        // 创建图书结果集合List
        List<User> users = new ArrayList<User>();

        Connection conn = null;
        PreparedStatement preStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 创建数据库连接对象
            conn = DriverManager.getConnection(
                    "jdbc:mysql://172.17.10.159:33036/xpsdb?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                    "root",
                    "55F41AEC312911D8B92BCFBF43B88F60");

            // 定义查询SQL
            String sql = "select * from user";
            // 创建Statement语句对象
            preStatement = conn.prepareStatement(sql);
            // 执行语句, 得到结果集
            resultSet = preStatement.executeQuery();
            // 处理结果集

            while (resultSet.next()) {
                // 创建图书对象
                User user = new User();
                user.setUserId(Long.valueOf(resultSet.getString("user_id")));
                // 将查询到的结果添加到list中
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (null != conn) conn.close();
                if (null != preStatement) preStatement.close();
                if (null != resultSet) resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSON(users));;
    }

}
