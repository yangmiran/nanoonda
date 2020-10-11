<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="reply.model.vo.Reply, java.util.ArrayList"%>
<%
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_ALARM</title>
	<%@ include file="../../views/common/meta.jsp"%>

</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>New Comments</span>"<%= loginMember.getnName() %>" 님께 새로운 댓글이 달렸습니다.</h1>
	<div id="tempcon">
	<!-- 댓글 출력 테이블 -->
	<ul class="list_reply">
		<% for(Reply r : list){ %>												
			<li class="list_print">			
				<span class="reply_content">
					<span id="r_nickname"><%= r.getReplyWriter() %></span>
					<span id="r_content"><%= r.getReplyContent() %></span>
					<span id="r_date"><%= r.getReplyDate() %></span>
					<a href="/nnd/ddetail?dno=<%= r.getDiaryNo() %>">본문 보러가기</a>
				</span>
			</li>		
		<% } %>		
	</ul>	
	<!-- 댓글 출력 테이블 끝 -->	
	<div class="confirm" align="center"><a href="/nnd/rcheck?writer=<%= loginMember.getnName() %>" id="confirm">새 댓글 모두 확인</a></div>
	</div>
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>