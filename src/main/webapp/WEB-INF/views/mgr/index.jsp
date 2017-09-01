<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript" src="../plugins/jquery/jquery-1.12.4.min.js"></script>
<link href="../plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../plugins/theme/blue/images/icon.jpg" rel="stylesheet">
<link href="../plugins/theme/blue/signin.css" rel="stylesheet">
<link href="../plugins/theme/blue/icons.css" rel="stylesheet">
<script type="text/javascript"
	src="../plugins/bootstrap/js/bootstrap.js"></script>
<title>欢迎</title>
</head>
<body>
	<div class="container">
		<form class="form-signin">
			<h2 class="form-signin-heading">数据同步系统登陆</h2>
			
			<label for="inputEmail" class="sr-only">帐号</label>
			<input type="email" id="inputEmail" class="form-control" placeholder="帐号" required autofocus>
			<label for="inputPassword" class="sr-only">密码</label>
			<input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录
				in</button>
		</form>
	</div>
</body>
</html>
