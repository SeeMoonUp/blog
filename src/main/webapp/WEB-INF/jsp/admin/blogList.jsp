<%--
  Created by IntelliJ IDEA.
  User: lvxiran
  Date: 2018/4/4
  Time: 上午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/static/sbAdmin2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/sbAdmin2/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/sbAdmin2/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/static/sbAdmin2/vendor/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/sbAdmin2/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <%@ include file="nav.jsp" %>
        <%@ include file="sidebar.jsp" %>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h4 class="page-header">博文列表</h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        list
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>标题</th>
                                    <th>概要</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${blogList}" var="blog">
                                    <tr>
                                        <td>${blog.id}</td>
                                        <td>${blog.title}</td>
                                        <td>${blog.title}</td>
                                        <td><a href="/mgr/blogdetail.html?blogId=${blog.id}">查看详情</a> </td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>Thornton</td>
                                    <td>@fat</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Larry</td>
                                    <td>the Bird</td>
                                    <td>@twitter</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/static/sbAdmin2/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/sbAdmin2/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/static/sbAdmin2/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/static/sbAdmin2/vendor/raphael/raphael.min.js"></script>
<script src="/static/sbAdmin2/vendor/morrisjs/morris.min.js"></script>
<script src="/static/sbAdmin2/data/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/static/sbAdmin2/dist/js/sb-admin-2.js"></script>

<!-- marked.js support convert to html -->
<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>

<script type="text/javascript">
    var _write = $("#markdownContent");
    _write.bind('input propertychange', function() {
        var markContent = marked(_write.val());
        $("#content").html(markContent);
        $("#htmlContent").val(markContent);
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + "px";
    });

    function save() {
        $.ajax({
            type: 'POST',
            url: '/mgr/savearticle.html',
            data:{
                title: $('#title').val(),
                htmlContent: $('#htmlContent').val(),
                markdownContent: $('#markdownContent').val(),
            },
            async: false,
            dataType: 'json',
            success: function(data){
                if (data.success) {
                    alert("操作成功");
                } else {
                    alert("操作失败");
                }
            },
            error: function(xhr, type){
                alert("链接失败");
            },
            complete: function () {

            }
        });
    }

    // _write.onkeyup = function() {
    //     this.style.height = 'auto';
    //     this.style.height = this.scrollHeight + "px";
    // }
</script>
</body>

</html>
