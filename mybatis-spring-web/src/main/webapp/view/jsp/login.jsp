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
		<title>
			Signin Template for Bootstrap
		</title>
		<!-- Bootstrap core CSS -->
		<link href="../css/bootstrap.min.css"
		rel="stylesheet" />
		<!-- Custom styles for this template -->
		<link href="../css/signin.css" rel="stylesheet" />
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]>
			<script src="../js/assets/ie8-responsive-file-warning.js"></script>
		<![endif]-->
		<script src="../js/assets/ie-emulation-modes-warning.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media
		queries -->
		<!--[if lt IE 9]>
			<script src="../js/html5shiv.min.js"></script>
			<script src="../js/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="container">
			<form class="form-signin" action="/signIn" method="post">
				<h2 class="form-signin-heading">
					Please sign in
				</h2>
				<h4 class="form-signin-heading">${error_info }</h4>
				<label for="userName" class="sr-only">
					Email address
				</label>
				<input type="text" id="userName" name="userName" class="form-control" placeholder="UserName"/>
				<label for="password" class="sr-only">
					Password
				</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password"/>
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
		<!-- /container -->
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../js/assets/ie10-viewport-bug-workaround.js">
		</script>
	</body>
</html>