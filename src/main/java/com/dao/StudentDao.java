package com.dao;

import com.model.Student;

import java.util.ArrayList;

public interface StudentDao extends Dao
{
    //添加一个学生
    public boolean addStudent(Student student) throws DaoException;

    //查询所有学生数据ArrayList<Student>
    public ArrayList<Student> findAllStudent() throws DaoException;

    //删除一个学生
    public boolean deleteStudent(String id) throws DaoException;

    //修改一个学生的数据
    public boolean updateStudent(Student student) throws DaoException;

    //查询本学院的学生数据
    public ArrayList<Student> findStudentByDepartment(String dno) throws DaoException;

    //根据姓名、学号、身份证后八位查询学生数据
    public Student findStudentByStu_nameAndStu_idAndId(String stu_name,String stu_id,String id_last_eight) throws DaoException;
}
