package com.study.juc.book.chapter_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yhhu
 * @date 2021/6/6
 * @description
 */
public class JDBCThreadLocal {
    private static ThreadLocal<Connection> threadLocal = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    });
    public static Connection getConnection(){
        return threadLocal.get();
    }
}
