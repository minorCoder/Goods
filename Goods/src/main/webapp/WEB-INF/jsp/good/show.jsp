<%--
  Created by IntelliJ IDEA.
  User: niyaowoa
  Date: 2017-04-08
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../../../static/lib/html5shiv.js"></script>
    <script type="text/javascript" src="../../../static/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../../static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>用户查看</title>
</head>
<body >
<div class="cl pd-20" style=" background-color:dodgerblue">
    <img class="avatar size-XXXL l" src="../../../static/${good.good_photo}">
    <dl style="margin-left:150px;color: lightgrey">
        <dt>
            <span class="f-18">物品编码:&nbsp;&nbsp;&nbsp;&nbsp;</span>${good.good_code}
        </dt>
        <dt>
            <span class="f-18">物品名称:&nbsp;&nbsp;&nbsp;&nbsp;</span>${good.good_name}
        </dt>
        <dt>
            <span class="f-18">物品数量:&nbsp;&nbsp;&nbsp;&nbsp;</span>${good.good_num}&nbsp;${good.good_unit}
        </dt>
        <dt>
            <span class="f-18">所属分类:&nbsp;&nbsp;&nbsp;&nbsp;</span>${good.name}
        </dt>
    </dl>
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r f-18" width="150"><span class="f-18">物品所在位置:</span></th>
            <td style="color:black">${good.address}</td>
        </tr>

        <tr>
            <th class="text-r f-18" width="150"><span class="f-18">描述:</span></th>
            <td style="color:black">${good.good_remarks}</td>
        </tr>
        </tbody>
    </table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../../static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
</body>
</html>