<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="전체조회"/>
</jsp:include>

<section id="board-container" class="container">
	<div style="display:flex;justify-content:space-between;align-items:center">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
		<button class="btn btn-outline-primary my-2 my-sm-0" 
				onclick="location.assign('${path}/board/insertBoard.do')">
			작성하기
		</button>
	</div>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty board }">
	            <c:forEach var="b" items="${board }">
		            <tr>
		            	<td><c:out value="${b.boardNo }"/></a></td>
		            	<td><a href="${path }/board/viewBoard.do?boardNo=${b.boardNo}"><c:out value="${b.boardTitle }"/></a></td>
		            	<td><c:out value="${b.boardWriter.userId }"/></td>
		            	<td><c:out value="${b.boardDate }"/></td>
		            	<td>

		            		<c:if test="${empty b.files }">없음</c:if>
            		        <c:if test="${not empty b.files }">있음</c:if>
		            		
		            	</td>
		            	<td><c:out value="${b.boardReadCount }"/></td>
		            </tr>
	            </c:forEach>
            </c:if>           
            
        </table> 
        
		
        <div id="pageBar">
        	${pageBar }
        </div>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>