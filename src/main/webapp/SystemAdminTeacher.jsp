<%@ page import="com.model.Teacher" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理员后台-教师管理</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<%
    Teacher SystemAdmin=(Teacher) request.getSession().getAttribute("me");
    ArrayList<Teacher> teacher_list=(ArrayList<Teacher>) request.getSession().getAttribute("teacher_list");
    int [] color = new int[4];//0代表未打卡，1代表绿码，2代表黄码，3代表红码
    int [] department = new int[3];//0代表计算机学院，1代表理学院，2代表文学院
    String [] teacher_dep=new String[teacher_list.size()];//下标代表教师，记录教师所属的学院
    int i=0;//第一个教师
    for (Teacher teacher:teacher_list)
    {
        if (teacher.getColor() != null)
        {
            if (teacher.getColor().equals("white"))
            {
                color[0]++;
            }
            else if (teacher.getColor().equals("green"))
            {
                color[1]++;
            }
            else if (teacher.getColor().equals("yellow"))
            {
                color[2]++;
            }
            else if (teacher.getColor().equals("red"))
            {
                color[3]++;
            }
        }
        else
        {
            System.out.println("SystemAdmin页面：teacher健康码颜色有null");
        }
        if (teacher.getDno()!=null)
        {
            if (teacher.getDno().equals("1"))
            {
                department[0]++;
                teacher_dep[i]="计算机学院";
            }
            else if (teacher.getDno().equals("2"))
            {
                department[1]++;
                teacher_dep[i]="理学院";
            }
            else if (teacher.getDno().equals("3"))
            {
                department[2]++;
                teacher_dep[i]="文学院";
            }
        }
        else
        {
            System.out.println("SystemAdmin页面：teacherDno有null");
        }
        i++;
    }
%>

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="SystemAdminTeacher.jsp">系统管理员后台</a>
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
                    <a class="nav-link" href="./SystemAdminStudent.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        学生管理
                    </a>
                    <a class="nav-link" href="./SystemAdminTeacher.jsp">
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
                <h1 class="mt-4">教师管理</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">浙江工业大学全体教职工基本信息以及健康状况统计</li>
                </ol>
                <div class="row">
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-primary text-white mb-4">
                            <div class="card-body">全校教师数</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <%=teacher_list.size()%>
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
                                教师打卡情况
                            </div>
                            <div class="card-body"><canvas id="myPieChart_System_Teacher" width="100%" height="40"></canvas></div>
                        </div>
                    </div>
                </div>

                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table mr-1"></i>
                        教师列表
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalAdd">添加</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalDelete">删除</button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>身份证号</th>
                                    <th>工号</th>
                                    <th>学院</th>
                                    <th>健康状况</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>姓名</th>
                                    <th>身份证号</th>
                                    <th>工号</th>
                                    <th>学院</th>
                                    <th>健康状况</th>
                                    <th>操作</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <%
                                    int j=0;
                                    for(Teacher teacher:teacher_list)
                                    {
                                %>
                                <tr>
                                    <td><%=teacher.getTeacher_name()%></td>
                                    <td><%=teacher.getId()%></td>
                                    <td><%=teacher.getTeacher_id()%></td>
                                    <td><%=teacher_dep[j]%></td>
                                    <td><%=(teacher.getColor().equals("white"))?"未打卡":teacher.getColor()%>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModalUpdate<%=Integer.toString(teacher.getIdd())%>">更新</button>
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
    for(Teacher teacher1:teacher_list)
    {
%>
<div class="update_modal">
    <div class="modal fade" id="myModalUpdate<%=Integer.toString(teacher1.getIdd())%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">更新教师信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">
                                    &times;
                                </span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="UpdateTeacher.do">
                        <div class="form-group">
                            <label class="control-label">姓名：</label>
                            <input type="text" name="teacher_name" class="form-control" value="<%=teacher1.getTeacher_name()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">身份证号：</label>
                            <input type="text" name="id" class="form-control" value="<%=teacher1.getId()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">学号：</label>
                            <input type="text" name="teacher_id" class="form-control" value="<%=teacher1.getTeacher_id()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label" >学院代码：</label>
                            <input type="text" name="dno" class="form-control" value="<%=teacher1.getDno()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">权限：</label>
                            <input type="text" name="role" class="form-control" value="<%=teacher1.getRole()%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">密码：</label>
                            <input type="text" name="password" class="form-control" value="<%=teacher1.getPassword()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="color" class="form-control" value="<%=teacher1.getColor()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="days" class="form-control" value="<%=teacher1.getDays()%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="idd" class="form-control" value="<%=teacher1.getIdd()%>">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-info">提交</button>
                        </div>
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


<div class="update_modal">
    <div class="modal fade" id="myModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">添加教师信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">
                                    &times;
                                </span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="AddTeacher.do">
                        <div class="form-group">
                            <label class="control-label">姓名：</label>
                            <input type="text" name="teacher_name" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <label class="control-label">身份证号：</label>
                            <input type="text" name="id" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <label class="control-label">工号：</label>
                            <input type="text" name="teacher_id" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <label class="control-label" >学院代码：</label>
                            <input type="text" name="dno" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <label class="control-label">权限：</label>
                            <input type="text" name="role" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <label class="control-label">密码：</label>
                            <input type="text" name="password" class="form-control" %>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-info">提交</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="update_modal">
    <div class="modal fade" id="myModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">删除教师信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">
                                    &times;
                                </span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="DeleteTeacher.do">
                        <div class="form-group">
                            <label class="control-label">身份证号：</label>
                            <input type="text" name="id" class="form-control">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-info">提交</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>
                </div>
            </div>
        </div>
    </div>
</div>

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
    var ctx = document.getElementById("myPieChart_System_Teacher");
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
