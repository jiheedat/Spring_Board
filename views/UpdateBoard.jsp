<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<c:set var = "bno" value="${bno}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.0.min.js"></script>
<script>
	alert("수정 완료되었습니다.");
	location.href="BoardDetail?bno=${bno}";
</script>
<title></title>
</head>
<body>
</body>
</html>