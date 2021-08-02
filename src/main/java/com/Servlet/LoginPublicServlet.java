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

@WebServlet("/public-login")
public class LoginPublicServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //获取表单提交的信息
        String name=new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
        String teacher_or_stu_id=request.getParameter("teacher_or_stu_id");
        String id_last_eight=request.getParameter("id_last_eight");
        String identity=request.getParameter("identity");
        System.out.println("输出填报人员的信息："+name+" "+teacher_or_stu_id+" "+id_last_eight+" "+identity);
        TeacherDao dao=new TeacherDaoImpl();
        StudentDao dao1=new StudentDaoImpl();
        Student student=null;
        Teacher teacher=null;
        String message=null;
        String flag=null;
        try
        {
            if (identity.equals("teacher"))
            {
                teacher=dao.findTeacherByTeacher_nameAndTeacher_idAndId(name,teacher_or_stu_id,id_last_eight);
                System.out.println("数据库返回教师名："+teacher.getTeacher_name());
                if (teacher==null || teacher.getTeacher_name()==null)
                {
                    //null代表抛出sql语句异常，0代表没有找到
                    //页面跳转回登录页面，返回教师输入错误
//                    message="教师工号或密码错误";
//                    request.getSession().setAttribute("message",message);
                    RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginPublic.html");
                    rd.forward(request,response);
                }
                request.getSession().setAttribute("me",teacher);//将用户信息存在session
                flag="teacher";
                request.getSession().setAttribute("flag",flag);
            }
            else if (identity.equals("student"))
            {
                student=dao1.findStudentByStu_nameAndStu_idAndId(name,teacher_or_stu_id,id_last_eight);
                System.out.println("数据库返回学生名："+student.getStu_name());
                if (student==null || student.getStu_name()==null)
                {
                    //页面跳转回登录页面，返回学生输入错误
//                    message="学生学号或密码错误";
//                    request.getSession().setAttribute("message",message);
                    RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginPublic.html");
                    rd.forward(request,response);
                }
                request.getSession().setAttribute("me",student);
                flag="student";
                request.getSession().setAttribute("flag",flag);
            }
            else
            {
                //错误的identity
                //页面跳转回登录页面
                RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginPublic.html");
                rd.forward(request,response);
            }
            //页面跳转到问卷
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Question.jsp");
            rd.forward(request,response);
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
