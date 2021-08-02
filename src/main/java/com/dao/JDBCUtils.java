package com.dao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils
{
    public static DataSource ds;
    static
    {
        try
        {
            Properties pros = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            ds= DruidDataSourceFactory.createDataSource(pros);
            System.out.println("ds:"+ds);
//            System.out.println("JDBCUtils可以运行");
        }
        catch (IOException e1)
        {
            System.out.println("JDBCUtils"+e1);
            e1.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("JDBCUtils"+e);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
//        System.out.println("进来");
        Connection conn=ds.getConnection();
        System.out.println("conn:"+conn);
//        if (conn==null)
//        {
//            System.out.println("conn是null");
//        }
        return conn;
    }

//    public static void main(String[] args) throws SQLException {
//        getConnection();
//    }
}
