<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.model.vo.Diary, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Diary> list = (ArrayList<Diary>)request.getAttribute("list");
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
<title>NANOONDA_MYDIARY</title>
	<%@ include file="../../views/common/meta.jsp"%>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<h1 class="subtit"><span>My Diary</span>마이 다이어리</h1>
	<div class="writecon" id="mycon">
	<div align="center">
		<a href="/nnd/views/diary/mydiaryWriteForm.jsp" class="mywrite">나만의 일기 쓰기</a>
	</div>
	<div id="search" align="center">
		<form action="/nnd/mdsearch" method="post">
			<input type="text" name="keyword" class="searchblock">
			<input class="bottomc" type="submit" value="제목검색">
		</form>
	</div>
	<table class="dt" id="diary" align="center">
	<colgroup>
			<col width="70%" />
			<col width="30%" />
			<col width="*" />
	</colgroup>
    <thead>
    <tr>
        <th>제목</th><th>날짜</th>
    </tr>
    </thead>
    <tbody>
    <% for(Diary d : list){ %>
		<tr>
			<td>
				<a href="/nnd/mddetail?dno=<%= d.getDiaryNo() %>&page=<%= currentPage %>"><%= d.getDiaryTitle() %></a>
				<% if(d.getDiaryOriginfile()!=null){ %>
				<span class="ti-image" style="margin-left:5px;"></span><% } %>
			</td>
			<td><%= d.getDiaryDate() %></td>
		</tr>
	<% } %>
    </tbody>
	</table>
	<!-- 페이징 처리 -->
	<div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span class="ti-angle-double-left"></span>
		<% } else { %>
			<span><a href="/nnd/mdlist?page=1" class="ti-angle-double-left"></a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span class="number"><font><b><%= p %></b></font></span>
			<% } else { %>
				<span class="number"><a href="/nnd/mdlist?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span class="ti-angle-double-right"></span>
		<% } else { %>
			<span><a href="/nnd/mdlist?page=<%= maxPage %>" class="ti-angle-double-right"></a></span>
		<% } %>
	</div>
	
	</div>
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>