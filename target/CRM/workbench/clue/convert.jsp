﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

</head>
<body>
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" id="aname" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询" >
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="activitySearchBody">

						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="submitActivityBtn">提交</button>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${clue.fullname}${clue.appellation}-${clue.company}</small></h4>
	</div>

	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${clue.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${clue.fullname}${clue.appellation}
	</div>

	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
		<form id="tranForm" action="workbench/clue/convertConfirm.do" method="post">
			<input type="hidden" name="flag" value="">
			<input type="hidden" name="clueId" value="${clue.id}">
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="tranMoney">金额</label>
		    <input type="text" class="form-control" id="tranMoney" name="tranMoney">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tranName">交易名称</label>
		    <input type="text" class="form-control" id="tranName" name="tranName">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tranExpectedDate">预计成交日期</label>
		    <input type="text" class="form-control time" id="tranExpectedDate"name="tranExpectedDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tranStage">阶段</label>
		    <select id="tranStage"  class="form-control" name="tranStage">
		    	<option></option>
				<c:forEach items="${state}" var="s">
					<option value="${s.value}">${s.text}</option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tranActivity">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" id="openSearchModalBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" id="tranActivity" placeholder="点击上面搜索" readonly>
			  <input type="hidden" id="hidden-tranActivityId" name="tranActivityId">
		  </div>
		</form>
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${clue.owner}</b>
	</div>

	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" type="button" id="convertConfirmtBtn" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" value="取消">
	</div>
</body>

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(function(){

		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		$("#isCreateTransaction").click(function(){
			$("input[type=hidden]").val($('#isCreateTransaction').prop('checked'))
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});
		$("#openSearchModalBtn").click(function (){
			$("#searchActivityModal").modal("show");

		})
		$("#aname").keydown(function (event){

			if (event.keyCode==13){
				$.ajax({
					url:"workbench/clue/getActivityListByName.do",
					data:{
						"aname":$.trim($("#aname").val())
					},
					type:"get",
					dataType:"json",
					success:function (data){
						var html = "";
						$.each(data,function (i,n){

							html += '<tr>';
							html += '<td><input type="radio" name="xz" value="'+n.id+'"/></td>';
							html += '<td id="'+n.id+'">'+n.name+'</td>';
							html += '<td>'+n.startDate+'</td>';
							html += '<td>'+n.endDate+'</td>';
							html += '<td>'+n.owner+'</td>';
							html += '</tr>';
						})

						$("#activitySearchBody").html(html);

					}
				})
				return false;
			}
		})
		//搜索事件按钮事件
		$("#submitActivityBtn").click(function (){
			var id = $("input[name=xz]:checked").val();
			var name = $("#"+id).html();
			$("#activityId").val(id);
			$("#activityName").val(name);
			$("#searchActivityModal").modal("hide")

		});
		//转换按钮事件
		$("#convertConfirmtBtn").click(function (){
			if ($("#isCreateTransaction").prop("checked")){
				// alert("需要创建交易")
				<%--window.location.href = "workbench/clue/convert.do?clueId=${param.id}&moner=xxx&name=xxx&expectedDate=xxx&stage=xxx&activityName=xxx";--%>
				//上面这种方法太麻烦，改用表单提交
				$("#tranForm").submit();
			}else{
				// alert("不需要创建交易")
				window.location.href = "workbench/clue/convert.do?clueId=${clue.id}";
			}
		})

	})

</script>
</html>