<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/xadmin/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/xadmin/css/xadmin.css">
<script
	src="<%=request.getContextPath()%>/resources/xadmin/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/xadmin/js/xadmin.js"></script>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">部门管理</a>
			<a> <cite>部门展示</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			onclick="location.reload()" title="刷新"> <i
			class="layui-icon layui-icon-refresh" style="line-height: 30px"></i></a>
	</div>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body ">
						<form class="layui-form layui-col-space5" method="post"
							action="vagueGroupname" id='form'>
							<div class="layui-inline layui-show-xs-block">
								<input type="text" name="groupname" placeholder="请输入部门名"
									autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline layui-show-xs-block">
								<button class="layui-btn" lay-submit="" lay-filter="sreach">
									<i class="layui-icon">&#xe615;</i>
								</button>
							</div>
						</form>
					</div>
					<div class="layui-card-header">
						<button class="layui-btn"
							onclick="xadmin.open('添加部门','addInput',800,600)">
							<i class="layui-icon"></i>添加
						</button>
					</div>
					<div class="layui-card-body layui-table-body layui-table-main">
						<table class="layui-table layui-form">
							<thead>
								<tr>
									<th>编号</th>
									<th>部门名称</th>
									<th>部门介绍</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groups }" var="group">
									<tr>
										<td>${group.id }</td>
										<td>${group.groupname }</td>
										<td><c:choose>
												<c:when test="${empty group.introduce }">该部门比较懒,没有介绍</c:when>
												<c:otherwise>${group.introduce }</c:otherwise>
											</c:choose></td>
										<td class="td-manage"><a title="编辑"
											onclick="xadmin.open('编辑','updateInput?id=${group.id}',800,600)"
											href="javascript:;"> <i class="layui-icon">&#xe642;</i>
										</a> <a title="删除" onclick="member_del(this,${group.id })"
											href="javascript:;"> <i class="layui-icon">&#xe640;</i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	layui.use([ 'laydate', 'form' ], function() {
		var laydate = layui.laydate;
		var form = layui.form;
	});
		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
					$.post('delete', {"id":id}, function(ajaxObj) {
						// 把返回的json格式的字符串转换的json对象
						var json = JSON.parse(ajaxObj);
						// 1是成功
						if (json.result == 1) {
							// 在页面中删除这个数据
							$(obj).parents("tr").remove();
							// 提示信息
							layer.msg(json.msg,{
								  icon: 1,
								  time: 1000 //2秒关闭（如果不配置，默认是3秒）
								});
						} else {
							layer.msg(json.msg,{
								 icon: 2,
								  time: 1000 
							});
						}
					});
			});
		}
</script>
</html>