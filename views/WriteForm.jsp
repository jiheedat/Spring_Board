<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width= device-width" initial-scale= "1">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/WriteForm.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<title>로그인 게시판</title>
</head>
<body>
	<nav class = "navbar navbar-default">
		<div class = "navbar-header">
			<button type = "button" class= "navbar-toggle collapsed" data-toggle = "collapsed" data-target="#bs-example-navbar-collapse-1" aria-expanded= "false">
				<span class = "icon-bar"></span>
				<span class = "icon-bar"></span>
				<span class = "icon-bar"></span>
			</button>
			<a class = "font navbar-brand"id="catch_go" href= "http://wlmgl0729.cafe24.com:9091/catchTable2024" target="_blank">CatchTable 이동</a>
		</div>
		<div class = "collapse navbar-collapse" id = "bs-example-navbar-collapse-1">
			<ul class = "nav navbar-nav">
				<li class = "font"><a href= "BoardList">HOME</a>
			</ul>
		</div>
	</nav>
	<div class = "container">
		<div class = "row">
			<form method = "post" action= "BoardWrite">
				<table class = "font table table-striped " id="row_table">
					<thead>
						<tr>
							<th  colspan ="2" id="table_writer">write</th>
					</thead>
					<tbody>
						<tr>
							<td><input type = "text" class = "form-control" placeholder= "글 제목" name = "title" maxlength = "50"></td>
						</tr>
						<tr>																											<!-- required -->
							<td><textarea class = "form-control" placeholder= "글 내용" name = "content" maxlength = "1000" id="height" ></textarea></td>
						</tr>
					</tbody>
				</table>
				<input type = "submit" class = "btn btn-primary pull-right" value = "submit">
			</form>
		</div>
	</div>
</body>
</html>