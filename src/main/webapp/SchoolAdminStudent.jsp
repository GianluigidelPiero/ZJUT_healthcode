<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Student" %>
<%@ page import="com.model.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校级管理员后台-学生管理</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body>
<%
    Teacher SchoolAdmin=(Teacher) request.getSession().getAttribute("me");
    ArrayList<Student> stu_list=(ArrayList<Student>) request.getSession().getAttribute("stu_list");
    int [] color=new int[4];//0代表未打卡，1代表绿码，2代表黄码，3代表红码
    int [] department=new int [3];//0代表计算机学院，1代表理学院，2代表文学院
    String [] stu_dep=new String[stu_list.size()];//下标代表学生，记录学生所属的学院
//    String myDepartment=null;//本校级管理员的学院
//    if (SchoolAdmin.getDno().equals("1"))
//    {
//        myDepartment="计算机学院";
//    }
//    else if (SchoolAdmin.getDno().equals("2"))
//    {
//        myDepartment="理学院";
//    }
//    else if (SchoolAdmin.getDno().equals("3"))
//    {
//        myDepartment="文学院";
//    }
    int i=0;//第一个学生
    for (Student stu:stu_list)
    {
        if (stu.getColor() != null)
        {
            if (stu.getColor().equals("white"))
            {
                color[0]++;
            }
            else if (stu.getColor().equals("green"))
            {
                color[1]++;
            }
            else if (stu.getColor().equals("yellow"))
            {
                color[2]++;
            }
            else if (stu.getColor().equals("red"))
            {
                color[3]++;
            }
        }
        else
            {
            System.out.println("SchoolAdmin页面：Student健康码颜色有null");
        }
        if (stu.getMno() != null)
        {
            if (stu.getMno().equals("11") || stu.getMno().equals("12") || stu.getMno().equals("13") || stu.getMno().equals("14"))
            {
                department[0]++;
                stu_dep[i] = "计算机学院";
            }
            else if (stu.getMno().equals("21") || stu.getMno().equals("22") || stu.getMno().equals("23") || stu.getMno().equals("24"))
            {
                department[1]++;
                stu_dep[i] = "理学院";
            }
            else if (stu.getMno().equals("31") || stu.getMno().equals("32") || stu.getMno().equals("33") || stu.getMno().equals("34"))
            {
                department[2]++;
                stu_dep[i] = "文学院";
            }
        }
        else
        {
            System.out.println("SchoolAdmin页面：student系名找不到");
        }
        i++;
    }
%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="SchoolAdminStudent.jsp">校级管理员后台</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="查询信息..." aria-label="Search" aria-describedby="basic-addon2" />
            <div class="input-group-append">
                <button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
            </div>
        </div>
    </form>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <a class="nav-link" href="./SchoolAdminStudent.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        学生管理
                    </a>
                    <a class="nav-link" href="./SchoolAdminTeacher.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                        教师管理
                    </a>
                    <a class="nav-link" href="./LoginAdmin.html">
                        <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                        退出登录
                    </a>
                </div>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <h1 class="mt-4">学生管理</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">浙江工业大学全体学生基本信息以及健康状况统计</li>
                </ol>
                <div class="row">
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-primary text-white mb-4">
                            <div class="card-body">全校学生数</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <%=stu_list.size()%>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-success text-white mb-4">
                            <div class="card-body">绿码人数</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <%=color[1]%>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-warning text-white mb-4">
                            <div class="card-body">黄码人数</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <%=color[2]%>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-danger text-white mb-4">
                            <div class="card-body">红码人数</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <%=color[3]%>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xl-8">
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-chart-area mr-1"></i>
                                学生打卡情况
                            </div>
                            <div class="card-body"><canvas id="myPieChart_School_Student" width="100%" height="40"></canvas></div>
                        </div>
                    </div>
                </div>

                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table mr-1"></i>
                        学生列表
