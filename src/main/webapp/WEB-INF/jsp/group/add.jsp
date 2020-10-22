<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/xadmin/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/xadmin/js/xadmin.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row">
			<form class="layui-form" method="post" action="add" id='form'>
				<div class="layui-form-item">
					<label for="L_groupname" class="layui-form-label"> <span
						class="x-red">*</span>部门名
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_groupname" name="groupname" required=""
							lay-verify="groupname" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>将会成为您唯一的部门名
					</div>
				</div>
				<div class="layui-form-item">
					<label for="L_introduce" class="layui-form-label"> <span
						class="x-red">*</span>介绍
					</label>
					<div class="layui-input-inline">
						<input type="text" id="L_introduce" name="introduce" required=""
							lay-verify="introduce" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label for="L_repass" class="layui-form-label"></label>
					<button class="layui-btn" lay-filter="add" lay-submit="">增加</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		layui.use([ 'form', 'layer', 'jquery' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;

			//自定义验证规则
			form.verify({
				groupname : function(value) {
					if (value.length < 3) {
						return '部门名至少得3个字符啊';
					}
					var json;
					$.ajax({
						url : "verifyGroupname",
						type : 'post',
						async : false,
						data : {
							'groupname' : value
						},
						success : function(ajaxObj) {
							json = JSON.parse(ajaxObj);

						}
					});
					if (json.result == 0) {
						return '部门名已存在';
					}
				},
			});

			//监听提交
			form.on('submit(add)', function(data) {
				// 				console.log(data.field);
				//发异步，把数据提交给php

				$.post("add", data.field, function() {
					layer.alert("增加成功", {
						icon : 6
					}, function() {
						//关闭当前frame
						xadmin.close();
						// 提交action
						//$("#form").submit();
						// 可以对父窗口进行刷新 
						xadmin.father_reload();
					});
				});
				// xadmin.father_reload();
				return false;
			});

		});
	</script>

</body>

</html>