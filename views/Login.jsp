<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="msg" value="${msg}"/>
<c:choose>
	<c:when test="${not empty msg}">
		<script>alert("${msg}");</script>
	</c:when>
	<c:when test="${not empty join}">
		<script>alert("${join}");</script>
	</c:when>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width= device-width" initial-scale= "1">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/Login.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">

<script>
	$(function(){
		$(".dropdown>a").click(function(){
			$(this).next("ul").toggleClass();
		});
	});	
</script>
</head>
<body>
	<nav class = "navbar navbar-default">
		<div class = "navbar-header">
		<button type = "button" class= "navbar-toggle collapsed" 
		data-toggle = "collapsed" data-target="#bs-example-navbar-collapse-1"
		aria-expanded= "false">
		<span class = "icon-bar"></span>
		<span class = "icon-bar"></span>
		<span class = "icon-bar"></span>
		</button>
		<a class = "font navbar-brand" href= "http://wlmgl0729.cafe24.com:9091/catchTable2024" target="_blank" style="color:#F5481A;">CatchTable 이동</a>
		</div>
		<div class = "collapse navbar-collapse" id = "bs-example-navbar-collapse-1"">
			<ul class = "font nav navbar-nav">
				<%-- <li><a href= "${pageContext.request.contextPath}/"> Home </a> --%>
				<li><a href= "https://political-avocado-414.notion.site/5-b60107c9be734b90b8323c48efc5881d?pvs=4" target="_blank"> 프로젝트 소개서 </a>
			</ul>
			<ul class = "nav navbar-nav navbar-right">
				<li class = "dropdown">
					<a href = "#" class  ="font dropdown-toggle" data-toggle = "dropdown" role="button" aria-haspopup="true" aria-expanded="false" >join us<span class = "caret"></span></a> 
					<ul class= "dropdown-menu" id="modal">
						<%-- <li class = "font"><a href ="${pageContext.request.contextPath}/">login</a></li>
						 --%><li class="font active"><a href = "Join">sign in</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class = "container">
		<div class ="col-lg-4"></div>
		<div class ="col-lg-4">
			<div class =" font jumbotron" id="Login">
				<form method = "post" action = "LoginCheck">
					<h3 id="Login_2">Login</h3>
					<div class = "form-group">
						<input type = "text" class = "form-control" placeholder= "ID" name ="id" maxlength = "20">
					</div>
					<div class = "form-group">
						<input type = "password" class = "form-control" placeholder= "PW" name ="pw" maxlength = "20">
					</div>
					<input type = "submit" class= "btn btn-primary form-control" value="로그인">
				</form>
				<div id="test">
					<div>TEST ID  :  ygyg12 </div>
					<div>TEST PW  :  1234 </div>
				</div>
			</div>
	</div>
	</div>
</body>
</html>