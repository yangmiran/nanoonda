<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="diary.model.vo.Diary, reply.model.vo.Reply, java.util.ArrayList"%>
<%
	Diary diary = (Diary)request.getAttribute("diary");
	ArrayList<Reply> replyList = (ArrayList<Reply>)request.getAttribute("replyList");
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
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>My Diary</span>마이 다이어리</h1>
	<div id="tempcon">
	<!-- 일기 상세보기 테이블 -->		
	<table class="dt" id="diarydetail" align="center">		
		<tr><th>제목</th><td><%= diary.getDiaryTitle() %></td></tr>
		<tr><th>작성일</th><td><%= diary.getDiaryDate() %></td></tr>
		<tr><td colspan="2" id="contentarea">
		<% if(diary.getDiaryOriginfile() != null) {%>
					<img src="/nnd/resources/dupimages/<%= diary.getDiaryRenamefile() %>"><br><br>		
				<% }else{ } %>
		<%= diary.getDiaryContent() %></td>
		</tr>
        <tr>			            
            <td colspan="2" class="mybutton" align="center">
            	<input type="button" value="수정" class="bottomb" onclick="javascript:location.href='/nnd/dupview?dno=<%= diary.getDiaryNo() %>&page=<%= currentPage %>&openOk=<%= diary.getOpenOk() %>'">
            	<input type="button" value="삭제" class="bottomb" onclick="javascript:location.href='/nnd/ddel?dno=<%= diary.getDiaryNo() %>&openOk=<%= diary.getOpenOk() %>'">
                <input type="button" value="목록" class="bottomb" onclick="javascript:location.href='/nnd/mdlist?page=<%= currentPage %>'">
            </td>
		</tr>  	
	</table>
	<!-- 일기 상세보기 테이블 끝 -->
	</div>
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>