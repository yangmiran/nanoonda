<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//가입경로
	String en1 = (String) request.getAttribute("en1");//지인추천
	String en2 = (String) request.getAttribute("en2");//인터넷 검색
	String en3 = (String) request.getAttribute("en3");//각종 매체광고
	String en4 = (String) request.getAttribute("en4");//기존 사용
	String en5 = (String) request.getAttribute("en5");//기타
	
	//탈퇴사유
	String del1 = (String) request.getAttribute("del1");//서비스 불만
	String del2 = (String) request.getAttribute("del2");//컨턴츠 부족
	String del3 = (String) request.getAttribute("del3");//이용 안함
	String del4 = (String) request.getAttribute("del4");//기타
%>    

<!DOCTYPE html>
<html lang="ko">
<head>
<title>NANOONDA</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../../views/common/meta.jsp"%>
<style>





/* body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
} */

/* Style the content */
.content {
  background-color: #ddd;
  padding: 10px;
  height: 400px; /* Should be removed. Only for demonstration */
}

/* Style the footer- footer 업데이트 후 삭제해야함*/
.footer {
  background-color: #f1f1f1;
  padding: 10px;
}

</style>
<!-- 가입 경로 원형 그래프 -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart1);
    google.setOnLoadCallback(drawChart2);
    
    function drawChart1() 
    {
        var data = google.visualization.arrayToDataTable(
            [["가입경로별","비율"],["지인추천",<%=en1%>],["인터넷 검색",<%=en2%>],["각종매체광고",<%=en3%>],["기존사용",<%=en4%>],["기타",<%=en5%>]]
        );
        var options = {
            title: "가입 경로 분석"
        };
        var chart = new google.visualization.PieChart(document.getElementById("draw1"));
        chart.draw(data, options);
    }


    <!-- 탈퇴이유 원형 그래프 -->


    function drawChart2() 
    {
        var data = google.visualization.arrayToDataTable(
            [["가입경로별","비율"],["서비스불만",<%=del1%>],["컨텐츠부족",<%=del2%>],["이용안함",<%=del3%>],["기타",<%=del4%>]]
        );
        var options = {
            title: "탈퇴사유 분석"
        };
        var chart = new google.visualization.PieChart(document.getElementById("draw2"));
        chart.draw(data, options);
    }

$(function(){
	
	//미처리 상태인 신고글 보여주기
	$.ajax({
		url : "/nnd/rtylist.ad", //값보낼 곳
		type : "get",
		dataType : "json", //json ~ 배열과 객체 리터럴 표기법의 조합
		success : function(data) {
			console.log("success: " + data);//확인용

			var jsonStr = JSON.stringify(data);//객체 또는 배열을 인자로 받아 JSON 문자열로 직렬화
			var json = JSON.parse(jsonStr);//string 객체를 json으로 바꿈
			var values = "<tr><th>번호</th><th>상태</th><th>제목</th><th>신고자</th><th>신고받은자</th><th>신고일</th></tr>";//초기화
			
			for ( var i in json.list) {//변수 선언만하면 0부터 1씩 증가함
				values += "<tr><td>"     
						+ (window.parseInt(i)+1) //번호
						+"</td><td> 미처리"//상태
						+ "</td><td><a href='/nnd/rdetail.ad?rno=" //제목 클릭시 상세보기로
						+ json.list[i].no
						+ "'>"
						+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ") //신고제목
						+ "</a></td><td>"
						+ decodeURIComponent(json.list[i].sender).replace(/\+/gi, " ") //신고자
						+"</td><td>"
						+ decodeURIComponent(json.list[i].receiver).replace(/\+/gi, " ") //신고받은자
						+"</td><td>"
						+ json.list[i].date //신고일
						+ "</td></tr>"
							
			} //for in
			
			$("#newreport").html($("newreport").html() + values);
		},
		error : function(jqXHR, textstatus, errorthrown) {//에러 출력
			console.log("error: " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	
	 
		//답글 쓰지 않은 문의사항 최신순으로 5개 
		$.ajax({
			url : "/nnd/qalarm", //값보낼 곳
			type : "get",
			dataType : "json", //json ~ 배열과 객체 리터럴 표기법의 조합
			success : function(data) {
				console.log("success: " + data);//확인용

				var jsonStr = JSON.stringify(data);//객체 또는 배열을 인자로 받아 JSON 문자열로 직렬화
				var json = JSON.parse(jsonStr);//string 객체를 json으로 바꿈
				var values = "<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th></tr>";//초기화
				
				for ( var i in json.list) {//변수 선언만하면 0부터 1씩 증가함
					values += "<tr><td>"     
							+ (window.parseInt(i)+1) //번호
							+ "</td><td><a href='/nnd/aqdetail?qna_no=" //제목 클릭시 상세보기로 (**지은이꺼 확인 후 적용!)
							+ json.list[i].no
							+ "'>"
							+ decodeURIComponent(json.list[i].title).replace(/\+/gi, " ") //문의사항제목
							+ "</a></td><td>"
							+ decodeURIComponent(json.list[i].writer).replace(/\+/gi, " ") //문의사항 작성자
							+"</td><td>"
							+ json.list[i].date  //문의사항 등록일
							+"</td></tr>"
							
									
				} //for in
				
				$("#newqna").html($("newqna").html() + values);
			},
			error : function(jqXHR, textstatus, errorthrown) {//에러 출력
				console.log("error: " + jqXHR + ", " + textstatus + ", "
						+ errorthrown);
			}
		}); 
		 
	
});	
</script>
</head>
<body>
<!-- 관리자 헤더 불러옴 -->
<%@ include file="/views/common/adminheader.jsp" %>
<%-- <%@ include file="/views/statistics/StatisticsView.jsp" %>  --%>
 <section id="adminmaingrap" class="clear">
	 <div id="draw1" ></div>
	 <div id="draw2" ></div>
 </section>
 
<div id="adminmain" class="clear">
	<!-- <h1>관리자님에 새 글이 도착했습니다</h1> -->
	<section>
	<!-- 미처리 신고게시글 출력-->
	<div class="ad_list">
		<h4>미처리 신고게시글 <span class="admore"><a href ="/nnd/rlist.ad?page=1">더보기</a></span></h4>
		<table id="newreport" cellspacing="0" float="center">
		</table>
	</div>
	
	<!--문의사항 최신순으로 5개 출력  -->
	</section>
	<div class="ad_list">
		<h4>미처리 문의사항 <span class="admore"><a href ="/nnd/aqlist">더보기</a></span></h4>
		<table id="newqna" cellspacing="0" float="center">
		</table>
	</div>
</div>

</body>
</html>