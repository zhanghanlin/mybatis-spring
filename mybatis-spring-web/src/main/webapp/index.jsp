<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>
			Home
		</title>
		<link href="../css/index.css" rel="stylesheet">
	</head>
	<body>
		<%@include file="/common/head.html" %>
		<div class="container">
			<div class="starter-template">
				<h1>
					Hi,${userName }
				</h1>
				<p class="lead">
					Use this document as a way to quickly start any new project.
					<br>
					All you get is this text and a mostly barebones HTML document.
				</p>
			</div>
		</div>
		<%@include file="/common/footer.html" %>
	</body>
</html>