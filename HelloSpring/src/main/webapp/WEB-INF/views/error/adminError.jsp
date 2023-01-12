<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    					<!-- isErrorPage 추가! -->
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="error"/>
</jsp:include>
	<h2 style="color:red"><%=exception.getMessage() %></h2>
	<script>
		setTimeout(()=>{location.replace("${path}")},3000})
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>