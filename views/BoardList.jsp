<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width= device-width" initial-scale= "1">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/BoardList.css" rel ="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script>
		$(function(){
			$(".tr").click(function(){
				let bno = $(this).find(".bno").text();
				location.href="BoardDetail?bno="+bno;
			});
			$("#logout").click(function(){
				if(confirm(" 로그아웃하시겠습니까? ")==false){
					return;
				}location.href="${pageContext.request.contextPath}/Logout";
			});
			$("#table>tr").mouseover(function(){
				$(this).addClass("textalign");
			}).mouseout(function(){
				$(this).removeClass("textalign");
			});
			
		 });
		
	</script>
<title>로그인 게시판</title>
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
				<a href = "#" class  =" font dropdown-toggle"
					data-toggle = "dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">join us<span class = "caret"></span></a>
					<ul class= "font dropdown-menu">
					<li class = "font"><a href = "ModificationForm">modification</a></li>
					</ul>
				</li>
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
			<table class = "table table-striped " id="row_table">
				<thead>
					<tr >
						<th class="thead_th">No.</th>
						<th class="thead_th">Title</th>
						<th class="thead_th">writer</th>
						<th class="thead_th">date</th>
						<th class="thead_th">hits</th>
					</tr>
				</thead>
<script>
	/* setNum(bno, num) : 댓글의 개수가 1개 이상이면, 타이틀에 " [__]"를 추가함. */
 	function setNum(bno, num) {
		$(".bno").each(function(idx, item) {
			if($(item).text()!=bno) {
				return;
			}
			$(item).next().find("span").text(" ["+num+"]");
			console.log(idx + ") " + bno + " : " + num + "ge.");
		});
	} 
</script>
				<tbody id="table">
					<c:forEach var="dto" items="${dtoList}">
						<tr class="tr">
							<td class="bno">${dto.bno }</td>
							<td>${dto.title}<span id="dto_title"></span></td>
							<td>${dto.writer } 
							 <c:choose>
								<c:when test="${dto.profil ne null }">
									<img id="writer_img" src="${pageContext.request.contextPath}/resources/upload/${dto.profil }"> 
								</c:when>
							</c:choose>
							</td>									
							<td>${dto.writedate.substring(0,16) }</td>
							<td>${dto.hitcount }</td>
						</tr>
						<script>
							$.ajax({
								url: '${pageContext.request.contextPath}/ajax/ReplyCount',
								type: 'post',
								data: JSON.stringify({"bno":${dto.bno}}),//넘어가는 데이터타입을 의미함
								contentType: "application/json; charset=utf-8",//넘어가는 데이터타입을 의미함
								datatype: "json", //응답받는 데이터타입을 의미함.
								success: function(obj) {
									//console.log(obj);
									//alert(obj);
									let num = obj.num;
									setNum(${dto.bno}, num);
								},
								error: function(r,s,e) {
									alert("[에러] code:" + r.status
											+ "message:" + r.responseText
											+ "error:" + e);
								}
							});
						</script>
					</c:forEach>	
				</tbody>
			</table>
			<div id= "pagination">
				<%
					int currentPage = 1;
					try{
						currentPage = Integer.parseInt(request.getParameter("page"));
					} catch(NumberFormatException E) {	}
					 
					int base = currentPage/5*5 - (currentPage%5==0? 5: 0);
					int start = base+1;
					int end = base+5;
					int lastPage = (Integer)request.getAttribute("lastPage");
					
					for(int i=start; i<=end; i++ ) {
						if(i>lastPage) break;
						if(start==i && start-5>0){
				%>		
						<span><a href="BoardList?page=<%=start-5%>">&lt;</a></span>	
					<%} %>
						
						<%if(currentPage==i) { %>
							<span><%=i %></span>
						<%} else {%>
								<span><a href="BoardList?page=<%=i%>"><%=i %></a></span>
							<%} %>
						<%if(end==i && end+1<=lastPage) { %>
								<span><a href="BoardList?page=<%=end+1%>">&gt;</a></span>	
					<%} %>
						
				<%
					}
				%>
			</div>		
			<a href = "WriteForm" class = "btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>

</body>
</html>