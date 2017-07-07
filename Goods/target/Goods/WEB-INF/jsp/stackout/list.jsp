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
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 出库列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/h-ui.admin/skin/default/skin.css" id="skin" />

    <link rel="shortcut icon" href="../../../static/error/favicon.ico">
    <link href="../../../static/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="../../../static/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">


</head>
<body>
<nav class="breadcrumb">出库管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
        <input type="text" class="input-text" style="width:250px" placeholder="输入用户名" id="info" name="">
        <button type="submit" onclick="Research()" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜记录</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="Stackout_add('添加入库','<%=basePath%>Stackout/goAdd','','800')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加出库</a></span> <span class="r">共有数据：<strong>${tot}</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort dataTables-example">
            <thead>
            <tr class="text-c">
                <th width="40"><input name="" type="checkbox" value=""></th>
                <th width="80">批次号</th>
                <th width="100">仓库名</th>
                <th width="40">物品名</th>
                <th width="40">物品数量</th>
                <th width="40">操作人</th>
                <th width="40">操作时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${varList}" var="var" varStatus="status">
                <tr class="text-c">
                    <td><input type="checkbox" value="${var.batchcode}" name="cbox[]"></td>
                    <td>${var.batchcode}</td>
                    <td>${var.storage_name}</td>
                    <td>${var.good_name}</td>
                    <td>${var.num}</td>
                    <td>${var.name}</td>
                    <td>${var.create_time}</td>
                    <td class="td-manage"><a style="text-decoration:none" class="ml-5" onClick="Stackout_del(this,'${var.batchcode}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
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
<script type="text/javascript" src="../../../static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../../../static/lib/laypage/1.2/laypage.js"></script>

<script src="../../../static/js/plugins/jeditable/jquery.jeditable.js"></script>

<!-- Data Tables -->
<script src="../../../static/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="../../../static/js/plugins/dataTables/dataTables.bootstrap.js"></script>


<!-- Page-Level Scripts -->
<script>

    var t;
    //datatables
    $(document).ready(
        function () {
         t = $('.dataTables-example').DataTable({
                    "aaSorting": [[ 6, "desc" ]],//默认第几个排序
                    "oLanguage": {
                    "oAria": {
                        "sSortAscending": " - click/return to sort ascending",
                        "sSortDescending": " - click/return to sort descending"
                    },
                    "sLengthMenu": "显示 _MENU_ 记录",
                    "sZeroRecords": "对不起，查询不到任何相关数据",
                    "sEmptyTable": "未有相关数据",
                    "sLoadingRecords": "正在加载数据-请等待...",
                    "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
                    "sInfoEmpty": "当前显示0到0条，共0条记录",
                    "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
                    "sProcessing": "<div class='sk-spinner sk-spinner-wave'> <div class='sk-rect1'></div><div class='sk-rect2'></div> <div class='sk-rect3'></div> <div class='sk-rect4'></div><div class='sk-rect5'></div></div>",
                    "sSearch": "查询：",
                    "sUrl": "",
                    //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": " 上一页 ",
                        "sNext": " 下一页 ",
                        "sLast": " 尾页 "
                    }
                },
            "aLengthMenu": [
                [5, 10, 15,  -1],['5', '10', '15' , "all"] // change per page values here
            ],

        });

    }
    );

    /*出库-添加*/
    function Stackout_add(title,url,pid,w,h){
        layer_show(title,url,w,h);
    }


    /*出库-删除*/
    function Stackout_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                data:{
                  'batchcode':id
                },
                url: '<%=basePath%>Stackout/del',
                dataType: 'json',
                success: function(data){
                    if(data.msg=="true") {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                    }else{
                        layer.msg('删除失败!', {icon: 1, time: 1000});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }

    //对分类的删除和table的刷新
    function deleteuser(batchcode,num){
        //alert(userid);
        //alert(num);
        if(num<=0)return;
        var data = {
            "num":num,
            "batchcode":batchcode
        };
        var url = "<%=basePath%>Stackout/deleteMuch";
        $.post(url,data,function(data){

            layer.msg('删除!'+data.msg,{icon:1,time:1000});

            //刷新
            location.replace(location.href);

        })
    }
    //分类删除
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
            deleteuser(delnow,delnum);

        });
    }
    function  Research() {
        t.draw();
    }
    //日期的搜索
    $.fn.dataTable.ext.search.push(
        function( settings, data, dataIndex ) {

            var min = new Date($('#datemin').val()).getTime();
            var max = new Date($('#datemax').val()).getTime();
            var dat = data[6];
            var mid = new Date(dat).getTime();
            var msg = $('#info').val();
            var test1 = false;
            if ( ( isNaN( min ) && isNaN( max ) ) ||
                ( isNaN( min ) && mid < max +60*60*24*1000) ||
                ( min <= mid   && isNaN( max ) ) ||
                ( min <= mid   && mid < max+60*60*24*1000 )) {
                test1 =true;
            }
            var test2 = false;
            if(msg==''||msg==null)test2 = true;
            else if(data[5]==msg)test2 = true;

            return test1&&test2;
        }
    );

</script>

</body>

</html>

