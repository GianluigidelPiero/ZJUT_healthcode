package com.Servlet;

import com.dao.*;
import com.model.Student;
import com.model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin-login")
public class LoginAdminServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //获取表单提交的信息
        String teacher_id=request.getParameter("teacher_id");
        String psw=request.getParameter("password");
        TeacherDao dao=new TeacherDaoImpl();
        StudentDao dao1=new StudentDaoImpl();
        Teacher teacher=null;
        String message=null;
        String dno=null;
        String role=null;
        try
        {
            System.out.println("输入信息："+teacher_id+" "+psw);
            teacher=dao.findTeacherByTeacher_idAndPassword(teacher_id,psw);//获得这个教师对应的元组
            System.out.println("打印这个教师的信息"+teacher);
            dno=teacher.getDno();//获得教师所在学院
            role=teacher.getRole();//获得教师的身份（超级管理员、校级管理员、院级管理员、普通教师）
            System.out.println("学院号是："+dno+" 权限是："+role);
            if (role==null)
            {
                message="没有查到这个教师的信息";
                System.out.println(message);
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginAdmin.html");
                rd.forward(request,response);
            }
            if (role.equals("system"))
            {
                System.out.println(role);
                ArrayList<Teacher> teacher_list=(ArrayList<Teacher>) dao.findAllTeacher();
                ArrayList<Student> stu_list=(ArrayList<Student>) dao1.findAllStudent();
                request.getSession().setAttribute("teacher_list",teacher_list);
                request.getSession().setAttribute("stu_list",stu_list);
                request.getSession().setAttribute("me",teacher);//不要忘记发送自己的数据
                //页面跳转到超级管理员的控制台
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/SystemAdminStudent.jsp");
                rd.forward(request,response);
            }
            else if (role.equals("school"))
            {
                System.out.println(role);
                ArrayList<Student> stu_list=dao1.findAllStudent();
                ArrayList<Teacher> teacher_list=(ArrayList<Teacher>) dao.findAllTeacher();
                request.getSession().setAttribute("stu_list",stu_list);
                request.getSession().setAttribute("teacher_list",teacher_list);
                request.getSession().setAttribute("me",teacher);//不要忘记发送自己的数据
                //页面跳转到校级管理员的控制台
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/SchoolAdminStudent.jsp");
                rd.forward(request,response);
            }
            else if (role.equals("department"))
            {
                System.out.println(role);
                ArrayList<Student> stu_list=dao1.findStudentByDepartment(dno);
                request.getSession().setAttribute("stu_list",stu_list);
                request.getSession().setAttribute("me",teacher);//不要忘记发送自己的数据
                //页面跳转到院级管理员的控制台
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/DepartmentAdminStudent.jsp");
                rd.forward(request,response);
            }
            else if (role.equals("normal"))
            {
                //包括权限为normal的教师以及不存在的教师两种情况
                message="您不具备管理员权限";
                System.out.println(message);
                //返回登录页，并传回message信息
//                request.getSession().setAttribute("message",message);
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginAdmin.html");
                rd.forward(request,response);
            }
        }
        catch (DaoException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
