<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="report.model.vo.Report"%>
  
<%
	Report report = (Report)request.getAttribute("report");
%>    


<!-- 관리자가 신고게시판에서 제목누르면 나오는 상세보기  -->
<!DOCTYPE html>
<html>
<head>
<title>NANOONDA REPORT</title>
<meta charset="UTF-8">
<%@ include file="../../views/common/meta.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<%@ include file ="../common/adminheader.jsp" %> 
<%@ include file="../pro/proModalView.jsp" %>
	
<body>
<div id="ad_listview"> <!-- 전체 묶은 div : start -->	

<h1 class="ad_pagetit"><span>Report Management</span>신고내역 상세보기</h1>	
	

<table align="center" class="w3-table-all w3-small serchlist" >

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
<tr><th colspan="2">

<div class="ad_detailbtn">
<input type="button" id="returnlist" value="목록" onclick="location.href='/nnd/rlist.ad?page=1'; return false;" style="cursor:pointer; ">

<input type="button" id="reportyellow" value="경고주기" onclick="javascript:location.href='/nnd/rstcheck.ad?rno=<%=report.getReportNo() %>&writer=<%=report.getreceiverNname() %>'" style="cursor:pointer; ">

<input type="button" id="addpro" value="금지어로등록" onclick="document.getElementById('id01').style.display='block'" style="cursor:pointer; ">

<input type="button" id="reportdelete" value="삭제" onclick="javascript:location.href='/nnd/radonedelete.ad?rno=<%=report.getReportNo()%>'">

<input type="button" id="reportcom" value="처리완료" onclick="javascript:location.href='/nnd/rsimplech?rno=<%=report.getReportNo() %>'" style="cursor:pointer; ">

<%-- <input type="button" id="reportcom" value="해당 게시글로가기" onclick="javascript:location.href='/nnd/rmoveto.ad?dno=<%=report.getDiaryNo() %>&pno=<%=report.getPostNo()%>&writer=<%=report.getreceiverNname() %>'" style="cursor:pointer; "> --%>

</div>
</th></tr>
</table>
  
          
</body>
</html>
