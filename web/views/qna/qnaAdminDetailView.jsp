<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="qna.model.vo.Qna, qnareply.model.vo.QnaReply"%>
    <% Qna qna = (Qna) request.getAttribute("qna");  
    QnaReply qnareply = (QnaReply)request.getAttribute("qnareply");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_QNA</title>
<!--  문의사항 관리자가 목록 상세보기 -->
<%@ include file="../../views/common/meta.jsp"%>

</head>
<body>
<%@ include file="../common/adminheader.jsp" %>

<div id="ad_listview"> <!-- 전체 묶은 div : start -->	

<h1 class="ad_pagetit"><span>Qna Management</span><%= qna.getQnaNo() %> 번 문의사항 상세보기</h1>

<table align="center" class="w3-table-all w3-small serchlist">

<tr>
<th>제목</th>
<td><%= qna.getQnaTitle() %></td>
</tr>

<tr>
<th>작성자</th>
<td><%= qna.getNname() %></td>
</tr>

<tr>
<th>등록날짜</th>
<td><%= qna.getQnaDate() %></td>
</tr>

<tr>
<th>첨부파일 : </th>
<td><% if(qna.getQnaOriginalFilepath() != null) { %>
<a href="/nnd/qfdown?qfile=<%= qna.getQnaOriginalFilepath() %>&rfile=<%= qna.getQnaRenameFilepath() %>"><%= qna.getQnaOriginalFilepath() %></a>
<% } else{ %>
&nbsp;
<% } %>
</td>
</tr>

<tr>
<th>내용</th>
<td><%= qna.getQnaContent() %></td>
</tr>

	<tr>
      <th colspan="2" float="center">
      <div class="ad_detailbtn">
      <button onclick="javascript:location.href='/nnd/qdel?qna_no=<%= qna.getQnaNo() %>&rfile=<%= qna.getQnaRenameFilepath() %>';">삭제하기</button>
      <button onclick="javascript:history.go(-1);">목록으로</button>
      <button onclick="location.href='views/qnareply/QnaReplyForm.jsp?qnum=<%= qna.getQnaNo() %>'">댓글달기</button>
      </div>
      </th>
   </tr>
</table>

<!-- 페이징 처리 -->

<%-- <div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span>◀◀</span>
		<% } else { %>
			<span><a href="/nnd/rlist.ad?page=1">◀◀</a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span><font color="steelblue" size="4"><b><%= p %></b></font></span>
			<% } else { %>
				<span><a href="/nnd/rlist.ad?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span>▶▶</span>
		<% } else { %>
			<span><a href="/nnd/rlist.ad?page=<%= maxPage %>">▶▶</a></span>
		<% } %>
	</div> --%>
</div> <!-- 전체 묶은 div : end -->	

</body>
</html>