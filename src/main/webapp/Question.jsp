<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/6/5
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.model.Student" %>
<%@ page import="com.model.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>健康码申请</title>
    <link rel="stylesheet" href="./css/Question.css" />
</head>
<body>
<%
    String flag=(String)request.getSession().getAttribute("flag");
    Student student=null;
    Teacher teacher=null;
    String name=null;
    String id=null;
    String stu_or_teacher_id=null;
    System.out.println(flag);
    if (flag!=null)
    {
        if (flag.equals("teacher"))
        {
            teacher=(Teacher) request.getSession().getAttribute("me");
            name=teacher.getTeacher_name();
            id=teacher.getId();
            stu_or_teacher_id=teacher.getTeacher_id();
            System.out.println(teacher);
        }
        else if (flag.equals("student"))
        {
            student = (Student) request.getSession().getAttribute("me");
            name=student.getStu_name();
            id=student.getId();
            stu_or_teacher_id=student.getStu_id();
            System.out.println(student);
        }
    }
%>
<form class="box" action="QRcodeServlet" method="post">
    <img src="image/hangzhou.jpg" alt="" id="hangzhoujpg">
    <div class="text2">
        姓名
    </div>
    <div class="choiceBox0">
        <input type="text" class="data" name="name" value="<%=name%>" readonly="readonly">
    </div>

    <div class="text2">
        身份证号
    </div>
    <div class="choiceBox0">
        <input type="text" class="data" name="id" value="<%=id%>" readonly="readonly">
    </div>

    <div class="text2">
        学号
    </div>
    <div class="choiceBox0">
        <input type="text" class="data" name="stu_or_teacher_id" value="<%=stu_or_teacher_id%>" readonly="readonly">
    </div>

    <div class="text2">
        手机号
    </div>
    <div class="choiceBox0">
        <input type="text" class="data" name="phone">
    </div>

    <div class="choiceBox1">
        <div class="text">
            您最近14天是否去过重点疫灾区？
            <div class="tip">单选</div>
        </div>
        <div class="choices">
            <input name='place' type="radio" class="choice" value="no">
            <div class="value">否</div>
        </div>
        <div class="choices">
            <input name='place' type="radio" class="choice" value="yes">
            <div class="value last">是</div>
        </div>
    </div>

    <div class="choiceBox1">
        <div class="text">
            您最近14天是否去过国外？
            <div class="tip">单选</div>
        </div>
        <div class="choices">
            <input name='place1' type="radio" class="choice" value="no">
            <div class="value">否</div>
        </div>
        <div class="choices">
            <input name='place1' type="radio" class="choice" value="yes">
            <div class="value last">是</div>
        </div>
    </div>

    <div class="choiceBox2">
        <div class="text">
            您最近14天是否接触过新冠确诊病人或疑似病人?&nbsp;<div class="tip">单选</div>
        </div>
        <div class="choices">
            <input name='touch' type="radio" class="choice" value="no">
            <div class="value">否</div>
        </div>
        <div class="choices">
            <input name='touch' type="radio" class="choice" value="yes">
            <div class="value last">是</div>
        </div>
    </div>

    <div class="choiceBox2">
        <div class="text">
            本人是否被卫生部门确认为新冠肺炎确诊病例或疑似病例?&nbsp;<div class="tip">单选</div>
        </div>
        <div class="choices">
            <input name='confirmed' type="radio" class="choice"  value="no">
            <div class="value">否</div>
        </div>
        <div class="choices">
            <input name='confirmed' type="radio" class="choice"  value="yes">
            <div class="value last">是</div>
        </div>
    </div>

    <div class="choiceBox3">
        <div class="text">
            当前健康状况（若有一下状况，请在方框内勾选）&nbsp;<div class="tip orange">多选</div>
        </div>
<%--        <div class="choices">--%>
<%--            <input name='health' type="checkbox" class="choice" value="healthy">--%>
<%--            <div class="value">无异常</div>--%>
<%--        </div>--%>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="fever">
            <div class="value ">发烧（≥37.3℃）</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="feeble">
            <div class="value ">乏力</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="cough">
            <div class="value ">干咳</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="nasal congestion">
            <div class="value ">鼻塞</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="runny nose">
            <div class="value ">流涕</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice" value="sore throat">
            <div class="value ">咽痛</div>
        </div>
        <div class="choices">
            <input name='health' type="checkbox" class="choice"  value="diarrhea">
            <div class="value last">腹泻</div>
        </div>

    </div>
    <div class="choiceBox4">
        <div class="title">
            本人郑重承诺：
        </div>
        <div class="choices">
            <input name='promise' type="checkbox" class="choice2" value="promise1">
            <div class="value">为疫情防控，本人同意以上信息依法提交所在辖区疫情防控部门统筹管理</div>
        </div>
        <div class="choices">
            <input name='promise' type="checkbox" class="choice3" value="promise2">
            <div class="value last">
                上述信息是我本人填写，本人对信息内容的真实性和完整性负责。如果信息有误或缺失，本人愿承相应的法律责任。同时，本人保证遵守防疫管控的各项规定，配合听从各项措施和要求。
            </div>
        </div>
        <button type="submit" class="sub" onclick="check()">提交</button>
    </div>
</form>
</body>
</html>
