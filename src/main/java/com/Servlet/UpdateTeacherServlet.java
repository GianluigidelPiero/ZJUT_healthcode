package com.Servlet;

import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UpdateTeacher.do")
public class UpdateTeacherServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        TeacherDao dao=new TeacherDaoImpl();
        Teacher teacher=new Teacher();
        try
        {
            teacher.setTeacher_name(request.getParameter("teacher_name"));
            teacher.setId(new String(request.getParameter("id").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setTeacher_id(new String(request.getParameter("teacher_id").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setDno(new String(request.getParameter("dno").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setRole(new String(request.getParameter("role").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setColor(new String(request.getParameter("color").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setDays(Integer.parseInt(request.getParameter("days")));
            teacher.setPassword(new String(request.getParameter("password").getBytes("iso-8859-1"),"UTF-8"));
            teacher.setIdd(Integer.parseInt(request.getParameter("idd")));
            boolean success=dao.updateTeacher(teacher);//调用dao，与数据库联系起来
            if (success)
            {
                System.out.println("修改成功");
                //目的是刷新后获得新数据
                ArrayList<Teacher> teacherArrayList=dao.findAllTeacher();
                request.getSession().setAttribute("teacher_list",teacherArrayList);
            }
            else
            {
                System.out.println("修改教师失败");
            }
        }
        catch (Exception e)
        {
            System.out.println(e+"修改教师失败");
            e.printStackTrace();
        }
        RequestDispatcher rd=getServletContext().getRequestDispatcher("/SystemAdminTeacher.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
