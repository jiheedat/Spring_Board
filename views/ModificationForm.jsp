<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<meta name = "viewport" content = "width= device-width" initial-scale= "1">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/ModificationForm.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">

<script>
	 $(function(){ //아이디 중복체크
		$("#id").on('blur',function(){
			let id = $("#id").val();
			$.ajax({
				type: 'post',
				data: JSON.stringify({"id":id}),
				contentType: "application/json; charset=utf-8",
				datatype: "json", 
				url: '${pageContext.request.contextPath}/ajax/idCheck',
				success: function(obj) {
					if(obj.idFromDB=="true" ) {
						$("#idError").text("사용가능한 아이디 입니다");
					} else if(obj.idFromDB=="false") {
						$("#idError").text("중복된 아이디 입니다");
					}
		 		},
				error: function(r,s,e) {
					alert("[에러] code:" + r.status
							+ "message:" + r.responseText
							+ "error:" + e);
				}
			});
		}); 
		$(".dropdown>a").click(function(){
			$(this).next("ul").toggleClass();
		});
	});
	function checkSubmit() {
		let pw = $("#pw").val();
		let pw2 = $("#pwCheck").val();
		let id = $("#id").val();
		let check = RegExp(/^[a-zA-Z0-9]{4,12}$/);
		let getName = RegExp(/^[가-힣]+$/);
		let getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	
		if(id==pw){
			alert("아이디와 비밀번호가 같습니다");
			$("#pw").val("");
			$("#pw").focus();
			return false;
		}
		if(!check.test(pw)){
			alert("비밀번호 형식에 맞게 입력해주세요");
			$("#pw").val("");
			$("#pw").focus();
			return false;
		}
		if(pw != pw2 ){
			alert("비밀번호를 확인해주세요");
			$("#pw").val("");
			$("#pwCheck").val("");
			$("#pw").focus();
			return false;
		}
		if(!getMail.test($("#email").val())){
			alert("이메일 형식을 확인해주세요");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
		if(!getName.test($("#name").val())){
			alert("이름을 확인해주세요");
			$("#name").val("");
			$("#name").focus();
			return false;
		}
	}
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
				<li><a href= "${pageContext.request.contextPath}/"> Home </a>
			</ul>
			<ul class = "nav navbar-nav navbar-right">
				<li class = "dropdown">
					<a href = "#" class  ="font dropdown-toggle" data-toggle = "dropdown" role="button" aria-haspopup="true" aria-expanded="false" >join us<span class = "caret"></span></a> 
					<ul class= "dropdown-menu" id="modal">
						<li class = "font"><a href ="${pageContext.request.contextPath}/">login</a></li>
						<li class="font active"><a href="Join">sign in</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class = "container" style="width:100%; height:100%;">
		<div class ="col-lg-4"></div>
		<div class ="col-lg-4">
			<div class = "font jumbotron" style = "padding-top: 20px;margin-top:80px;">
				<form action="Modification" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit();">
					<h3 id="modification">Modification</h3>
						<div class = >
							<label for="id"> 아이디 </label>
							<input type = "text" class = "form-control" placeholder= "${memberDetail.id }" name = "id"  id="id" value="${memberDetail.id }">
						</div>
						<div class = "form-group">
							<label for="pw"> 비밀번호 </label> 
							<input type = "password" class = "form-control" placeholder= "4~12자의 영문 대소문자와 숫자로만 입력" name = "pw" id="pw" maxlength = "20" required>
						</div>
						<div class = "form-group">
							<label for="pwCheck"> 비밀번호 확인</label>
							<input type="password" class="form-control"placeholder="비밀번호 확인" id="pwCheck" required/>
						</div>
						<div class = "form-group">
							<label for="name"> 이름 </label>
							<input type = "text" class = "form-control" placeholder= "한글입력 예) 홍길동" name = "name" id="name" maxlength = "20" required>
						</div>
						<div class = "form-group" id="email"></div>
						<div class = "form-group">
							<label for="email"> 이메일 </label>
							<input type = "email" class = "form-control" placeholder= "예) id @domain.com" name = "email" id="email" maxlength = "100" required>
						</div>
					[ 프로필 사진 변경 ]  
					<input type="file" name="file" id="profil" value="${memberDetail.profil }">
					<input type = "submit" class= "btn btn-primary form-control" value="수정완료">
					</form>
					<a href="DeleteMembership?id=${memberDetail.id }" onclick="return confirm('탈퇴하시겠습니까?');">회원탈퇴</a>
			</div>
	
	</div>
	</div>
</body>
</html>