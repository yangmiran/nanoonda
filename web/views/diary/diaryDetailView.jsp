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
<title>NANOONDA_OPENDIARY</title>
<%@ include file="../../views/common/meta.jsp"%>
<script>
//댓글 테이블 좋아요/싫어요 오버시 컬러변경
$(document).ready(function(){
	$("#likebtn img").mouseover(function(){
		$(this).attr("src", "/nnd/resources/images/like_on.png");
	});
	$("#likebtn img").mouseout(function(){
		$(this).attr("src", "/nnd/resources/images/like_off.png");
	});
	
	$("#hatebtn img").mouseover(function(){
		$(this).attr("src", "/nnd/resources/images/hate_on.png");
	});
	$("#hatebtn img").mouseout(function(){
		$(this).attr("src", "/nnd/resources/images/hate_off.png");
	});
});
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>Open Diary</span>오픈 다이어리</h1>
	<div class="writecon">
	<!-- 일기 상세보기 테이블 -->		
	<table class="dt" id="diarydetail" align="center">		
		<tr><th>제목</th><td><%= diary.getDiaryTitle() %></td></tr>
		<tr><th>작성자</th><td><%= diary.getDiaryWriter() %></td></tr>
		<tr><th>작성일</th><td><%= diary.getDiaryDate() %></td></tr>
		<tr><td colspan="2" id="contentarea">
			<% if(diary.getDiaryOriginfile() != null) {%>
					<img src="/nnd/resources/dupimages/<%= diary.getDiaryRenamefile() %>"><br><br>		
				<% }else{ } %>
			<%= diary.getDiaryContent() %></td>
		</tr>
		<tr>
				<td colspan="2" align="center">
				<a href="/nnd/rduple.u?dno=<%=diary.getDiaryNo() %>&dwriter=<%= diary.getDiaryWriter() %>"><img src="/nnd/resources/images/report.png"></a> 
				</td>
        </tr>
        <tr>			            
            <td colspan="2" class="button" align="center">
            	<% if(loginMember.getnName().equals(diary.getDiaryWriter()) || loginMember.getStatus().equals("2") || loginMember.getStatus().equals("3")){ %>
            	
            	<input type="button" value="수정" class="bottomb" onclick="javascript:location.href='/nnd/dupview?dno=<%= diary.getDiaryNo() %>&page=<%= currentPage %>&openOk=<%= diary.getOpenOk() %>'">
            	<input type="button" value="삭제" class="bottomb" onclick="javascript:location.href='/nnd/ddel?dno=<%= diary.getDiaryNo() %>&openOk=<%= diary.getOpenOk() %>'">
                <% } %>
                <input type="button" value="목록" class="bottomb" onclick="javascript:location.href='/nnd/dlist?page=<%= currentPage %>'">
            </td>
		</tr>  	
	</table>
	<!-- 일기 상세보기 테이블 끝 -->
	
	<!-- 댓글 작성 테이블 -->		
	<form id="writeform" method="post" action="/nnd/rinsert">
		<input type="hidden" name="writer" value="<%= loginMember.getnName() %>">
		<input type="hidden" name="dno" value="<%= diary.getDiaryNo() %>">
		<input type="hidden" name="page" value="<%= currentPage %>">
		<textarea id="rcontent" name="content" placeholder="당신의 예쁜 마음을 나눠주세요. (비추천 5개 누적 시 자동으로 댓글이 삭제됩니다.)" tabindex="1" style="width:800px; margin:10px;"></textarea>
		<input type="submit" class="bottomb" value="댓글달기">
	</form>
	<!-- 댓글 작성 테이블 끝 -->	
	
	
	<!-- 댓글 출력 테이블 -->
	<ul class="list_reply">
		<% for(Reply r : replyList){ %>												
			<li class="rlist_print">			
				<span class="reply_content">
					<span id="rr_nickname"><%= r.getReplyWriter() %></span>
					<span id="rr_content"><%= r.getReplyContent() %></span>
					<span id="rr_date"><%= r.getReplyDate() %></span>
					<span id="likehate" class="likehate">
						<span class="lhbtn" id="likebtn"><a href="/nnd/like?rno=<%= r.getReplyNo() %>&dno=<%= r.getDiaryNo() %>&page=<%= currentPage %>&counter=<%= loginMember.getId() %>">
							<img src="/nnd/resources/images/like_off.png"></a><%= r.getLikeCount() %></span>
						<span class="lhbtn" id="hatebtn"><a href="/nnd/hate?rno=<%= r.getReplyNo() %>&dno=<%= r.getDiaryNo() %>&hateCount=<%= r.getHateCount() %>&page=<%= currentPage %>&counter=<%= loginMember.getId() %>">
							<img src="/nnd/resources/images/hate_off.png"></a><%= r.getHateCount() %></span>
					</span>
				</span>
				<% if(loginMember.getnName().equals(r.getReplyWriter())){ %>
				<div class="my_edit">
 					<a href="/nnd/rupview?rno=<%= r.getReplyNo() %>&page=<%= currentPage %>" class="edit">수정</a>
					<a href="/nnd/rdel?rno=<%= r.getReplyNo() %>&dno=<%= r.getDiaryNo() %>&page=<%= currentPage %>" class="edit">삭제</a>
				</div>
				<% } %>
			</li>		
		<% } %>		
	</ul>
	</div>
	<!-- 댓글 출력 테이블 끝 -->
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>