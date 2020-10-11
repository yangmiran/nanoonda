<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="notice.model.vo.Notice"%>
<%
    Notice notice = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
<!--  공지사항 수정 -->
</head>
<body>
<%@ include file="../../views/common/meta.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ include file="../common/adminheader.jsp"%>

<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
<h1 class="ad_pagetit"><span>Notice Management</span><%= notice.getNoticeNo() %> 번 글 수정페이지</h1>
	
	<form action="/nnd/anupdate" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="<%= notice.getNoticeNo()%>">
		<input type="hidden" name="ofile"value="<%= notice.getOriginalFilepath() %>"> 
		<input type="hidden" name="rfile" value="<%= notice.getRenameFilepath() %>">
		<table class="ad_uptable" align="center" border="1" cellspacing="0"cellpadding="5">
		
			
			<tr>
				<th>제 목</th>
				<td>
				<input type="text" name="title" size="50" value="<%= notice.getNoticeTitle() %>"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>
				<input type="text" name="writer" readonly value="<%= notice.getNname() %>"></td>
			</tr>
			
			<tr>
				<th>파일 선택 : </th>
				<td>
					<% if(notice.getOriginalFilepath() != null){ %> 
					<%= notice.getOriginalFilepath() %>
					<input type="checkbox" name="deleteFlag" value="yes">파일삭제
					<br> <% }else{ %> 첨부파일 없음<br> 
					<input type="file" name="upfile"> 
					<% } %>
				</td>
			</tr>
			
			<tr>
				<th>내 용</th>
				<td>
				<textarea rows="5" cols="50" name="content" value="<%= notice.getNoticeContent()%>"></textarea>
				</td>
			</tr>
			
			<tr class="adtablenone">
				<th colspan="2">
				<input type="submit" value="수정하기"  class="bottomb" onclick="javascript:this.form.action='/nnd/anupdate';"/> 
				<input type="button" value="이전 페이지로" onclick="javascript:history.go(-1); return false;"> 
				</th>
			</tr>
	</table>
</form>
</div>
</body>
</html>