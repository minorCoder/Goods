<%--
  Created by IntelliJ IDEA.
  User: niyaowoa
  Date: 2017-04-05 
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> 外借管理系统</title>

    <meta name="keywords" content="外借管理系统">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="../../../static/error/favicon.ico"> <link href="../../../static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../../../static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="../../../static/css/animate.css" rel="stylesheet">
    <link href="../../../static/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">外借管理系统</strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">外借管理系统
                    </div>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">分类</span>
                </li>
                <li>
                    <a class="J_menuItem" href="http://ipr.zbj.com/sort?_union_uid=12142980&_union_itemid=53780">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">主页</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuItem" href="<%=basePath%>OptUser/show?id=${sessionUser.id}">个人资料</a>
                </li>
                <c:if test ="${sessionScope.role==0}">
                <li>
                    <a href="#">
                        <i class="fa fa fa-bar-chart-o"></i>
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="<%=basePath%>OptUser/list">用户列表</a>
                        </li>

                    </ul>
                </li>
                </c:if>
                <li class="line dk"></li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">物品管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <c:if test ="${sessionScope.role==0}">
                        <li><a class="J_menuItem" href="<%=basePath%>Category/list">分类管理</a>
                        </li>
                        </c:if>
                        <li><a class="J_menuItem" href="<%=basePath%>Good/list">物品管理</a>
                        </li>
                    </ul>
                </li>
                <c:if test ="${sessionScope.role==0}">
                <li>
                    <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">出库入库</span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="<%=basePath%>Storage/storage_list">仓库管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="<%=basePath%>Storage/list">查看库存</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="<%=basePath%>Stackout/list">出库</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="<%=basePath%>Stackin/list">入库</a>
                        </li>
                    </ul>
                </li>
                </c:if>
                <li>
                    <a href="#"><i class="fa fa-table"></i> <span class="nav-label">借用归还</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=basePath%>Rent/list">借用</a>
                        </li>
                        <li><a class="J_menuItem" href="<%=basePath%>Revert/list">归还</a>
                        </li>

                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">预约</span></a>
                    <ul class="nav nav-second-level">
                        <li> <a class="J_menuItem" href="<%=basePath%>Reservation/list?id="+${sessionUser.id}>预约</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">

                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-user"></span>  ${sessionUser.username}
                         </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','<%=basePath%>system/gochang_password','${sessionUser.id}','600','270')" href="javascript:;" title="修改密码">修改密码</a>
                            </li>
                            <li>
                                <a href="<%=basePath%>system/logout">
                                    <span >退出登录</span>
                                </a>
                            </li>

                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="https://www.baidu.com" frameborder="0" data-id="admin_index" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->
<script src="../../../static/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="../../../static/lib/layer/2.4/layer.js"></script>
<script src="../../../static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="../../../static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="../../../static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- 自定义js -->
<script src="../../../static/js/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="../../../static/js/index.js"></script>

<!-- 第三方插件 -->
<script src="../../../static/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript">
    /*密码-修改*/
    function change_password(title,url,id,w,h){

            layer.open({
                type: 2,
                area: ['600px', '360px'],
                shadeClose: true, //点击遮罩关闭
                content: url+'?id='+id
        });
    }
</script>
</body>

</html>
