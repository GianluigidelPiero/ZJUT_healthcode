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

@WebServlet("/DeleteTeacher.do")
public class DeleteTeacherServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        TeacherDao dao=new TeacherDaoImpl();
        try
        {
            String id=new String(request.getParameter("id").getBytes("iso-8859-1"),"UTF-8");
            boolean success=dao.deleteTeacher(id);
            if (success)
            {
                System.out.println("删除教师信息成功");
                ArrayList<Teacher> teacherArrayList=dao.findAllTeacher();
                request.getSession().setAttribute("teacher_list",teacherArrayList);
            }
            else
            {
                System.out.println("删除教师信息失败");
            }
        }
        catch (Exception e)
        {
            System.out.println(e+"删除教师失败");
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
