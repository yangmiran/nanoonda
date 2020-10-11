<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="reply.model.vo.Reply" %>
<%
	Reply reply = (Reply)request.getAttribute("reply");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>NANOONDA_OPENDIARY</title>
	<%@ include file="../../views/common/meta.jsp"%>
	
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>Comment</span>댓글 수정하기</h1>
	<div id="tempcon">
	<div id="updateReplyForm">
        <form method="post" action="/nnd/rupdate">
  	   		<input type="hidden" name="rno" value="<%= reply.getReplyNo() %>">
        	<input type="hidden" name="dno" value="<%= reply.getDiaryNo() %>">
			<input type="hidden" name="page" value="<%= currentPage %>">  
            <textarea rows="7" cols="70" name="content" id="replyup" required><%= reply.getReplyContent() %></textarea>
            <br><br>
            <input class="bottomb" type="submit" value="수정">
            <input class="bottomb" type="button" value="취소" onclick="history.go(-1); return false;">
        </form>
    </div>
    </div>
    <%@ include file="../../views/common/footer.jsp"%>
</body>
</html>