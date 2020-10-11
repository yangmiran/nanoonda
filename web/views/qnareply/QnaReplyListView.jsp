<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="qnareply.model.vo.QnaReply, java.util.ArrayList, java.sql.Date" %>
    <%
    ArrayList<QnaReply> list = (ArrayList<QnaReply>)request.getAttribute("list");
    int listCount = ((Integer)request.getAttribute("listCount")).intValue();
    int startPage = ((Integer)request.getAttribute("startPage")).intValue();
    int endPage =((Integer)request.getAttribute("endPage")).intValue();
    int maxPage =((Integer)request.getAttribute("maxPage")).intValue();
    int currentPage =((Integer)request.getAttribute("currentPage")).intValue();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_QNA_REPLY</title>
<!-- 댓글달기 -->

</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center">문의글 목록 : 총 <%= listCount %> 개</h2>
<!-- 게시글 쓰기(등록)은 로그인한 회원만 가능함 -->
<%if(loginMember != null){  // 로그인 상태라면 %>
<div style="align:center; text-align: center;">
	<button onclick="showWriteForm();">글쓰기</button>
</div>
<%} %>
<br>
<table align="center" border="1" cellspacing="0" width="700">
<tr>
<th>번 호</th>
<th>제 목</th>
<th>작성자</th>
<th>날짜</th>
</tr>

<% for(QnaReply qr : list){ %>
<tr>
<td align="center"><%=qr.getQnaNo() %></td>
<td>

<!-- 댓글일 때는 제목을 들여쓰기함(보드레벨을 이용하면 확인가능) -->
<% if(qr.getReplyTitle() != null){ // 원글의 댓글일 때  %>
&nbsp; &nbsp; ▶
<% }  %>

<!-- 로그인한 사용자만 (회원만) 상세보기 할 수 있게 함 -->
<!-- 해당 게시글의 글번호 및 페이지수를 전송 -->
<% if(loginMember != null){ %>
	<a href="/nnd/qdetail?qna_no=<%= qr.getQnaNo()%>&page=<%= currentPage %>"><%= qr.getReplyTitle() %></a>
<% } else{ %>
<%= qr.getReplyTitle() %>
<% } %>
</td>
<td align="center"><%=qr.getNname()%></td>
<td align="center"><%=qr.getReplyDate()%></td>
</td>
</tr>
<%} // for each %>
</table>
<br>

<!-- 페이징 처리 -->
<div style="text-align:center;">
<% if(currentPage <= 1){ %>
	[맨처음]&nbsp;
<% } else{ %>
	<a href="/nnd/qlist?page=1">[맨처음]</a>
<% } %>

<!-- 이전 그룹으로 이동 처리 10은 페이지 10개 보여줄거냐  -->
<% if((currentPage - 10) < startPage && (currentPage - 10) > 1){ %>
<a href="/nnd/qlist?page=<%= startPage -10 %>">[이전그룹]</a> 
<% } else{ %>
	[이전그룹]&nbsp;
<% } %>

<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
<% for(int p = startPage; p <= endPage; p++){ 
 
	if(p == currentPage){     %>
	<font color="red" size="4"><b>[<%= p %>]</b></font>
	<% } else{ %>
	<a href="/test1/blist?page=<%= p %>"><%= p %></a>
	<% }} //for 닫고 if 닫고 %>

<!-- 다음 그룹으로 이동 처리 -->
<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){ %>
<a href="/nnd/qlist?page=<%= endPage + 10 %>">[다음그룹]</a> 
<% } else{ %>
	[다음그룹]&nbsp;
<% } %>

<!-- 맨끝 페이지로 이동처리 -->
<% if(currentPage >= maxPage){ %>
	[맨끝]&nbsp;
<% } else{ %>
	<a href="/nnd/qlist?page=<%= maxPage%>">[맨끝]</a>
<% } %>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>