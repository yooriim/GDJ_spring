<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<c:set var="path" value="${ pageContext.request.contextPath}"/>   

<!-- chart.js 라이브러리 CDN 로드 -->    
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.0/chart.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>

<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>


<body>
	<div>
		<canvas id="gender-doughnut" width="100" height="100"></canvas>
	</div>
	<div>
	<canvas id="chartArea" width="100" height="100"></canvas>
	</div>

	<button id="btn">일일 매출</button>
	<button id="btn1">회원 성별 구성</button>

</body>
<!-- <style>
	body{
		display:inline-block;
	}

</style> -->

<script>	
	$("#btn").click(e=>{
 		$.ajax({
			url: "${path}/chart/chart.do",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			success:function(data){
 				var saleList=[];
				var dateList=[];
				
				
				$.each(data,function(){
					saleList.push(this["sale"])
					dateList.push(this["date"]) 
					
				})
				
 				console.log(saleList);
				console.log(dateList); 
				
				 new Chart(document.getElementById("chartArea"), {
					  plugins: [ChartDataLabels],
					  type: 'line',
					  data: {
					    labels: dateList,
					    datasets: [{ 
					        label :'일일 매출 현황',
					        data : saleList,
					        
					      }
					    ]
					  }
					}); 	
				
			}
		}) 
	});
	
	$("#btn1").click(e=>{	
		$.ajax({
			url: "${path}/chart/genderRatio.do",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			success:function(data){
 				var male=[];
				var female=[];	
				
				
				$.each(data,function(){
					male.push(this["maleCount"])
					female.push(this["femCount"]) 
					
				})
				 	new Chart(document.getElementById("gender-doughnut"), {
					  plugins: [ChartDataLabels],
					  type: 'doughnut',
					  data: {
					    labels: ['남','여'],
					    datasets: [{ 
					        data: [male,female], 
					        backgroundColor: [
					            '#9DCEFF',
					            '#FFACB7'
					          ],
					        borderWidth: 0,
					        scaleBeginAtZero: true,
					        fill: true
					      }
					    ]
					  },
					  options: {
					    title: {
					      display: true,
					      responsive:false,	//차트 크기 조정용
					      text: 'member 성비',
					      datalabels: { // datalables 플러그인 세팅
					          formatter: function (value, context) {
					            var idx = context.dataIndex; // 각 데이터 인덱스
				
					            // 출력 텍스트
					            return context.chart.data.labels[idx] + value;
					          },
					          align: 'top', // 도넛 차트에서 툴팁이 잘리는 경우 사용
					        },
					      
					    }
					  }
					})
	 	
	 	
			}
		})
	});
	
</script>


</html>