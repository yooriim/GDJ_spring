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

	<button type="button" class="btn btn-outline-primary my-2 my-sm-0"
	onclick="fn_memberInfoAjax();">회원 조회</button>
	
	<div id="membercontainer"></div>
	
	<button class="btn btn-outline-primary my-2 my-sm-0"
	onclick="jsonTest();">회원가입</button>
	
	<img src="${path }/resources/images/logo-spring.png" id="center-image" alt="스프링로고">
	
	<h3> == jpa테스트하기 == </h3>
	<h3><a href="${path }/jpa/insert">jpa회원 저장하기</a></h3>
		<form action="${path}/jpa/insert ">
			<input type="text" name="userId">
			<input type="submit" value="가입">		
		</form>
	<h3><a href="${path }/jpa/members">jpa회원 전체 조회하기</a></h3>
	<h3><a href="${path }/jpa/member?id=1">jpa회원 조회하기</a></h3>
	<h3><a href="${path }/jpa/member/search?height=180.5">jpa회원 조건 조회하기</a></h3>
	<h3><a href="${path }/jpa/update?no=1&age=20&height=190.5&intro=새해복많이받으세요">jpa회원 수정하기</a></h3>
	<h3><a href="${path }/jpa/delete?no=1">jpa회원 삭제하기</a></h3>
	<h3><a href="${path }/jpa/insertMember">jpa 다대일 클래스 저장하기</a></h3>
	<h3><a href="${path }/jpa/major?no=1">jpa 학과 조회하기</a></h3>
	<h2>== 다대다 관계 ==</h2>
	<h3><a href="${path }/jpa/insertStudentClub">jpa 다대다 저장하기</a></h3>
	<h3><a href="${path }/jpa/selectStudent?no=9">jpa 다대다 조회하기</a></h3>
	<h3><a href="${path }/jpa/selectStudent?no=9">jpa 다대다 조회하기</a></h3>
	
	
</section>
<script>

	function jsonTest(){
		//비동기식통신을 할 때 js기본제공하는 함수 이용하기
		//fecth()함수를 제공함, 동기식 통신을 위해서 promise객체로 반환을 함! fetch 그냥 쓰며ㅓㄴ 댐 !
		//fecth(url주소, 옵션(전송))		  전송에 대한 옵션이 없으면 기본 get방식으로 보낸다
		//.then(response=>return response.json()
		//.then(data=>{로직})
		
		/* 'fetch('서버주소')' 는 웹 브라우저에게 '이 서버주소로 요청해줘' 라는 의미이고, 
			뒤에 .then이 붙으면 '요청 끝나고나서 이 할일을 해줘!' 라는 것이다. */
		fetch("${path}/member/ajax/insert",{
			method:"post", //전송방법
			headers:{ //요청헤더 설정
				"Content-Type":"application/json" /* 이렇게 안하면 415error! json방식으로 받는다고 햇는데 그렇게 안보내서 난 에러 */
			},
			body:JSON.stringify({userId:"test22",password:"1234",userName:"test22"})  //전송 데이터
			/* body에서 보낼떄 JSON.stringify로 보내줘야 컨트롤러에서 consumes=MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE) 얘네 쓸수잇슴 */
		})
		.then(response=>{console.log(response); return response.json(); }) //여기서 리턴된 값이 다음 then 메소드의 매개변수로 들어감
		.then(data=>	//success callback
		{console.log(data)})
		
		
	}

	const fn_memberInfoAjax=()=>{
			$.get("${path}/member/memberList.do"
					,data=>{
						const table =$("<table>");
						const header= $("<tr>");
						header.append("<th>아이디</th>")
							.append("<th>이름</th>")
							.append("<th>나이</th>")
							.append("<th>성별</th>")
							.append("<th>전화번호</th>")
							.append("<th>이메일</th>")
							.append("<th>주소</th>")
							.append("<th>취미</th>")
							.append("<th>가입일</th>");
						table.append(header);
						data.forEach(e=>{
							let tr=$("<tr>");
							let userId=$("<td>").text(e.userId);
							let name=$("<td>").text(e.userName);
							let age=$("<td>").text(e.age);
							let gender=$("<td>").text(e.gender);
							let phone=$("<td>").text(e.phone);
							let email=$("<td>").text(e.email);
							let address=$("<td>").text(e.address);
							let hobby=$("<td>").text(e.hobby.toString());
							let enrollDate=$("<td>").text(new Date(e.enrollDate));
							tr.append(userId).append(name).append(age).append(gender)
							.append(phone).append(email).append(address)
							.append(hobby).append(enrollDate);
							table.append(tr);
						});
						$("#membercontainer").html(table);
						
						
					});					
	}
</script>
<!-- 
	푸터파일 불러오기
	
 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

