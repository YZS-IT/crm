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

<%--七个隐藏域存储条件查询的值--%>
<input type="hidden" id="hidden_fullname" value="">
<input type="hidden" id="hidden_company" value="">
<input type="hidden" id="hidden_phone" value="">
<input type="hidden" id="hidden_source" value="">
<input type="hidden" id="hidden_owner" value="">
<input type="hidden" id="hidden_mphone" value="">
<input type="hidden" id="hidden_state" value="">

	<!-- 创建线索的模态窗口 -->
	<div class="modal fade" id="createClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="createClueForm">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner" name="owner">

								</select>
							</div>
							<label for="create-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-company" name="company">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-appellation" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-appellation" name="appellation">
								  <option></option>
									<c:forEach items="${applicationScope.appellation}" var="a">
										<option value="${a.value}">${a.text}</option>
									</c:forEach>
								</select>
							</div>
							<label for="create-fullname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-fullname" name="fullname">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job" name="job">
							</div>
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email" name="email">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone" name="phone">
							</div>
							<label for="create-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website" name="website">
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone" name="mphone">
							</div>
							<label for="create-clueState" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-clueState" name="state">
								  <option></option>
									<c:forEach items="${applicationScope.clueState}" var="c">
										<option value="${c.value}">${c.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="create-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-source" name="source">
								  <option></option>
									<c:forEach items="${applicationScope.source}" var="s" >
										<option value="${s.value}">${s.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						

						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">线索描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description" name="description"></textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>
						
						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="create-contactSummary" name="contactSummary"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control time" id="create-nextContactTime" name="nextContactTime" readonly>
								</div>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>
						
						<div style="position: relative;top: 20px;">
							<div class="form-group">
                                <label for="create-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="create-address" name="address"></textarea>
                                </div>
							</div>
						</div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改线索的模态窗口 -->
	<div class="modal fade" id="editClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改线索</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form" id="editForm">

						<input type="hidden" id="edit-id"  name="id" value="">

						<div class="form-group">
							<label for="edit-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner" name="owner">
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company" name="company" value="动力节点">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-appellation" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-appellation" name="appellation">
									<option></option>
									<c:forEach items="${applicationScope.appellation}" var="s" >
										<option value="${s.value}">${s.text}</option>
									</c:forEach>
								</select>
							</div>
							<label for="edit-fullname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-fullname" name="fullname" value="">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job" name="job" value="CTO">
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email" name="email" value="lisi@bjpowernode.com">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone" name="phone" value="010-84846003">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website" name="website" value="http://www.bjpowernode.com">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone" name="mphone" value="12345678901">
							</div>
							<label for="edit-state" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-state" name="state">
									<option></option>
									<c:forEach items="${applicationScope.clueState}" var="c">
										<option value="${c.value}">${c.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-source" name="source">
									<option></option>
									<c:forEach items="${applicationScope.source}" var="s" >
										<option value="${s.value}">${s.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description" name="description">这是一条线索的描述信息</textarea>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>
						
						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="edit-contactSummary" name="contactSummary">这个线索即将被转换</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-nextContactTime" name="nextContactTime" value="2017-05-01">
								</div>
							</div>
						</div>
						
						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address" name="address">北京大兴区大族企业湾</textarea>
                                </div>
                            </div>
                        </div>
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>线索列表</h3>
			</div>
		</div>
	</div>
	
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
	
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

<%--			按条件所搜线索栏目--%>
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="query_fullname">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司</div>
				      <input class="form-control" type="text" id="query_company">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司座机</div>
				      <input class="form-control" type="text" id="query_phone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索来源</div>
					  <select class="form-control" id="query_source" >
					  	  <option></option>
							<c:forEach items="${applicationScope.source}" var="s">
								<option value="${s.value}">${s.text}</option>
							</c:forEach>
					  </select>
				    </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="query_owner">
				    </div>
				  </div>
				  
				  
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">手机</div>
				      <input class="form-control" type="text" id="query_mphone">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索状态</div>
					  <select class="form-control" id="query_state">
					  	<option></option>
						  <c:forEach items="${applicationScope.clueState}" var="s">
							  <option value="${s.value}">${s.text}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

				  <button type="button" class="btn btn-default" id="queryBtn">查询</button>
				  
				</form>
			</div>

<%--			线索的增删改栏目--%>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 40px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
			</div>

<%--			线索详细栏目--%>
			<div style="position: relative;top: 50px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
							<td>公司</td>
							<td>公司座机</td>
							<td>手机</td>
							<td>线索来源</td>
							<td>所有者</td>
							<td>线索状态</td>
						</tr>
					</thead>
					<tbody id="clueBox">
<%--						<tr>--%>
<%--							<td><input type="checkbox" /></td>--%>
<%--							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/clue/detail.jsp?id=b6e5fd97f58a4f1e80212b1a5ca143a5';">马云先生</a></td>--%>
<%--							<td>阿里巴巴</td>--%>
<%--							<td>010-88888888</td>--%>
<%--							<td>13888888888</td>--%>
<%--							<td>合作伙伴</td>--%>
<%--							<td>张三</td>--%>
<%--							<td>将来联系</td>--%>
<%--						</tr>--%>
					</tbody>
				</table>
			</div>

<%--			底部分页UI插件--%>
			<div style="height: 50px; position: relative;top: 60px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="total" ></b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="pageSize">${applicationScope.pageSize[0].text}</button>
						<ul class="dropdown-menu" role="menu">
							<c:forEach items="${applicationScope.pageSize}" var="p">
								<li><a data-toggle="" onclick="if($('#pageSize').html()!='${p.text}') queryClue(1,'${p.text}')">${p.text}</a></li>
							</c:forEach>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination" id="pageCarts">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>


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
			pickerPosition: "top-left"
		});

		$("#qx").click(function (){
			$('.xz').prop("checked",$(this).prop("checked"))
		})
		$("#clueBox").on("click",".xz",function (){
			$("#qx").prop("checked",$(".xz:checked").length==$(".xz").length)
		})


		$("#addBtn").click(function(){
			$.get("workbench/clue/getUserList.do",null,function (data) {
				var html = "";
				$.each(data,function (i,n){
					html += "<option value='"+n.id+"'>"+n.name+"</option>";
				})
				$("#create-owner").html(html);
				var id ="${user.id}";
				$("#create-owner").val(id);
				$("#createClueModal").modal("show");
			})
		})

		//为保存按钮添加事件，执行线索添加操作
		$("#saveBtn").click(function (){
			if ($.trim($('#create-company').val())==""||$.trim($('#create-fullname').val())==""){
				alert("公司和姓名不能为空")
				return
			}
			var param = $('#createClueForm').serialize()
			$.post("workbench/clue/save.do",param,function (data) {
				if (data.success){
					// $('#createClueForm')[0].reset()
					$("#createClueModal").modal("hide");
					queryClue(1,$('#pageSize').html())
				}else{
					alert("线索添加失败")
				}
			})
		})

		$('#editBtn').click(function (){
			var $xz = $(".xz:checked");
			if ($xz.length==0){
				alert("请选择需要修改的记录")
				return
			}
			if ($xz.length>1){
				alert("对不起，一次只能同时修改一个记录，请重新选择")
				return;
			}
			if ($xz.length==1){
				var id = $('.xz:checked').prop("id")
				$.get("workbench/clue/edit.do",{id},function (data) {
					//用户列表
					var html = ""
					$.each(data.userList,function (i,n) {
						html+="<option value='"+n.id+"'>"+n.name+"</option>"
					})
					$('#edit-owner').html(html)
					//修改线索模板
					$('#edit-id').val(data.clue.id)
					$('#edit-owner').val(data.clue.owner)
					$('#edit-company').val(data.clue.company)
					$('#edit-appellation').val(data.clue.appellation)
					$('#edit-fullname').val(data.clue.fullname)
					$('#edit-job').val(data.clue.job)
					$('#edit-email').val(data.clue.email)
					$('#edit-phone').val(data.clue.phone)
					$('#edit-website').val(data.clue.website)
					$('#edit-mphone').val(data.clue.mphone)
					$('#edit-state').val(data.clue.state)
					$('#edit-source').val(data.clue.source)
					$('#edit-description').val(data.clue.description)
					$('#edit-contactSummary').val(data.clue.contactSummary)
					$('#edit-nextContactTime').val(data.clue.nextContactTime)
					$('#edit-address').val(data.clue.address)
				})

				$('#editClueModal').modal("show");
			}
		})

		$('#updateBtn').click(function (){
			$.post("workbench/clue/update.do",$('#editForm').serialize(),function (data) {
				if (data.success){
					$('#editClueModal').modal("hide");
					queryClue($('.active a').html(),$('#pageSize').html())
				}else{
					alert("更新失败")
				}
			})
		})

		$('#deleteBtn').click(function (){
			var $xz = $(".xz:checked");
			if ($xz.length==0){
				alert("请选择需要修改的记录")
				return
			}
			if($xz.length > 0 && confirm("确认删除所选条目吗?该操作不可恢复")){

				var param = ""

				$.each($xz,function (i,n) {
					param += "id=" + n.id + "&"
				})

				param = param.substring(0,param.length-1)

				$.post("workbench/clue/delete.do",param,function (data) {
					if (data.success){
						queryClue($xz.length==$(".xz").length&&$('.active a').html()!=1?$('.active a').html()-1:$('.active a').html(),$('#pageSize').html())
					}else{
						alert("删除失败")
					}
				})
			}
		})



		$("#queryBtn").click(function (){

			$("#hidden_fullname").val($.trim($("#query_fullname").val()))
			$("#hidden_company").val($.trim($("#query_company").val()))
			$("#hidden_owner").val($.trim($("#query_owner").val()))
			$("#hidden_phone").val($.trim($("#query_phone").val()))
			$("#hidden_mphone").val($.trim($("#query_mphone").val()))
			$("#hidden_state").val($.trim($("#query_state").val()))
			$("#hidden_source").val($.trim($("#query_source").val()))

			queryClue(1,$("#pageSize").html())
		})



	});

	//定义一个变量存储跳转页面之前的旧页面,用于判断分页卡片的显示内容
	var prePageNum = 1

	queryClue(1,$('#pageSize').html())

	function queryClue(pageNum,pageSize){

		$('#qx').prop("checked",false)

		$('#pageSize').html(pageSize)

		$.post("workbench/clue/queryClue.do",{
			pageNum,
			pageSize,
			"fullname":$('#hidden_fullname').val(),
			"company":$('#hidden_company').val(),
			"phone":$('#hidden_phone').val(),
			"source":$('#hidden_source').val(),
			"owner":$('#hidden_owner').val(),
			"mphone":$('#hidden_mphone').val(),
			"state":$('#hidden_state').val()
		},function (data) {

			$('#total').html(data.total)

			var html = ""
			var flag = ""

			if (pageNum > 1){
				html+="<li><a data-toggle='' onclick='queryClue(1,"+$('#pageSize').html()+")'>首页</a></li>"
				html+="<li><a data-toggle='' onclick='queryClue("+(pageNum-1)+","+$('#pageSize').html()+")'>上一页</a></li>"
			}
			if (pageNum >= prePageNum){
				if (pageNum + 4 <= data.pages){
					for (var i = pageNum;i <= pageNum + 4;i++){
						if (i == pageNum) flag="active"; else flag="";
						html+="<li class='"+flag+"'><a data-toggle='' onclick='queryClue("+i+","+$('#pageSize').html()+")' >"+i+"</a></li>"
					}
				}else{
					var mHtml = ""
					for (var i = data.pages;i >= 1 && i>=data.pages-4;i--){
						if (i == pageNum) flag="active"; else flag="";
						mHtml = "<li class='"+flag+"'><a data-toggle='' onclick='queryClue("+i+","+$('#pageSize').html()+")'>"+i+"</a></li>" + mHtml
					}
					html+=mHtml;
				}
			}else {
				if ( pageNum >= 5){
					for (var i = (pageNum-4);i<=pageNum;i++){
						if (i == pageNum) flag="active"; else flag="";
						html+="<li class='"+flag+"'><a data-toggle='' onclick='queryClue("+i+","+$('#pageSize').html()+")'>"+i+"</a></li>"
					}
				}else{
					for (var i = 1;i <= 5 && i<=data.pages;i++){
						if (i == pageNum) flag="active"; else flag="";
						html+="<li class='"+flag+"'><a data-toggle='' onclick='queryClue("+i+","+$('#pageSize').html()+")'>"+i+"</a></li>"
					}
				}
			}

			if (pageNum < data.pages){
				html+="<li><a data-toggle='' onclick='queryClue("+data.pages+","+$('#pageSize').html()+")'>末页</a></li>"
				html+="<li><a data-toggle='' onclick='queryClue("+(pageNum+1)+","+$('#pageSize').html()+")'>下一页</a></li>"
			}

			$('#pageCarts').html(html)
			prePageNum = pageNum

			html=""
			$.each(data.clueList,function (i,n) {
				html+="<tr><td><input type='checkbox' class='xz' id='"+n.id+"'/></td>" +
						"<td><a style='text-decoration: none; cursor: pointer;' onclick=\"window.location.href='workbench/clue/detail.do?id="+n.id+"'\">"+n.fullname+"</a></td>" +
						"<td>"+n.company+"</td>" +
						"<td>"+n.phone+"</td>" +
						"<td>"+n.mphone+"</td>" +
						"<td>"+n.source+"</td>" +
						"<td>"+n.owner+"</td>" +
						"<td>"+n.state+"</td></tr>"
			})
			$("#clueBox").html(html)

			$("#query_fullname").val($("#hidden_fullname").val())
			$("#query_company").val($("#hidden_company").val())
			$("#query_owner").val($("#hidden_owner").val())
			$("#query_phone").val($("#hidden_phone").val())
			$("#query_mphone").val($("#hidden_mphone").val())
			$("#query_state").val($("#hidden_state").val())
			$("#query_source").val($("#hidden_source").val())

		})

	}



</script>

</body>
</html>