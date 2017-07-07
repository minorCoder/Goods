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
    <title>物品列表</title>

    <link href="../../../static/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">

</head>
<body class="pos-r">

    <nav class="breadcrumb">产品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"><c:if test="${sessionScope.role==0}"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="product_add('添加产品','<%=basePath%>Good/goAdd','','800')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a></span> </c:if><span class="r">共有数据：<strong>${tot}</strong> 条</span> </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr class="text-c">
                    <th width="40"><input name="" type="checkbox" value=""></th>
                    <th width="40">物品编码</th>
                    <th width="60">缩略图</th>
                    <th width="100">产品名称</th>
                    <th>摆放地址</th>
                    <th width="100">租借单价</th>
                    <th width="60">所属分类</th>
                    <th>剩余数量</th>
                    <c:if test="${sessionScope.role==0}">
                        <th width="100">操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${varList}" var="var" varStatus="status">
                    <tr class="text-c va-m">
                        <td><input type="checkbox" value="${var.good_code}" name="cbox[]"></td>
                        <td>${var.good_code}</td>
                        <td><a href="../../../static/${var.good_photo}" data-gallery=""><img height="60" width="60" class="product-thumb" src="../../../static/${var.good_photo}"></a></td>
                        <td class="text-l"><a style="text-decoration:none" onClick="product_show('${var.good_name}','<%=basePath%>Good/show','${var.good_code}','800')" href="javascript:;"><img title="国内品牌" src="../../../static/h-ui.admin/images/cn.gif"> ${var.good_name}</a></td>
                        <td class="text-l">${var.address}</td>
                        <td><span class="price"> ${var.good_value}</span>元/${var.good_unit}</td>
                        <td>${var.name}</td>
                        <td>${var.good_num}</td>
                        <c:if test="${sessionScope.role==0}">
                         <td class="td-manage"><a style="text-decoration:none" class="ml-5" onClick="product_edit('${var.good_name}','<%=basePath%>Good/goedit','${var.good_code}','800')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'${var.good_code}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="blueimp-gallery" class="blueimp-gallery">
            <div class="slides"></div>
            <h3 class="title"></h3>
            <a class="prev">‹</a>
            <a class="next">›</a>
            <a class="close">×</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
        </div>

    </div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../../static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../../static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="../../../static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../../../static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../../../static/lib/laypage/1.2/laypage.js"></script>
    <!-- blueimp gallery -->
    <script src="./../../static/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>

    <script type="text/javascript">

    $('.table-sort').dataTable({
        "aaSorting": [[ 5, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            {"orderable":false,"aTargets":[0,7]}// 制定列不参与排序
        ]
    });
    /*产品-添加*/
    function product_add(title,url,id,w,h){
        layer_show(title,url+"?good_code="+id,w,h);
    }
    /*产品-查看*/
    function product_show(title,url,id,w,h){
        layer_show(title,url+"?good_code="+id,w,h);
    }

    /*产品-编辑*/
    function product_edit(title,url,id,w,h){
        layer_show(title,url+"?good_code="+id,w,h);
    }

    /*产品-删除*/
    function product_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            var rdata = {
                'good_code':id
            };
            //alert(id);
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>Good/del',
                data:rdata,
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                    //刷新
                    location.replace(location.href);
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }


    //对分类的删除和table的刷新
    function deletegoods(good_code,num){
        //alert(good_code);
        //alert(num);
        if(num<=0)return;
        var data = {
            "num":num,
            "good_code":good_code
        };
        var url = "<%=basePath%>Good/delMuch";
        $.post(url,data,function(data){

            layer.msg('删除!'+data.msg,{icon:1,time:1000});

            //刷新
            location.replace(location.href);

        })
    }

    //多物品删除
    function datadel(){
        layer.confirm('确认要删除吗?',function(index){
            var bos = document.getElementsByName("cbox[]");
            var delnum = 0;
            var delnow = "[";
            for(var i=0;i<bos.length;i++){
                if(bos[i].checked){
                    delnow +="{" + bos[i].value + "},";
                    delnum++;
                }
            }
            delnow+="]";
            deletegoods(delnow,delnum);

        });
    }
</script>
</body>
</html>