package com.dao;

import com.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDaoImpl implements TeacherDao
{
    @Override
    public boolean addTeacher(Teacher teacher) throws DaoException
    {
        String sql="INSERT INTO teachers (teacher_name,id,teacher_id,dno,role,color,days,password) VALUES(?,?,?,?,?,?,?,?)";
        try(
                Connection conn=getConnection();//Dao接口里的方法
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher.getTeacher_name());
            pstmt.setString(2,teacher.getId());
            pstmt.setString(3,teacher.getTeacher_id());
            pstmt.setString(4,teacher.getDno());
            pstmt.setString(5,teacher.getRole());
            pstmt.setString(6,teacher.getColor());
            pstmt.setInt(7,teacher.getDays());
            pstmt.setString(8,teacher.getPassword());
            int n=pstmt.executeUpdate();//执行DDL语句（ALTER、CREATE）或DML语句（INSERT、UPDATE、DELETE）时使用
            System.out.println(n);
            return true;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Teacher> findAllTeacher() throws DaoException
    {
        ArrayList<Teacher> teacher_list=new ArrayList<Teacher>();
        String sql="SELECT * FROM teachers";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rst=pstmt.executeQuery())
        {
            while (rst.next())
            {
                Teacher teacher=new Teacher();
                teacher.setTeacher_name(rst.getString("teacher_name"));
                teacher.setId(rst.getString("id"));
                teacher.setTeacher_id(rst.getString("teacher_id"));
                teacher.setDno(rst.getString("dno"));
                teacher.setRole(rst.getString("role"));
                teacher.setColor(rst.getString("color"));
                teacher.setDays(rst.getInt("days"));
                teacher.setPassword(rst.getString("password"));
                teacher.setIdd(rst.getInt("idd"));
                teacher_list.add(teacher);
            }
            return teacher_list;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteTeacher(String id) throws DaoException
    {
        //根据身份证号删除一行信息
        String sql="DELETE FROM teachers WHERE id=?";
        try ( Connection conn=getConnection();
              PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,id);
            int n=pstmt.executeUpdate();
            System.out.println(n);
            return true;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher) throws DaoException
    {
        String sql="UPDATE teachers " +
                "SET teacher_name=?," +
                "id=?," +
                "teacher_id=?, " +
                "dno=?, " +
                "role=?, " +
                "color=?, " +
                "days=?," +
                "PASSWORD=?"+
                "WHERE idd=?";
        try(
                Connection conn=getConnection();//Dao接口里的方法
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher.getTeacher_name());
            pstmt.setString(2,teacher.getId());
            pstmt.setString(3,teacher.getTeacher_id());
            pstmt.setString(4,teacher.getDno());
            pstmt.setString(5,teacher.getRole());
            pstmt.setString(6,teacher.getColor());
            pstmt.setInt(7,teacher.getDays());
            pstmt.setString(8,teacher.getPassword());
            pstmt.setInt(9,teacher.getIdd());//和add的不同之处
            int n=pstmt.executeUpdate();//执行DDL语句（ALTER、CREATE）或DML语句（INSERT、UPDATE、DELETE）时使用
            System.out.println(n);
            return true;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public Teacher findTeacherByTeacher_idAndPassword(String teacher_id, String password) throws DaoException
    {
        String sql="SELECT * FROM teachers WHERE teacher_id=? AND PASSWORD=?";
        Teacher teacher=new Teacher();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
//            System.out.println("try");
            pstmt.setString(1,teacher_id);
            pstmt.setString(2,password);
            try(ResultSet rst=pstmt.executeQuery())
            {
                if (rst.next())
                {
                    teacher.setTeacher_name(rst.getString("teacher_name"));
                    teacher.setId(rst.getString("id"));
                    teacher.setTeacher_id(rst.getString("teacher_id"));
                    teacher.setDno(rst.getString("dno"));
                    teacher.setRole(rst.getString("role"));
                    teacher.setColor(rst.getString("color"));
                    teacher.setDays(rst.getInt("days"));
                    teacher.setPassword(rst.getString("password"));
                    teacher.setIdd(rst.getInt("idd"));
                }
            }
        }
        catch(SQLException se)
        {
            System.out.println("se");
            se.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            System.out.println("e");
            e.printStackTrace();
            return null;
        }
        return teacher;
    }

    @Override
    public Teacher findTeacherByTeacher_nameAndTeacher_idAndId(String teacher_name, String teacher_id, String id_last_eight) throws DaoException
    {
        String sql="SELECT * FROM teachers WHERE teacher_name=? AND teacher_id=? AND id LIKE ?";
        Teacher teacher=new Teacher();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,teacher_name);
            pstmt.setString(2,teacher_id);
            pstmt.setString(3,"%"+id_last_eight);
            try(ResultSet rst=pstmt.executeQuery())
            {
                if (rst.next())
                {
                    teacher.setTeacher_name(rst.getString("teacher_name"));
                    teacher.setId(rst.getString("id"));
                    teacher.setTeacher_id(rst.getString("teacher_id"));
                    teacher.setDno(rst.getString("dno"));
                    teacher.setRole(rst.getString("role"));
                    teacher.setColor(rst.getString("color"));
                    teacher.setDays(rst.getInt("days"));
                    teacher.setPassword(rst.getString("password"));
                    teacher.setIdd(rst.getInt("idd"));
                }
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        return teacher;
    }
}
