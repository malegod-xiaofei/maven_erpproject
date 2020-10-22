<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="x-admin-sm">
<head>
<meta charset="UTF-8">
<title>登录</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/xadmin/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/xadmin/css/login.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/xadmin/css/xadmin.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/xadmin/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/xadmin/lib/layui/layui.js"
	charset="utf-8"></script>
</head>
<body class="login-bg">

	<div class="login layui-anim layui-anim-up">
		<div class="message">管理登录</div>
		<div id="darkbannerwrap"></div>

		<form method="post" class="layui-form">
			<input name="username" placeholder="用户名" type="text"
				lay-verify="required" class="layui-input">
			<hr class="hr15">
			<input name="password" lay-verify="required" placeholder="密码"
				type="password" class="layui-input">
			<hr class="hr15">
			<input value="登录" lay-submit lay-filter="login" style="width: 100%;"
				type="submit">
			<hr class="hr20">
		</form>
	</div>

	<script>
		$(function() {
			layui.use('form', function() {
				var form = layui.form;
				// layer.msg('玩命卖萌中', function(){
				//   //关闭后的操作
				//   });
				//监听提交
				form.on('submit(login)', function(data) {
					// data.field json格式的 用户名和密码
					//  Object { username="admin", password="root"}
					// 					console.log(data.field);
					// login是请求的路由
					$.post('login', data.field, function(obj) {
						// 把返回的json格式的字符串转换的json对象
						var json = JSON.parse(obj);
						// 1是成功
						if (json.result == 1) {
							layer.msg(json.msg, {
								icon : 1,
								time : 1000
							//2秒关闭（如果不配置，默认是3秒）
							}, function() {
								// 登陆成功后 跳转到主页
								location.href = 'index.jsp'
							});
						} else {
							layer.msg(json.msg, {
								icon : 2,
								time : 1000
							});
						}
					});
					return false;
				});

			});
		})
	</script>

</body>
</html>