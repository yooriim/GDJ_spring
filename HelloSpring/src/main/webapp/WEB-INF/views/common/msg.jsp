<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 메세지</title>
</head>
<body>
	
	<script>
		alert("${msg}");
		location.replace("${pageContext.request.contextPath}${loc}");
	</script>
	
</body>
</html>