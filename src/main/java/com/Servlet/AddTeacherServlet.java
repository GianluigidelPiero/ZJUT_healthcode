package com.Servlet;

import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
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

@WebServlet("/AddTeacher.do")
public class AddTeacherServlet extends HttpServlet
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
            teacher.setPassword(new String(request.getParameter("password").getBytes("iso-8859-1"),"UTF-8"));
            //添加新教师时，默认没有打卡
            teacher.setColor("white");
            teacher.setDays(0);
            boolean success=dao.addTeacher(teacher);//调用dao，与数据库联系起来
            if (success)
            {
                //测试
                System.out.println("教师添加成功");
                ArrayList<Teacher> teacherArrayList=dao.findAllTeacher();
                request.getSession().setAttribute("teacher_list",teacherArrayList);
            }
            else
            {
                System.out.println("教师添加失败");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e+"教师添加失败");
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
