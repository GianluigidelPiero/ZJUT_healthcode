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

@WebServlet("/DeleteStudent.do")
public class DeleteStudentServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException
    {
        StudentDao dao=new StudentDaoImpl();
        try
        {
            String id=new String(request.getParameter("id").getBytes("iso-8859-1"),"UTF-8");
            boolean success=dao.deleteStudent(id);
            if (success)
            {
                System.out.println("删除学生信息成功");
                ArrayList<Student> studentArrayList=dao.findAllStudent();
                request.getSession().setAttribute("stu_list",studentArrayList);
            }
            else
            {
                System.out.println("删除学生信息失败");
            }
        }
        catch (Exception e)
        {
            System.out.println(e+"删除学生失败");
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
