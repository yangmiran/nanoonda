<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="report.model.vo.Report"%>
  
<%
	Report report = (Report)request.getAttribute("report");

%>    


<!-- 마이페이지 신고게시판에서 제목누르면 나오는 상세보기    -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA MYPAGE</title>
<%@ include file="../../views/common/meta.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>

 <%@ include file="../common/header.jsp" %> 


<h1 class="subtit"><span>Report</span> 신고내역 상세보기</h1>
<div id="wrap">
	<div class="my_page">
	<div class="my_table">


<table >
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
</table>
<!-- 상세보기에서 목록으로 돌아가기 -->
<div class="btn_in">
	<button onclick="javascript:history.go(-1); return false;">목록</button>

 <!-- 신고처리 완료,  -->
 <%if(Integer.parseInt(report.getReportStatus())==1){//신고처리 상태가 처리완료된 상태이면 수정 불가 %>
 
 <%}else{ %>
 <input type="button" id="reportupdate" value="수정" onclick="javascript:location.href='/nnd/reportup?rno=<%=report.getReportNo()%>'">
 <%} %>
<input type="button" id="reportdelete" value="삭제" onclick="javascript:location.href='/nnd/ronedelete?rno=<%=report.getReportNo()%>'">
</div>
 


</div>
   </div>
   </div>  
  <%@ include file="../common/footer.jsp"%>         
</body>
</html>
