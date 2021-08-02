package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Dao
{
    //返回连接对象
    public default Connection getConnection()
    {
        Connection conn=null;
        try
        {
            conn=JDBCUtils.getConnection();
        }
        catch(SQLException e)
        {
            System.out.println("Dao"+e);
            e.printStackTrace();
        }
        return conn;
    }
}
