<%@ page import="com.model.Student" %>
<%@ page import="com.model.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的健康码</title>
    <script type="text/javascript" src="//cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="//static.runoob.com/assets/qrcode/qrcode.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
        #qrcode
        {
            width: 400px;
            margin:0 auto;/*确定宽度时使用这种方法*/
            margin-top: 20px;
        }
        #btn
        {
            width: 400px;
            text-align: center;
            margin: 0 auto;
            margin-top: 20px;
        }
        #name
        {
            width: 400px;
            text-align: center;
            margin: 0 auto;
            margin-top: 20px;
            font-size: larger;
        }
        a
        {
            text-decoration:none;
        }
        html,body
        {
            background-image: url('./image/zjut.jpg');
            background-size:cover;
            background-repeat: no-repeat;
            height: 100%;
            font-family: 'Numans', sans-serif;
        }
    </style>
</head>
<body>
<%
    String QRcodeString=(String) request.getSession().getAttribute("QRcodeString");
    String color=(String) request.getSession().getAttribute("color");
    System.out.println("打印二维码页面flag："+QRcodeString+" "+color);
    String flag=null;
    Student student=null;
    Teacher teacher=null;
    String name=null;
    flag=(String) request.getSession().getAttribute("flag");
    System.out.println("打印二维码页面flag："+flag);
    if (flag.equals("teacher"))
    {
        teacher=(Teacher) request.getSession().getAttribute("me");
        name=teacher.getTeacher_name();
    }
    else if (flag.equals("student"))
    {
        student=(Student) request.getSession().getAttribute("me");
        name=student.getStu_name();
    }
%>
<div id="qrcode"></div>
<div id="name" class="card">
    姓名：
    <%=name%>
    <br>
    身份：
    <%=flag%>
</div>
<div id="btn">
    <a type="button" class="btn btn-primary btn-sm" href="LoginAdmin.html">
        确定
    </a>
</div>

<script type="text/javascript">
    var codetext='<%=QRcodeString%>';
    var codecolor='<%=color%>';
    console.log(codetext+" "+codecolor);
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        text:codetext,
        width : 400,
        height : 400,
        colorDark : codecolor,
        colorLight : "white",
    });
</script>
</body>
</html>