package com.dao;

import com.model.Student;
import com.model.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherDao extends Dao
{
    //添加一个教师
    public boolean addTeacher(Teacher teacher) throws DaoException;

    //查询所有教师数据
    public ArrayList<Teacher> findAllTeacher() throws DaoException;

    //删除一个教师
    public boolean deleteTeacher(String id) throws DaoException;

    //修改一个教师的数据
    public boolean updateTeacher(Teacher teacher) throws DaoException;

    //根据工号和密码查询教师
    public Teacher findTeacherByTeacher_idAndPassword(String Teacher_id,String password) throws DaoException;

    //根据姓名、工号、身份证后八位查询教师数据
    public Teacher findTeacherByTeacher_nameAndTeacher_idAndId(String teacher_name, String teacher_id, String id_last_eight) throws DaoException;

//    public static void main(String[] args) throws SQLException
//    {
//        Teacher teacher=null;
//        teacher= findTeacherByTeacher_idAndPassword("root","123");
//    }
}