<%--                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdd">添加</button>--%>
<%--                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalDelete">删除</button>--%>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>身份证号</th>
                                    <th>学号</th>
                                    <th>学院班级</th>
                                    <th>健康状况</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>姓名</th>
                                    <th>身份证号</th>
                                    <th>学号</th>
                                    <th>学院班级</th>
                                    <th>健康状况</th>
                                    <th>操作</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    int j=0;
                                    for(Student stu:stu_list)
                                    {
                                %>
                                <tr>
                                    <td><%=stu.getStu_name()%></td>
                                    <td><%=stu.getId()%></td>
                                    <td><%=stu.getStu_id()%></td>
                                    <td><%=stu_dep[j]+" "+stu.getStu_class()%></td>
                                    <td><%=(stu.getColor().equals("white"))?"未打卡":stu.getColor()%>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModalUpdate<%=Integer.toString(stu.getIdd())%>">详情</button>
                                    </td>
                                </tr>
                                <%
                                        j++;
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; John the Baptist 2021</div>
                    <div>
                        <a href="#">隐私政策</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>

<%
    int k=0;
    for(Student stu1:stu_list)
    {
%>
<div class="update_modal">
    <div class="modal fade" id="myModalUpdate<%=Integer.toString(stu1.getIdd())%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">详细信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">
                                    &times;
                                </span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <label class="control-label">姓名：</label>
                            <input readonly="readonly" type="text" name="stu_name" class="form-control" value="<%=stu1.getStu_name()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">身份证号：</label>
                            <input readonly="readonly" type="text" name="id" class="form-control" value="<%=stu1.getId()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">学号：</label>
                            <input readonly="readonly" type="text" name="stu_id" class="form-control" value="<%=stu1.getStu_id()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label" >专业代码：</label>
                            <input readonly="readonly" type="text" name="mno" class="form-control" value="<%=stu1.getMno()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">班级：</label>
                            <input readonly="readonly" type="text" name="stu_class" class="form-control" value="<%=stu1.getStu_class()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="color" class="form-control" value="<%=stu1.getColor()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="days" class="form-control" value="<%=stu1.getDays()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="idd" class="form-control" value="<%=stu1.getIdd()%>">
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <button type="button" class="btn btn-info">确定</button>--%>
<%--                        </div>--%>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%
        k++;
    }
%>


<%--<div class="update_modal">--%>
<%--    <div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h4 class="modal-title">添加学生信息</h4>--%>
<%--                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                <span aria-hidden="true">--%>
<%--                                    &times;--%>
<%--                                </span>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <form action="AddStudent.do">--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label">姓名：</label>--%>
<%--                            <input type="text" name="stu_name" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label">身份证号：</label>--%>
<%--                            <input type="text" name="id" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label">学号：</label>--%>
<%--                            <input type="text" name="stu_id" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label" >专业代码：</label>--%>
<%--                            <input type="text" name="mno" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label">班级：</label>--%>
<%--                            <input type="text" name="stu_class" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <button type="submit" class="btn btn-info">提交</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div class="update_modal">--%>
<%--    <div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h4 class="modal-title">删除学生信息</h4>--%>
<%--                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                                <span aria-hidden="true">--%>
<%--                                    &times;--%>
<%--                                </span>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <form action="DeleteStudent.do">--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label">身份证号：</label>--%>
<%--                            <input type="text" name="id" class="form-control">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <button type="submit" class="btn btn-info">提交</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/datatables-demo.js"></script>
<script type="text/javascript">
    Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#292b2c';
    var ctx = document.getElementById("myPieChart_School_Student");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["绿码", "黄码", "红码", "未打卡"],
            datasets: [{
                data: [<%=color[1]%>, <%=color[2]%>, <%=color[3]%>,<%=color[0]%>],
                backgroundColor: ['green', 'yellow', 'red', 'blue'],
            }],
        },
    });
</script>
</body>
</html>
