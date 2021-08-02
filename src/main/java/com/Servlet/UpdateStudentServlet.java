package com.Servlet;

import com.dao.StudentDao;
import com.dao.StudentDaoImpl;
import com.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UpdateStudent.do")
public class UpdateStudentServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        StudentDao dao=new StudentDaoImpl();
        Student student=new Student();
        try
        {
            student.setStu_name(request.getParameter("stu_name"));
            student.setId(new String(request.getParameter("id").getBytes("iso-8859-1"),"UTF-8"));
            student.setStu_id(new String(request.getParameter("stu_id").getBytes("iso-8859-1"),"UTF-8"));
            student.setMno(new String(request.getParameter("mno").getBytes("iso-8859-1"),"UTF-8"));
            student.setStu_class(request.getParameter("stu_class"));
            student.setColor(new String(request.getParameter("color").getBytes("iso-8859-1"),"UTF-8"));
            student.setDays(Integer.parseInt(request.getParameter("days")));
            student.setIdd(Integer.parseInt(request.getParameter("idd")));
            boolean success=dao.updateStudent(student);//调用dao，与数据库联系起来
            if (success)
            {
                System.out.println("修改成功");
                //目的是刷新后获得新数据
                ArrayList<Student> studentArrayList=dao.findAllStudent();
                request.getSession().setAttribute("stu_list",studentArrayList);
            }
            else
            {
                System.out.println("修改学生失败");
            }
        }
        catch (Exception e)
        {
            System.out.println(e+"修改学生失败");
            e.printStackTrace();
        }
        RequestDispatcher rd=getServletContext().getRequestDispatcher("/SystemAdminStudent.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
