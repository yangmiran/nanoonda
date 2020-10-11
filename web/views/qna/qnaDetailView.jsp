<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="qna.model.vo.Qna, qnareply.model.vo.QnaReply, java.util.ArrayList"%>
    <% Qna qna = (Qna) request.getAttribute("qna"); 
    		QnaReply reply= (QnaReply)request.getAttribute("qnareply");
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA MYPAGE</title>
<!--  유저가 보는 문의사항 상세 -->
</head>
<body>
<%@ include file="../../views/common/meta.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1">

 <%@ include file="../common/header.jsp" %> 
<h1 class="subtit"><span>QnA</span><%= qna.getQnaNo() %>번 문의사항 상세보기</h1>


<div id="wrap">
<div class="my_page">
<div class="my_table">
<table>
	<tr>
	<th>제목</th>
	<td><%= qna.getQnaTitle() %></td>
	</tr>
	
	<tr>
	<th>작성자</th>
	<td><%= qna.getNname() %></td>
	
	<tr>
	<th>등록날짜</th>
	<td><%= qna.getQnaDate() %></td>
	<tr>
	
	<tr>
      <th>첨부파일</th>
      <td>
         <% if(qna.getQnaOriginalFilepath()!= null){ %>
         <a href="/nnd/qfdown?qfile=<%= qna.getQnaOriginalFilepath() %>&rfile=<%= qna.getQnaRenameFilepath() %>"><%= qna.getQnaOriginalFilepath() %></a>
         <% }else{ %>
         &nbsp;
         <% } %>
      </td>
   </tr>
	
	<tr>
	<th>내용</th>
	<td><%= qna.getQnaContent() %></td>
	</tr>
	</table>
	
	<!-- 상세보기에서 목록으로 돌아가기 -->
	<div class="btn_in">
	<button onclick="javascript:history.go(-1);">목록으로</button>
	</div>
	<% if(reply != null){ %>
	<!-- 답글 달기 -->
<div id="wrap">
<div class="my_page">
<div class="my_table">
	<table>
	<tr>
	<th>제목</th>
	<td><%= reply.getReplyTitle() %></td>
	</tr>
	
	<tr>
	<th>작성자</th>
	<td><%= reply.getNname() %></td>
	
	<tr>
	<th>등록날짜</th>
	<td><%= reply.getReplyDate() %></td>
	<tr>
	
	<tr>
	<th>내용</th>
	<td><%= reply.getReplyContent() %></td>
	</tr>
	</table>
	</div>
	</div>
	<% } else { } %>
	 <%-- <%@ include file="../common/footer.jsp"%> --%>
	</body>
</html>