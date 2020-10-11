<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
</head>
<body>
<!--  유저가 보는 공지사항 목록 -->
<%@ include file="../../views/common/meta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1">

<%@ include file="../common/header.jsp"%>

<h1 class="subtit"><span>Notice</span>공지사항</h1>
	<div id="wrap">
	<div class="my_page">
	<div class="my_table">
	<table style="text-align: center;">
	<colgroup>
			<col width="10%" />
			<col width="50%" />
			<col width="20%" />
			<col width="*" />
	</colgroup>
	<tr>
		<th style="text-indent:0">글번호</th>
		<th style="text-indent:0">글제목</th>
		<th style="text-indent:0">작성자</th>
		<th style="text-indent:0">날짜</th>
	</tr>
		
		<%
			for (Notice n : list) {
		%>
		<tr>
			<td><%=n.getNoticeNo()%></td>
			<td><a href="/nnd/ndetail?notice_no=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle()%></a></td>
			<td><%=n.getNname()%></td>
			<td><%= n.getNoticeDate() %></td>
   </tr>
   <% } %>
</table>
<br>
<!-- 페이징 처리 -->

	<div id="page" style="text-align: center; width: 90%; float: right">
		<% if (currentPage <= 1) {//맨 첫 페이지로 이동%>
			<span class="ti-angle-double-left"></span> <!-- 1페이지에 있으면 이동 X -->
		<%} else {%>
			<a href="/nnd/nlist?page=1" class="ti-angle-double-left"></a> <!-- 1페이지아니면 1페이지로 이동 O -->
		<%}%>

		<!-- 이전 그룹으로 이동 처리 -->
		<%if ((currentPage - 10) < startPage && (currentPage - 10) > 1) {%>
			<a href="/nnd/nlist?page=<%=startPage - 10%>" class="ti-angle-left"></a>
		<%} else {%>
			<span class="ti-angle-left"></span>
		<%}%>

		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for (int p = startPage; p <= endPage; p++) {
			if (p == currentPage) {%>
				<span class="number"><font><b><%=p%></b></font></span>
			<%} else {%>
				<span class="number"><a href="/nnd/nlist?page=<%=p%>"><%=p%></a></span>
			<%} //else 닫기%>

		<%} //for 닫기%>

		<!-- 다음 그룹으로 이동 처리 -->
		<%if ((currentPage + 10) > endPage && (currentPage + 10) < maxPage) {%>
			<a href="/nnd/nlist?page=<%=endPage + 10%>" class="ti-angle-right"></a>
		<%} else {%>
			<span class="ti-angle-right"></span>
		<%}%>
		
		<% if (currentPage >= maxPage) {//맨 첫 페이지로 이동%>
			<span class="ti-angle-double-right"></span> <!-- 맨끝 페이지에 있으면 이동 X -->
		<%}else{ %>
			<a href="/nnd/nlist?page=<%=maxPage %>" class="ti-angle-double-right"></a> <!-- 맨끝 페이지아니면 맨끝 페이지로이동 O -->
		<%} %>
			</div>
				</div>
				</div>
				</div>

</div>
</div>
</div>

<%@ include file="../common/footer.jsp"%>
</body>
</html>