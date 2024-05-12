<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width= device-width" initial-scale= "1">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/reply.css" rel ="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/UpdateForm.css" rel ="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title>Insert title here</title>
<script>
	$(function(){
		$("#logout").click(function(){
			if(confirm("로그아웃하시겠습니까?")==false) {
				return;
			}location.href="Logout";
				
		});
	});
</script>
</head>
<body>
		<nav class = "blue navbar navbar-default">
		<div class = "navbar-header">
		<button type = "button" class= "navbar-toggle collapsed" 
		data-toggle = "collapsed" data-target="#bs-example-navbar-collapse-1"
		aria-expanded= "false">
		<span class = "icon-bar"></span>
		<span class = "icon-bar"></span>
		<span class = "icon-bar"></span>
		</button>
		<a class = "font navbar-brand" href= "http://wlmgl0729.cafe24.com:9091/catchTable2024" target="_blank" id="catch_go">CatchTable 이동</a>
		</div>
		<div class = "collapse navbar-collapse" id = "bs-example-navbar-collapse-1">
			<ul class = "nav navbar-nav">
				<li class = "font active"><a href= "BoardList">Home</a>
			</ul>
		<ul class = "nav navbar-nav navbar-right">
				<li class = "dropdown">
				<a href = "#" class  ="font dropdown-toggle"
					data-toggle = "dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">logout<span class = "caret"></span></a>
					<ul class= "font dropdown-menu">
					<li class = 'font'><a id="logout">logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class = "container">
		<div class = "row">
			<form method = "post" action= "UpdateBoard">
				<input type="hidden" name="bno" value="${bno}"/>
				<table class = " font table table-striped " id="table">
					<thead>
						<tr>
							<th  colspan ="2" id="revise">Revise</th>
					</thead>
					<tbody>
						<tr>
							<td><input type = "text" class = "form-control" placeholder= "글 제목" name = "title" maxlength = "50" value= "${updateDto.title }"></td>
						</tr>
						<tr>
							<td><textarea class = "form-control" placeholder= "글 내용" name = "content" maxlength = "1000" id="update_form"> ${updateDto.content } </textarea></td>
						</tr>
					</tbody>
				</table>
					<input type = "submit" class = "btn btn-primary pull-right" value = "revise">
			</form>
		</div>
	</div>
</body>
</html>