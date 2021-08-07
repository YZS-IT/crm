<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='settings/dictionary/value/add.jsp'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" onclick="editFun()"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="qx"/></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody id="valueBox">
			<c:forEach items="${valueList}" var="v" varStatus="status">
				<tr class="active">
					<td><input type="checkbox" class="xz" id="${v.id}"/></td>
					<td>${status.count}</td>
					<td>${v.value}</td>
					<td>${v.text}</td>
					<td>${v.orderNo}</td>
					<td>${v.typeCode}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

	$(function (){

		$('#qx').click(function (){
			$('.xz').prop("checked",$('#qx').prop("checked"))
		})
		$('#valueBox').on("click",'.xz',function (){
			$('#qx').prop("checked",$('.xz:checked').length==$('.xz').length)
		})

		$('#deleteBtn').click(function (){

			if ($('.xz:checked').length==0){
				alert("请先选中要删除的条目")
				return
			}

			if (confirm("确认删除所选条目吗?该操作不可恢复")){

				var param = ""
				$.each($('.xz:checked'),function (i,n) {
					param += "id=" + $(n).prop('id')
					param += "&"
				})
				param = param.substring(0,param.length-1)

				$.post("settings/dictionary/value/delete.do",param, function (data) {
					if(data.success){
						window.location.reload()
					}else{
						alert("删除失败!")
					}
				})
			}
		})

	})

	function editFun() {
		if ($('.xz:checked').length==0){
			alert("请先选中要编辑的条目")
			return
		}

		if ($('.xz:checked').length > 1){
			alert("只能编辑一条条目")
			return
		}
		window.location.href='settings/dictionary/value/edit.do?id='+$('.xz:checked').prop("id")
	}
</script>
</body>
</html>