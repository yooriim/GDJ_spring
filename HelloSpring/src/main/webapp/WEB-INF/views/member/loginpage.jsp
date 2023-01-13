<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="전체조회"/>
</jsp:include>

<!-- form action login or 절대경로로 ${path }/login 해줘야함. security가 처리해주는 경로가 이거라서ㅠ ㅋㅋ  -->
<form action="${path }/login" method="post">
	<div class="modal-body">
		<input type="text" name="userId" class="form-control" placeholder="아이디 입력" required><br>
		<input type="password" name="password" class="form-control" placeholder="비밀번호 입력" required>
		
														
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn btn-outline-success">로그인</button>
		<button type="button" class="btn btn-outline-success"
		data-dismiss="modal">취소</button>
	</div>
</form>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>