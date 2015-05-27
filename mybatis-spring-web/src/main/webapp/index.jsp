<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>
			Bootstrap 101 Template
		</title>
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<link href="../css/index.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="../js/html5shiv.min.js"></script>
			<script src="../js/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">
							Toggle navigation
						</span>
						<span class="icon-bar">
						</span>
						<span class="icon-bar">
						</span>
						<span class="icon-bar">
						</span>
					</button>
					<a class="navbar-brand" href="#">
						Project name
					</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<div class="navbar-form navbar-right">
						<button id="signUp" type="button" class="btn btn-success">Sign Up</button>
						<button id="signIn" type="button" class="btn">Sign In</button>
					</div>
				</div>
			</div>
		</nav>
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
		<!-- /.container -->
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<script src="../js/index.js"></script>
	</body>
</html>