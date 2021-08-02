package com.dao;

import com.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao
{
    @Override
    public boolean addStudent(Student student) throws DaoException
    {
        String sql="INSERT INTO students (stu_name,id,stu_id,mno,stu_class,color,days) VALUES(?,?,?,?,?,?,?)";
        try(
                Connection conn=getConnection();//Dao接口里的方法
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,student.getStu_name());
            pstmt.setString(2,student.getId());
            pstmt.setString(3,student.getStu_id());
            pstmt.setString(4,student.getMno());
            pstmt.setString(5,student.getStu_class());
            pstmt.setString(6,student.getColor());
            pstmt.setInt(7,student.getDays());
            int n=pstmt.executeUpdate();//执行DDL语句（ALTER、CREATE）或DML语句（INSERT、UPDATE、DELETE）时使用
            //返回受影响的行数
            System.out.println(n);
            System.out.println(student);
            return true;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Student> findAllStudent() throws DaoException
    {

        ArrayList<Student> stu_list=new ArrayList<Student>();
        String sql="SELECT * FROM students";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rst=pstmt.executeQuery())
        {
            while (rst.next())
            {
                Student student=new Student();//写在while循环里面，否则全是重复的
                student.setStu_name(rst.getString("stu_name"));
                student.setId(rst.getString("id"));
                student.setStu_id(rst.getString("stu_id"));
                student.setMno(rst.getString("mno"));
                student.setStu_class(rst.getString("stu_class"));
                student.setColor(rst.getString("color"));
                student.setDays(rst.getInt("days"));
                student.setIdd(rst.getInt("idd"));
                stu_list.add(student);
//                for(Student stu:stu_list)
//                {
//                    System.out.println(stu.toString());
//                }
            }
            return stu_list;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Student> findStudentByDepartment(String dno) throws DaoException
    {
        String sql="SELECT * FROM students WHERE mno IN(SELECT mno FROM major WHERE dno=?)";
        ArrayList<Student> stu_list=new ArrayList<Student>();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,dno);
            try(ResultSet rst=pstmt.executeQuery())
            {
                while (rst.next())
                {
                    Student student=new Student();
                    student.setStu_name(rst.getString("stu_name"));
                    student.setId(rst.getString("id"));
                    student.setStu_id(rst.getString("stu_id"));
                    student.setMno(rst.getString("mno"));
                    student.setStu_class(rst.getString("stu_class"));
                    student.setColor(rst.getString("color"));
                    student.setDays(rst.getInt("days"));
                    student.setIdd(rst.getInt("idd"));
                    stu_list.add(student);
                }
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        return stu_list;
    }

    @Override
    public boolean deleteStudent(String id) throws DaoException
    {
        //根据身份证号删除一行信息
        String sql="DELETE FROM students WHERE id=?";
        try ( Connection conn=getConnection();//Dao接口里的方法
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
    public boolean updateStudent(Student student) throws DaoException
    {
        String sql="UPDATE students " +
                "SET stu_name=?,id=?,stu_id=?,mno=?,stu_class=?," +
                "color=?,days=? " +
                "WHERE idd=?";

        System.out.println("在updatestudent里面的颜色"+student.getColor());
        try(
                Connection conn=getConnection();//Dao接口里的方法
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,student.getStu_name());
            pstmt.setString(2,student.getId());
            pstmt.setString(3, student.getStu_id());
            pstmt.setString(4,student.getMno());
            pstmt.setString(5,student.getStu_class());
            pstmt.setString(6,student.getColor());
            pstmt.setInt(7,student.getDays());
            pstmt.setInt(8,student.getIdd());//和add的不同之处
            System.out.println("打印updatestudent的psmt："+pstmt.toString());
            int n=pstmt.executeUpdate();//执行DDL语句（ALTER、CREATE）或DML语句（INSERT、UPDATE、DELETE）时使用
            System.out.println(n);
            return true;
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student findStudentByStu_nameAndStu_idAndId(String stu_name, String stu_id, String id_last_eight) throws DaoException
    {
        String sql="SELECT * FROM students WHERE stu_name=? AND stu_id=? AND id LIKE ?";
        Student student=new Student();
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql))
        {
           pstmt.setString(1,stu_name);
           pstmt.setString(2,stu_id);
           pstmt.setString(3,"%"+id_last_eight);
            try(ResultSet rst=pstmt.executeQuery())
            {
                if (rst.next())
                {
                    student.setStu_name(rst.getString("stu_name"));
                    student.setId(rst.getString("id"));
                    student.setStu_id(rst.getString("stu_id"));
                    student.setMno(rst.getString("mno"));
                    student.setStu_class(rst.getString("stu_class"));
                    student.setColor(rst.getString("color"));
                    student.setDays(rst.getInt("days"));
                    student.setIdd(rst.getInt("idd"));
                }
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        return student;
    }
}
