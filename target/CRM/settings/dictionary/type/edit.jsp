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
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>修改字典类型</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form" id="updateForm">
		<input type="hidden" name="id" value="${t.id}">
		<div class="form-group">
			<label for="update-code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="update-code" name="code" style="width: 200%;" placeholder="编码作为主键，不能是中文" value="${t.code}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="update-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="update-name" name="name" style="width: 200%;" value="${t.name}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="update-description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="update-description" name="description" style="width: 200%;">${t.description}</textarea>
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function (){
		$('#updateBtn').click(function (){
			$.post("settings/dictionary/type/update.do",$('#updateForm').serialize(),function (data) {
				if (data.success){
					window.location.href="settings/dictionary/type/index.do"
				}else{
					alert("更新字典类型失败")
				}
			})
		})
	})
</script>
</body>
</html>