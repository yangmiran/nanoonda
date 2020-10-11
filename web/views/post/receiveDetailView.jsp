<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="post.model.vo.Post"%>
<% Post post = (Post)request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">

<title>NANOONDA_POST</title>
 <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>
		
		<style>		
		.reD .titlc{center}  
		
		table{
		margin-left:auto;
		margin-right:auto;
		text-align: center; 
		}
	</style>	
</head>


<body>
<%@ include file="../common/header.jsp" %>
<h1 class="subtit"><span>Post</span><%=post.getPostSender()%>님에게 받은편지</h1>	

<section id="postwrap">

<!-- Page Content -->
<div id="postCon">

	<form method="post" name="form" enctype="multipart/form-data">
	
		<input type="hidden" name="no" value="<%=post.getPostNo() %>">
		
		<table class="reD">
			<colgroup>
				<col width="20%" />
				<col width="*" />
			</colgroup>
			<tr class="declaration">
				<th colspan="2">	
					<a href="/nnd/rduple2.u?pno=<%=post.getPostNo() %>&pwriter=<%= post.getPostSender()%>"><img src="/nnd/resources/images/report.png"></a>
				</th>
			</tr>
			<tr class="tit"><th><font>제 목</font></th><td class="titc"><%=post.getPostTitle()%></td></tr>
			
			<tr class="from"><th><font>보낸사람</font></th><td><%=post.getPostSender()%></td></tr>
			
			<tr class="date"><th><font>받은날짜</font></th><td><%=post.getSendDate()%></td></tr>
			
			<tr class="file"><th><font>첨부파일</font></th><td>
					<% if(post.getPostFile() != null){ %>
					<a href="/nnd/pdown?pfile=<%=post.getPostFile()%>&prfile=<%=post.getPostRefile()%>"><%= post.getPostFile() %></a>
					<% }else{ %>
					&nbsp;
					<% } %>
				</td>
			</tr>
			
			<tr class="cont"><th>내 용</th><td><%=post.getPostContent() %></td></tr>
			<tr class="detailBtn">
				<th colspan="2">
					<input type="button" value="목록" class="bottomb" onclick="javascript:history.go(-1); return false;">
					<input type="submit" value="답장보내기" class="bottomb" onclick="javascript:this.form.action='/nnd/views/post/postReplyForm.jsp?sender=<%=post.getPostSender()%>';"/>	
					<input type="submit" value="삭제" class="bottomb" onclick="javascript:this.form.action='/nnd/rdelete';"/>
				</th>
			</tr>	
		</table>
	</form>
</div>
</section>
<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>