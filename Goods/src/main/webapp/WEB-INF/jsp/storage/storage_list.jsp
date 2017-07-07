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
    <title>仓库列表</title>

    <link href="../../../static/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">

</head>
<body class="pos-r">

    <nav class="breadcrumb">仓库列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="product_add('添加产品','<%=basePath%>Storage/goAdd','','800')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加仓库</a></span> <span class="r">共有数据：<strong>${tot}</strong> 条</span> </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <thead>
                <tr class="text-c">
                    <th width="40"><input name="" type="checkbox" value=""></th>
                    <th width="40">仓库编码</th>
                    <th width="60">仓库名</th>
                    <th width="60">仓库地址</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${varList}" var="var" varStatus="status">
                    <tr class="text-c va-m">
                        <td><input type="checkbox" value="${var.storage_id}" name="cbox[]"></td>
                        <td>${var.storage_id}</td>
                        <td>${var.storage_name}</td>
                        <td>${var.address}</td>
                        <td class="td-manage"><a style="text-decoration:none" class="ml-5" onClick="product_edit('${var.storage_name}','<%=basePath%>Storage/goedit','${var.storage_id}','800')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="product_del(this,'${var.storage_id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
        "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            {"orderable":false,"aTargets":[0,3]}// 制定列不参与排序
        ]
    });
    /*产品-添加*/
    function product_add(title,url,id,w,h){
        layer_show(title,url+"?storage_id="+id,w,h);
    }

    /*产品-编辑*/
    function product_edit(title,url,id,w,h){
        layer_show(title,url+"?storage_id="+id,w,h);
    }

    /*产品-删除*/
    function product_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            var rdata = {
                'storage_id':id
            };
            //alert(id);
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>Storage/del',
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
            "storage_id":good_code
        };
        var url = "<%=basePath%>Storage/delMuch";
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