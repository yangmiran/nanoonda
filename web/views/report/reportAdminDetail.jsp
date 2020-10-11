<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="report.model.vo.Report"%>
  
<%
	Report report = (Report)request.getAttribute("report");
%>    


<!-- 신고게시판에서 제목누르면 나오는 상세보기  -->
<!DOCTYPE html>

<html>
<head>
<title>NANOONDA REPORT</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>
<%@ include file ="../common/adminheader.jsp" %> 
<hr>
<h2 align ="center"> <%= report.getReportNo() %> 번 상세보기</h2>
<br>


<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>제목</th><td><%= report.getReportTiltle() %></td></tr>
<tr><th>신고자</th><td><%= report.getsenderNname() %></td></tr>
<tr><th>처리상태</th>
<td>
<%if(Integer.parseInt(report.getReportStatus())==0){ %>
미처리
<%}else{ %>
처리
<%} %>
</td></tr>
<tr><th>신고받은자</th><td><%= report.getreceiverNname() %></td></tr>
<tr><th>내용</th><td><%= report.getReportContent() %></td></tr>
<tr><th>신고날짜</th><td><%= report.getReportRegDate() %></td></tr>
<tr><th>처리완료날짜</th>
<td>
<%if(report.getReportComDate()==null){ %>
&nbsp;
<%}else{ %>
<%= report.getReportComDate() %>
<%} %>
</td></tr>
<!-- 상세보기에서 목록으로 돌아가기 -->
<tr><th colspan="2"><button onclick="javascript:history.go(-1); return false;">목록</button>
 <!-- 신고처리 완료,  -->

<br> 
</th></tr>
</table>
  
          
</body>
</html>
