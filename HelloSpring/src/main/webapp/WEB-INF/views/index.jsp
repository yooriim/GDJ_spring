<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="path" value="${ pageContext.request.contextPath}"/>
<!-- 
	헤더파일 불러오기
	title 값을 전달해서 출력해야 함 -> Mainpage가 출력
 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>
    
<section id="content">
	<h2>Hello Spring</h2>
	<img src="${path }/resources/images/logo-spring.png" id="center-image" alt="스프링로고">
</section>

<!-- 
	푸터파일 불러오기
	
 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

