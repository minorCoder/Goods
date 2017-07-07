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
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../../static/lib/html5shiv.js"></script>
    <script type="text/javascript" src="../../static/lib/respond.min.js"></script>

    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../../static/lib/Hui-iconfont/1.0.8/iconfont.css" />

    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="../../static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../static/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <link href="../../static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用编码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${code}" placeholder="输入借用编码"  id="reservation_id" name="reservation_id" disabled>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用人：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.name}" data = "${user.id}" placeholder="输入借用人" id="user" name="user" disabled>
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-sm-9"><button type="button" class="btn btn-primary radius" id = "addRow">添加</button></div>
        <table id =  "tableData" class="formControls table table-border table-bordered table-hover table-bg table-sort " cellspacing="0" with = "100%">
            <thead>
                <th>物品名</th>
                <th>物品数量</th>
                <th>物品价格</th>
                <th>剩余数量</th>
                <th>操作</th>
            </thead>
            <tbody>

            </tbody>
        </table>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>租用天数：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onchange="tot()" name ="Day" id = "Day" placeholder="租用天数" value="" class="input-text" style="width:90%">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用总价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name ="tot_value" id = "tot_value" placeholder="" value="" class="input-text" style="width:90%" disabled>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存 </button>
         </div>
        </div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../../static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../../static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="../../static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="../../static/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="../../static/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="../../static/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="../../static/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<!-- Data Tables -->
<script src="../../static/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="../../static/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<script type="text/javascript">

    $(function() {
        var t = $('#tableData').DataTable(
            {
                "scrollY":"200px",
                "scollCollapse":true,
                "paging":false
            }
        );

        $('#addRow').on( 'click', function () {
            t.row.add( [
               '<select name="Goods" id = "Goods" class="select" onchange="change(this)">'+
                '<c:forEach  items="${goods}" var="var" varStatus="status">'+
                '<option value = "${var.good_code}" lev= "${status.index}">${var.good_name}</option>'+
                    +'</c:forEach>'+
                    '</select>'
                ,'<div class="formControls col-xs-10 col-sm-9">'+
                '<input type="text" onchange="tot()" class="input-text" value="1" placeholder="输入租借物品数量" id="rent_num" name="rent_num">'
                    +'</div>',
                '<div class="formControls col-xs-10 col-sm-9">'+
                '<input type="text" onchange="tot()" class="input-text"  value="${goods[0].good_value}" placeholder="租借价格" id="rent_value" name="rent_value" disabled>'+
                    '</div>',
                '<div class="formControls col-xs-8 col-sm-9">'+
                '<input type="text"  class="input-text" value="${goods[0].good_num}" placeholder="剩余数量" id="num" name="num" disabled>'
                +'</div>'
                ,
                '<td class="td-manage"><a title="删除" href="javascript:;" onclick="member_del(this)" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>'
            ] ).draw();

        } );

        // Automatically add a first row of data
        $('#addRow').click();
    } );
    $(function() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    });

    $(function(){

        $("#form-add").validate({
            rules:{
                Day:{
                    required:true,
                    digits:true
                }
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                //$(form).ajaxSubmit();
                //var index = parent.layer.getFrameIndex(window.name);
                //parent.$('.btn-refresh').click();
                //parent.layer.close(index);
                //alert(123)
                formSubmit();
            }
        });
    });

    function member_del(obj){
        var t = $('#tableData').DataTable();
        t.row($(obj).parents('tr')).remove().draw();
    }

    /**
     * 提交
     */
    function formSubmit(){
        var trList = $("#tableData").find("tr");
        var list = "{ reservation_id:"+$("#reservation_id").val()+", reservator_id:"+$("#user").attr("data")
            +",reservation_da:"+$("#Day").val()+",hire:"+$("#tot_value").val()+",good_info:[";
        for (var i=1;i<trList.length;i++) {
                var tdArr = trList.eq(i).find("td");
                list=list+"{ good_code:"
                    +tdArr.eq(0).find("option:selected").val()+
                    ",num:"
                    +tdArr.eq(1).find("input").val()+
                    ",value:"+tdArr.eq(2).find("input").val()+"}";
                if(i!=trList.length-1)list=list+",";
        }
        list=list+"],tot:"+(trList.length-1)+"}";
        //alert(list);
        var data = eval( "(" + list + ")" );
        //alert(data.good_info);
        $.post("<%=basePath%>Reservation/add",data,function(rback){
            alert(rback.msg);
            layer.confirm(rback.msg,function(index){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        });
    };
    function tot(){
     var trList = $("#tableData").find("tr");
     var ans = 0;
     for (var i=1;i<trList.length;i++) {
         var tdArr = trList.eq(i).find("td");
         var name = tdArr.eq(0).find("option:selected").html()
         var nu = tdArr.eq(1).find("input").val();
         var now = tdArr.eq(3).find("input").val();
         if(parseInt(nu)>parseInt(now)){
             alert("物品"+name+"不足");
         }
         var va = tdArr.eq(2).find("input").val();
         ans = parseFloat(ans) + parseFloat(va)*parseFloat(nu);
     }
     var da = $("#Day").val();
     ans = ans*parseFloat(da);
     $("#tot_value").val(parseFloat(ans));
 }
    function change(obj){
        var tr = $(obj).parents('tr');
        var td = tr.find("td").find("option:selected").attr("value");//找到td元素
        var data = {
            "good_code":td
        };
        $.post("<%=basePath%>Good/find",data,function(data){
            //alert(data.good.good_num);
            var tt = tr.find("td").eq(2).find("input").val(data.good.good_value);
            var rr = tr.find("td").eq(3).find("input").val(data.good.good_num);
        });

    }
</script>
</body>
</html>