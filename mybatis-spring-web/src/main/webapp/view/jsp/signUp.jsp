<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>
			SignUp
		</title>
		<link href="/css/signup.css" rel="stylesheet">
	</head>
	<body>
		<%@include file="/common/head.html" %>
		<div class="container">
			<form id="signUpForm" action="<%=request.getContextPath()%>/signUpSubmit" class="form-signup">
				<div class="form-group">
					<label for="account">
						Account
					</label>
					<input type="text" class="form-control" id="account" name="account" 
					placeholder="Enter Account" required autofocus>
				</div>
				<div class="form-group">
					<label for="password">
						Password
					</label>
					<input type="password" class="form-control" id="password" name="password"
					placeholder="Password" required>
				</div>
				<div class="form-group">
					<label for="rePassword">
						Repeat Password
					</label>
					<input type="password" class="form-control" id="rePassword" name="rePassword"
					placeholder="Repeat Password" required>
				</div>
				<input type="hidden" id="uuid" value="${uuid }" name="uuid"/>
				<button type="submit" class="btn btn-default">Submit</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</form>
		</div>
		<%@include file="/common/footer.html" %>
		<script type="text/javascript" src="/js/signUp.js"></script>
	</body>
</html>