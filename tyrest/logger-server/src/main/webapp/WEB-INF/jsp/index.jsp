<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basepath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日志系统</title>
<meta name="description" content="首页">
<meta name="author" content="wq">
<link rel='shortcut icon' href='favicon.ico' type='image/x-icon'>
<link rel="stylesheet" href="<%=basepath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basepath%>/css/bootstrap.pagination.css">
<style>
    #page-more{padding:0 15px 0 15px;text-align:center;}
    .row{margin-left: 0;margin-right:0;}
</style>
</head>
<body id="logManage">
	<div class="row" id="searchArea" style="margin-top:20px;margin-bottom:20px;">
		<div class="col-md-2">
			日志实体<select id="logEntities" data-bind="
							    options: logEntities()"></select>
		</div>
		<div class="col-md-6" id="changableSearch">
			
		</div>
		<div class="col-md-1">
			<input type="button" class="btn btn-primary" value="搜索" data-bind="click:searchLog"/>
		</div>
	</div>
	<div class="row table-responsive">
		<table  class="table table-striped table-bordered table-hover" style="border-bottom-width: 0px;">
			<thead>
				<tr>
					<th>时间</th>
					<th>访问模块</th>
					<th>访问资源</th>
					<th>访问动作</th>
					<th>API级别</th>
					<th>HTTP方法</th>
					<th>操作人</th>
					<th>描述</th>
					<th>状态</th>
					<th>失败原因</th>
				</tr>
			</thead>
			<tbody data-bind="foreach:logList()">
				<tr data-bind="attr:{'id':id},click:$root.showLogContent">
					<td data-bind="text:$root.showTime(recDate)"></td>
					<td data-bind="text:module"></td>
					<td data-bind="text:resource"></td>
					<td data-bind="text:operation"></td>
					<td data-bind="text:apiLevel"></td>
					<td data-bind="text:httpMethod"></td>
					<td data-bind="text:caller"></td>
					<td data-bind="text:description"></td>
					<td data-bind="text:result"></td>
					<td data-bind="text:errorMessage"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="page-more"></div>
	<div id="detailModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">日志详情</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<pre>输入:<code id="detail-parameters"></code></pre>
						</div>
						<div class="col-md-6">
							<pre>输出:<code id="detail-logContent"></code></pre>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basepath%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/jspretty.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/bootstrap-paginator.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/knockout.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/basetool.js"></script>
	<script type="text/javascript" src="<%=basepath%>/js/logmanage.js"></script>
</body>
</html>