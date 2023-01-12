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
div#board-container input{margin-bottom:15px;}
</style>

<div id="board-container">
	<form name="boardFrm" action="${path }/board/insertBoardEnd.do" method="post" enctype="multipart/form-data">
	<!-- 
		첨부파일 보낼떄 method="post" enctype="" 필수 
		이클립스에서는 cos.jar 썼지만 스프링에서는 다른애 써욤. pom.xml에 라이브러리 등록
	 -->
	
	
	    <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required>
	    <input type="text" class="form-control"  name="boardWriter" value="${loginMember.userId}" readonly required>
		<div class="input-group mb-3" style="padding:0px;">
	    	<div class="input-group-prepend" style="padding:0px;">
	            <span class="input-group-text">첨부파일1</span>
	        </div>
	        <div class="custom-file">
	            <input type="file" class="custom-file-input" name="upFile" id="upFile1">
	            <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
	        </div>
	    </div>
		<div class="input-group mb-3" style="padding:0px;">
	    	<div class="input-group-prepend" style="padding:0px;">
	            <span class="input-group-text">첨부파일2</span>
	        </div>
	        <div class="custom-file">
	            <input type="file" class="custom-file-input" name="upFile" id="upFile2">
	            <label class="custom-file-label" for="upFile2">파일을 선택하세요</label>
	        </div>
	    </div>
	    <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
	    <br />
	    <input type="submit" class="btn btn-outline-success" value="저장" >
	</form>
</div>
<script>
	$(()=>{
		$("[name=upFile]").change(e=>{
			console.dir(e.target);
			const fileName=e.target.files[0].name; /* input=file이면 아래에 files라는 속성이있음~~ */
			$(e.target).next(".custom-file-label").text(fileName);
		})
	})
</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>