<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.model.vo.Diary, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Diary> list = (ArrayList<Diary>)request.getAttribute("list");
	String keyword = request.getAttribute("keyword").toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_SEARCH</title>
	<%@ include file="../../views/common/meta.jsp"%>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<h1 class="subtit"><span>Search</span>'<%= keyword %>'에 대한 검색 결과입니다.</h1>
	<div id="tempcon">
	<table class="dt" id="diary">
	<colgroup>
			<col width="50%" />
			<col width="20%" />
			<col width="20%" />
			<col width="10%" />
			<col width="*" />
	</colgroup>
    <thead>
    <tr>
        <th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <% for(Diary d : list){ %>
		<tr>
			<td>
				<a href="/nnd/ddetail?dno=<%= d.getDiaryNo() %>&page=1"><%= d.getDiaryTitle() %></a>
			</td>
			<td><%= d.getDiaryWriter() %></td>
			<td><%= d.getDiaryDate() %></td>
			<td><%= d.getReadCount() %></td>
		</tr>
	<% } %>	
    </tbody>
	</table>
	<div id="templist">
		<input type="button" value="돌아가기" class="bottomb" onclick="javascript:history.go(-1)">
	</div>
	</div>
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>