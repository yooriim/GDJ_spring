<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="전체조회"/>
</jsp:include>

 <br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
            
            <c:if test="${not empty memo }">
	      		<c:forEach var="m" items="${memo }">
					<tr>
						<td><c:out value="${m.memoNo }"/></td>
						<td><c:out value="${m.memo}"/></td>
						<td><c:out value="${m.memoDate}"/></td>
						<td><button class="btn btn-outline-danger">삭제</button></td>
					</tr>
	      		</c:forEach>	
            </c:if>
       
        </table>
		<button class="btn btn-outline-primary my-2 my-sm-0" 
				onclick="location.assign('${path}/memo/writeMemo.do')">
			메모 작성
		</button>
		<div id="pageBar">
			${pageBar }
		</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>