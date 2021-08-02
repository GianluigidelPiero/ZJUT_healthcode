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

@WebServlet("/QRcodeServlet")
public class QRcodeServlet extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //是否接受了协议
        String [] promise=request.getParameterValues("promise");
        if(promise!=null)
        {
            System.out.println("同意的协议数量："+promise.length);
        }
        else
        {
            System.out.println(0);
        }
        if (promise.length<2)
        {
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/Question.jsp");
            rd.forward(request,response);
        }

        //生成二维码所需信息：姓名、身份（老师或同学）、工号或学号、学院
        String name=new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
        String flag=(String)request.getSession().getAttribute("flag");
        String stu_or_teacher_id=request.getParameter("stu_or_teacher_id");
        String dno=null;
        Student student=null;
        Teacher teacher=null;
        //初始的颜色和天数
        int init_days=0;
        String init_color=null;
        String QRcodeString=null;

        if (flag!=null)
        {
            if (flag.equals("teacher"))
            {
                teacher=(Teacher) request.getSession().getAttribute("me");
                dno=teacher.getDno();
                init_color=teacher.getColor();
                init_days=teacher.getDays();
                QRcodeString=flag+stu_or_teacher_id+dno+teacher.getTeacher_name();
            }
            else if (flag.equals("student"))
            {
                student=(Student) request.getSession().getAttribute("me");
                dno=student.getMno();//用专业号代替学院号
                init_color=student.getColor();
                init_days=student.getDays();
                QRcodeString=flag+stu_or_teacher_id+dno+student.getStu_name();
            }
        }

        System.out.println("QRCodeServlet："+QRcodeString);

        //根据表单提交的信息判断健康码的颜色
        String color="white";//初始为白色
        String place=request.getParameter("place");
        String place1=request.getParameter("place1");
        String touch=request.getParameter("touch");
        String confirmed=request.getParameter("confirmed");
        String [] health=request.getParameterValues("health");
        int len=0;
        if (health==null)
        {
            len=0;
        }
        else
        {
            len=health.length;
        }
        if (touch.equals("yes")||confirmed.equals("yes")||len >=2)
        {
            color="red";
        }
        else if (place.equals("yes")||place1.equals("yes")||len==1)
        {
            color="yellow";
        }
        else
        {
            color="green";
        }
        System.out.println("填报结果："+color);
        System.out.println(place+place1+touch+confirmed);
        if (health!=null)
        {
            System.out.println("病症数量："+len);
        }
        else
        {
            System.out.println("无症状");
        }

        //根据打卡日期判断，是否要改变码的颜色
        //7天才允许码变色
        //测试时用2天即可
        if (color.equals("red"))
        {
            //打卡结果是红码，直接变红，即color不变
            //打卡合格天数直接清零
            init_days=0;
        }
        if (color.equals("yellow"))
        {
            //打卡结果是黄码
            //打卡合格天数直接清零
            init_days=0;
            //如果初始码是红色，保持红码
            if (init_color.equals("red"))
            {
                color="red";
            }
            //如果初始码是黄码，color不变，不变色
            //如果初始码是绿码,color不变，变黄
        }
        if (color.equals("green"))
        {
            //打卡结果是绿码
            //今日打卡合格，打卡合格天数自增一天
            init_days++;
            if (init_color.equals("green")||init_color.equals("white"))
            {
                //初始码是绿色或白色（没填报过）
                //color保持绿色
                //变绿后直接清零
                init_days=0;
            }
            if (init_color.equals("yellow"))
            {
                //初始码是黄色
                if (init_days==2)
                {
                    color="green";
                    init_days=0;
                }
                else
                {
                    //否则不给变绿
                    color="yellow";
                }
            }
            if (init_color.equals("red"))
            {
                if (init_days==2)
                {
                    color="yellow";
                    init_days=0;
                }
                else
                {
                    color="red";
                }
            }
        }
        //经过上述判断，颜色的真值保存在color；而天数的真值保存在init_days
        if (color.equals("green"))
        {
            init_days=0;
        }
        System.out.println("判断后的真值分别是："+color+" "+init_days);

        //修改数据库中student或teacher中的color和days属性
        try
        {
            if (flag.equals("teacher"))
            {
                teacher.setColor(color);
                teacher.setDays(init_days);
                TeacherDao dao=new TeacherDaoImpl();
                dao.updateTeacher(teacher);
                request.getSession().setAttribute("me",teacher);
            }
            else if(flag.equals("student"))
            {
                student.setColor(color);
                student.setDays(init_days);
                StudentDao dao= new StudentDaoImpl();
                dao.updateStudent(student);
                request.getSession().setAttribute("me",student);
            }
        }
        catch (DaoException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        //发送数据给生成页面并转发
        request.getSession().setAttribute("QRcodeString",QRcodeString);
        request.getSession().setAttribute("color",color);
        RequestDispatcher rd=getServletContext().getRequestDispatcher("/QRcode.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}

//6.5 未完成！！！！还需要修改数据库的color和days，已知参数需要有teacher（student）————identity区分
// 调用dao里的update方法

//6.6已
// 完成上述功能
