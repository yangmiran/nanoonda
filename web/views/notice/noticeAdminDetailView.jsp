<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice"%>
    <% Notice notice = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
<!--  관리자가 보는 공지사항 상세보기 -->
<%@ include file="../../views/common/meta.jsp"%>

</head>
<body>
<%@ include file="../common/adminheader.jsp" %>


<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
<h1 class="ad_pagetit"><span>Notice Management</span><%= notice.getNoticeNo() %> 번 공지사항 상세보기</h1>

<table align="center" class="w3-table-all w3-small">

<tr>
<th>제목</th>
<td><%= notice.getNoticeTitle() %></td>
</tr>

<tr>
<th>작성자</th>
<td><%= notice.getNname() %></td>
</tr>

<tr>
<th>등록날짜</th>
<td><%= notice.getNoticeDate() %></td>
</tr>

<tr>
<th>첨부파일 : </th>
<td><% if(notice.getOriginalFilepath() != null) { %>
<a href="/nnd/nfdown?ofile=<%= notice.getOriginalFilepath() %>&rfile=<%= notice.getRenameFilepath() %>"><%= notice.getOriginalFilepath() %></a>
<% } else{ %>
&nbsp;
<% } %>
</td>
</tr>

<tr>
<th>내용</th>
<td><%= notice.getNoticeContent() %></td>
</tr>

<tr>
      <th colspan="2" float="center">
      <div class="ad_detailbtn">
      <button onclick="javascript:location.href='/nnd/npmove?notice_no=<%= notice.getNoticeNo()%>';">수정페이지로 이동</button>
      <button onclick="javascript:location.href='/nnd/ndel?notice_no=<%= notice.getNoticeNo() %>&rfile=<%= notice.getRenameFilepath() %>';">삭제하기</button>
      <button onclick="javascript:history.go(-1);">목록으로</button>
      </div>
      </th>
   </tr>
</table>
<br>

</body>
</html>