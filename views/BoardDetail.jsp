<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var = "loginId" value="${loginId }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- 추가할부분 -->
	<meta http-equiv="X-UA-Compatible" content="ie=edge"> <!-- 추가할부분 -->
<meta name = "viewport" content = "width= device-width" initial-scale= "1.0">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/reply.css" rel ="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700" rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/BoardDetail.css" rel ="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

<script>
$(function(){
	
	$("#replySubmit").click(function(){
		let bno = '${bno}';
		let replyer ='${loginId}';
		let content =$("#replyContent").val();
		if(content=="") { 
			alert("댓글을 입력해주세요");
			return;
		}
		let _this = $(this);
		 $.ajax({
			type: 'post',
			data: JSON.stringify({"bno":bno,"replyer":replyer,"content":content}),
			contentType: "application/json; charset=utf-8",
			datatype: "json", 
			url: '${pageContext.request.contextPath}/ajax/InsertReply',
			success: function(obj) {
				//console.log(obj);
				//alert(obj);
				 location.reload();
	 		},
			error: function(r,s,e) {
				 alert("[에러] code:" + r.status
						+ "message:" + r.responseText
						+ "error:" + e); 
			}
		});
	}); 
	$(".deleteReply").click(function(){
		if(confirm("삭제하시겠습니까? ")==false) {
			return;
		}
		let rno = $(this).next().val();
		let _this = $(this);
		$.ajax({
			type: 'post',
			data: JSON.stringify({"rno" : rno, "bno":${bno}}),//넘어가는 데이터타입을 의미함
			contentType: "application/json; charset=utf-8",//넘어가는 데이터타입을 의미함
			datatype: "json", //응답받는 데이터타입을 의미함.
			url: '${pageContext.request.contextPath}/ajax/DeleteReply',
			success: function(obj) {
				if(obj.result=='OKAY') {//자바스크립트라서.equals가아닌 == 사용가능
					_this.parents(".view").remove();
					$("#reply_cnt").text(obj.num + " 개");
					alert("삭제되었습니다.");
				}
			},
			error: function(r,s,e) {
				alert("[에러] code:" + r.status
						+ "message:" + r.responseText
						+ "error:" + e);
			}
		});
	});
	
	$(".updateReply").click(function(){
		//consider: 일단은 모두를 닫아줘야돼.(안 보이게)
		$(this).parents(".viewTop").next().css('display','block');  // 해당하는 것만 보이게.
		$(this).parents(".viewTop").find(".content").css('display', 'none');
	});
	
	$(".no").click(function(){
		 $(this).parent(".select").css("display","none"); 
		 $(this).parent(".select").prev().find(".more").next().css("display","none");
		 $(this).parent(".select").prev().find(".fl").children().show();		
	});
	
	$(".yes").click(function(){
		let rno = $(this).next().val();
		let content = $(this).closest(".border").find(".textarea").val();
		let bno = ${bno};
		let _this=$(this);
		
		$.ajax({
			type: 'post',
			data: JSON.stringify({"rno":rno, "bno":bno,"content":content}),//넘어가는 데이터타입을 의미함
			contentType: "application/json; charset=utf-8",//넘어가는 데이터타입을 의미함
			datatype: "json", //응답받는 데이터타입을 의미함.
			url: '${pageContext.request.contextPath}/ajax/UpdateReply',
			success: function(obj) {
				//console.log(obj);
				//alert(obj);
				_this.closest("div").find(".textarea").val(content);
				location.reload();
				alert("수정이 완료되었습니다.");
			},
			error: function(r,s,e) {
				alert("[에러] code:" + r.status
						+ "message:" + r.responseText
						+ "error:" + e);
			}
		});  
	});
	
	$(".more").click(function(){
		let loginId = '${loginId}';
		let writerId = $(this).parents(".viewTop").find(".writer").text();
		if(loginId != writerId) {
			return;
		}
		
		if($(this).next().css('display')=='none') {
			//$(this).next().css('display','block');
			$(this).parent().find('.mwl').css('display','block');
		} else {  // $(this).next().css('display')=='block'
			$(this).next().css('display','none');
		}
	});
	$(".updateReply").click(function(){
		$(this).find(".select").css("display","none");
	});
	
	$("dropdown-toggle").click(function(){
		alert("dropdown-toggle");
	});
	$("#logout").click(function(){
		if(confirm("로그아웃 하시겠습니까?")==false) {
			return;
		}location.href="Logout";
	});
	
	$("#deleteBoard").click(function(){
		let loginId = '${loginId}';
		let writer = '${detailDto.writer}';
		
		if(loginId!=writer){
			alert("접근권한이 없습니다.");
		}else if(loginId==writer&&confirm("삭제하시겠습니까?")==true) {
			location.href="Delete?bno="+${bno};
		}return;
	});	
	$("#revise").click(function(){
		let loginId = '${loginId}';
		let writer = '${detailDto.writer}';
		if(loginId==writer){
			location.href="UpdateForm?bno=${detailDto.bno }";
		}else {alert("접근권한이 없습니다.")}
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
		<a class = "font navbar-brand" href= "http://wlmgl0729.cafe24.com:9091/catchTable2024" target="_blank" id="catch_color">CatchTable 이동</a>
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
					<!-- <li class = "font"><a href = "Join">sign in</a></li> -->
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
	<div class = "container" >
		<div class = "row">
				<table class = "font table table-striped " style = "text-align: center; border:1px solid grey;">
					<thead>
						<tr>
							<th  colspan ="3" style ="background-color: #9db895; color: #eee; text-align: center;">view</th>
					</thead>
					<tbody>
							<div id="profil">
							<c:choose>
								<c:when test="${profil eq null }">
									<img id="img" src="https://static.nid.naver.com/images/web/user/default.png?type=s160">
								</c:when>
								<c:otherwise>
									<img id="img" src="${pageContext.request.contextPath}/resources/upload/${profil }">
								</c:otherwise>
							</c:choose>
							${detailDto.writer }</div>
						<tr>
							<td>title</td>
							<td colspan = "2">${detailDto.title}</td>
						</tr>
						<tr>
							<td>date</td>
							<td colspan = "2">${detailDto.writedate.substring(0,16)}</td>
						</tr>
						<tr>
							<td>content</td>
							<td colspan = "2" style = "min-height: 200px; text-align: center;">${detailDto.content}</td>
						</tr>
						<tr>
							<td>comment</td>
							<td id="reply_cnt" colspan = "2">${replyCnt} 개</td>
						</tr>
						</div>
					</tbody>
				</table>
					<a href= "BoardList" class = "btn btn-primary">list</a>
					<input type="button" id="revise" class= "btn btn-primary"value="revise">
					<input type="button" id="deleteBoard" class= "btn btn-primary" value="delete">
		</div>
	</div>
<!-- ===================================== 댓글시작--> 	
	<div id="reply" class="border">
		<div id="replybox" class="border ">
			<textarea id="replyContent" name="replyContent" class="textarea fl" style="resize : none;" placeholder="댓글을 작성해주세요" required ></textarea>
		</div>
		<div class="fr"><input type="submit" class="reply-btn" id="replySubmit" value="등록하기"/></div>
		<div style="clear:both;"></div>
		<c:forEach var="replyList" items="${replyList }"> 
			<div class="view rno" >
			<div class="viewTop ">
				<div class=" fl">
					<div class="writer">${replyList.replyer }</div>
					<div class="date">${replyList.writedate }</div>
					<div class="content">${replyList.content }</div>
				</div>
				<div class="border fr">
					<div class="rno"> 
						<c:if test="${loginId eq replyList.replyer}">
							<a  class="more" ><img src="https://app.catchtable.co.kr/public/img/icons/arrow-down.svg"></a>
						</c:if>
						<div class="mwl" id="mwl">
							<div class="check">
								<input type="button" class="reply-btn updateReply" value="수정"/> <br/>
								<input type="button" class="reply-btn deleteReply" value="삭제"/>
								<input type="hidden" name="rno" class="rno" value="${replyList.rno}"/> 
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="border select" id="border_button">
				<textarea style="resize : none;" class="textarea">${replyList.content }</textarea>
				<button type= "button" class="no modifyReply">취소</button>
				<button type= "button" class="yes modifyReply">완료</button>
				<input type="hidden" value="${replyList.rno}" />
			</div>
		</div>
		</c:forEach>  
		
	</div>
</body>
</html>