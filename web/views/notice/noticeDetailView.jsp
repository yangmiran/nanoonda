<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "notice.model.vo.Notice"%>
    <% Notice notice = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
<!--  유저가 보는 공지사항 상세 -->
</head>
<body>
<%@ include file="../../views/common/meta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<%@ include file="../common/header.jsp" %>

<h2 align="center"><%= notice.getNoticeNo() %>번 공지사항 상세보기</h2>
	<div id="wrap">
	<div class="my_page">
	<div class="my_table">
	<table>
	<tr>
      <th>제 목</th>
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
      <th>내 용</th>
      <td><%= notice.getNoticeContent() %></td>
   </tr>
   </table>
   </div>
    <!--  상세보기에서 목록으로 돌아가기 -->
   <div class="btn_in">
      <button onclick="javascript:history.go(-1);">목록으로</button>
   </div>
   </div>
   </div>
  <%@ include file="../common/footer.jsp"%>
</body>
</html>
	
