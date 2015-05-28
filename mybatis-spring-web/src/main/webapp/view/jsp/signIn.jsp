<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="" />
		<meta name="author" content="" />
		<title>Signin</title>
		<!-- Custom styles for this template -->
		<link href="/css/signin.css" rel="stylesheet" />
	</head>
	<body>
		<%@include file="/common/head.html" %> 
		<div class="container">
			<form class="form-signin" action="/signInSubmit" method="post">
				<h2 class="form-signin-heading">
					Please sign in
				</h2>
				<label for="userName" class="sr-only">
					Email address
				</label>
				<input type="text" id="userName" name="userName" class="form-control" placeholder="UserName" autofocus required/>
				<label for="password" class="sr-only">
					Password
				</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>
				<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me" />
						Remember me
					</label>
				</div>
				<input type="hidden" id="uuid" value="${uuid }" name="uuid"/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">
					Sign in
				</button>
			</form>
		</div>
		<%@include file="/common/footer.html" %> 
	</body>
</html>