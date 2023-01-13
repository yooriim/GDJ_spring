<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="전체조회"/>
</jsp:include>

<style>
	div#board-container{width:400px; margin:0 auto; text-align:center;}
	div#board-container input,div#board-container button{margin-bottom:15px;}
	div#board-container label.custom-file-label{text-align:left;}
</style>

<div id="board-container">
	<input type="text" class="form-control" name="boardTitle" id="boardTitle" value="${content.boardTitle }" required>
	<input type="text" class="form-control" name="boardWriter" value="${content.boardWriter.userId }" readonly required>
	
	<c:if test="${not empty content.files }">
		<c:forEach var="file" items="${content.files }">
			<button type="button" class="btn btn-outline-success btn-block"
			        onclick="fn_filedownload('${file.originalFilename}','${file.renamedFilename }');">${file.originalFilename }
			</button>
		</c:forEach>
	</c:if>
	
	<textarea class="form-control" name="boardContent" placeholder="내용" required>${content.boardContent }</textarea>
</div>
<script>

	const fn_filedownload=(oriname,rename)=>{
		location.assign("${path}/board/filedown.do?ori="+oriname+"&re="+rename);
	}
	
</script>

    
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>