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
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
    <dl style="margin-left:80px; color:#fff">
        <dt>
            <span class ="f pd-5" id ="qcode"></span>
        </dt>
        <dt>
            <span class="f-18">${user.name}</span>
            <span class="pl-10 f-12">欠费:${user.arrears_amount}</span>
        </dt>
        <dd class="pt-10 f-12" style="margin-left:0">物品逾期天数:${user.overdue}</dd>
    </dl>
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <tr>
            <th class="text-r" width="80">性别：</th>
            <td>${user.sex}</td>
        </tr>
        <tr>
            <th class="text-r" width="80">年龄：</th>
            <td>${user.age}</td>
        </tr>
        <tr>
            <th class="text-r">手机：</th>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <th class="text-r">邮箱：</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th class="text-r">地址：</th>
            <td>${user.address}</td>
        </tr>
        <tr>
            <th class="text-r">注册时间：</th>
            <td>${user.reg_date}</td>
        </tr>
        <tr>
            <th class="text-r">最后登录时间：</th>
            <td>${user.last_login}</td>
        </tr>
        </tbody>
    </table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../../static/js/qrcode.js"></script>
<script type="text/javascript" src="../../../static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->
<script type="text/javascript">
    $(function(){
            // 设置参数方式
            var qcode = new QRCode('qcode', {
                text: 'http://119.23.236.172:8080/Qcode/deal?id=${user.id}',
                width: 100,
                height: 100,
                colorDark : '#000000',
                colorLight : '#ffffff',
                correctLevel : QRCode.CorrectLevel.H
            });

        }
    );

</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>