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
    <!--/meta 作为公共模版分离出去-->

    <link href="../../../static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用编码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user_rent.rent_id}" placeholder="输入借用编码" id="rent_id" name="rent_id" disabled>
               </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用人：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				 <input type="text" class="input-text" value="${user_rent.name}" placeholder="" id="user_id" name="rent_id" disabled>
				</span> </div>
        </div>
        <div class="row cl">
        <table id =  "tableData" class="formControls table table-border table-bordered table-hover table-bg table-sort " cellspacing="0" with = "100%">
            <thead>
                <th>物品名</th>
                <th>物品数量</th>
                <th>物品价格</th>
            </thead>
            <tbody>
                <c:forEach items="${varList}" var="var">
                    <tr>
                        <td>${var.good_name}</td>
                        <td>${var.num}</td>
                        <td>${var.value}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>租用天数：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onchange="tot()" name ="Day" id = "Day" placeholder="租用天数" value="${user_rent.rent_date}" class="input-text" style="width:90%"disabled>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>借用总价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name ="tot_value" id = "tot_value" placeholder="" value="${user_rent.hire}" class="input-text" style="width:90%" disabled>
            </div>
        </div>


        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>押金：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name ="deposit" id = "deposit" placeholder="押金" value="${user_rent.deposit}" class="input-text" style="width:90%" disabled>
            </div>
        </div>

        <c:if test="${user_rent.status==0}">
            <div class="rol col">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>逾期：</label>
                <div class="formControls col-xs-2">
                    <input type="text" name ="overdue" id = "overdue" placeholder="" value="${user_rent.rent_time}" class="input-text" style="width:90%" disabled>
                </div>
                <label class="form-label col-xs-4"><span class="c-red">*</span>欠费(10元/天)：</label>
                <div class="formControls col-xs-2">
                    <input type="text" name ="money" id = "money" placeholder="" value="" class="input-text" style="width:90%" disabled>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onClick="save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 归还 </button>
                </div>
            </div>
        </c:if>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../../static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../../static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../../static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../../../static/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../../../static/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="../../../static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="../../../static/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="../../../static/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="../../../static/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="../../../static/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<!-- Data Tables -->
<script src="../../../static/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="../../../static/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<script type="text/javascript">
    $(function(){
        <c:if test="${user_rent.status==0}">
            var time1 =new Date($("#overdue").val()).getTime();
            var time2 = new Date().getTime();
            //alert(time2);//讲两个时间相减，求出相隔的天数
            var dayCount = (Math.abs(time2 - time1))/1000/60/60/24;
            var da = Math.ceil(dayCount)-parseInt($("#Day").val());
            var ovdu = (da)>=0?da:0;
            $("#overdue").val(ovdu);
            $("#money").val(ovdu*10);
        </c:if>
        var t = $('#tableData').DataTable({
                "scrollY":"200px",
                "scollCollapse":true,
                "paging":false
            }
        );
    });
    $(function() {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    });


    function member_del(obj){
        var t = $('#tableData').DataTable();
        t.row($(obj).parents('tr')).remove().draw();
    }

    $(function(){
        $("#form-add").validate({
            rules:{
                Day:{
                    required:true,
                    digits:true
                },
                deposit:{
                    required:true,
                    digits:true,
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


    /**
     * 提交
     */
    function formSubmit(){
        var trList = $("#tableData").find("tr");
        var list = "{ rent_id:"+$("#rent_id").val()+", user_id:"+${user_rent.user_id}
            +",overdue:"+$("#overdue").val()+",arrears_amount:"+$("#money").val()+",hire:"+$("#tot_value").val()+",deposit:"+$("#deposit").val()+",good_info:[";
        for (var i=1;i<trList.length;i++) {
            var tdArr = trList.eq(i).find("td");
            list=list+"{ good_code:"
                +tdArr.eq(0).attr("data")+
                ",num:"
                +tdArr.eq(1).html()+
                ",value:"+tdArr.eq(2).html()+"}";
            if(i!=trList.length-1)list=list+",";
        }
        list=list+"],tot:"+(trList.length-1)+"}";
        alert(list);
        var data = eval( "(" + list + ")" );
        //alert(123);
        $.post("<%=basePath%>Revert/add",data,function(data){
            alert(data.msg);
            layer.confirm(data.msg,function(index){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        });
    };

</script>
</body>
</html>