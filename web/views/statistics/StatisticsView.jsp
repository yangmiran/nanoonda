<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,statistics.model.vo.Statistics"%>

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
<html>
<head>
<title>NANOONDA STATISTICS</title>
<%@ include file="../../views/common/meta.jsp"%>

<style>

#draw1{
	style=" float: center; 
	width: 400px; 
	height: 400px;"
}

#draw2{
	style=" float: center; 
	width: 400px; 
	height: 400px;"
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
</script>
</head>
<body>
<%-- <%@ include file ="../common/adminheader.jsp" %>  --%>
    <div id="draw1" ></div>
    <div id="draw2" ></div>
</body>
</html>


