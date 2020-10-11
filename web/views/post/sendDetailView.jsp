<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="post.model.vo.Post, post.model.vo.Post, java.sql.Date"%>
    <% Post post = (Post)request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>NANOONDA_POST</title>
   <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>
	<style>
		.sendD .cont{height:400px}
		.sendD .tit{height:30px}
		.sendD .to{height:30px}
		.sendD .date{height:30px}
		.sendD .file{height:30px}
		.sendD .chk{height:30px}
		
		.sendD .titlc{center}  
		
		table{
		margin-left:auto;
		margin-right:auto;
		text-align: center; 
		}
	</style>	
</head>


<body>
<%@ include file="../common/header.jsp" %>

<h1 class="subtit"><span>Post</span>보낸편지</h1>	
<section id="postwrap">
<!-- Page Content -->
<div id="postCon">


<form method="post" name="form" enctype="multipart/form-data">
<input type="hidden" name="no" value="<%=post.getPostNo() %>">

<table class="sendD">


<tr class="tit"><th><font>제 목</font></th><td class="titc"><%=post.getPostTitle()%></td></tr>


<tr class="to"><th><font>받는사람</font></th><td><%=post.getPostReceiver()%></td></tr>

<tr class="date"><th><font>보낸날짜</font></th><td><%=post.getSendDate()%></td></tr>


<tr class="file"><th><font>첨부파일</font></th><td>
		<% if(post.getPostFile() != null){ %>
		<a href="/nnd/pdown?pfile=<%=post.getPostFile()%>&prfile=<%=post.getPostRefile()%>"><%= post.getPostFile() %></a>
		<% }else{ %>
		&nbsp;
		<% } %>
	</td>
</tr>

<tr class="chk"><th><font>수신여부</font></th><td>
<% if(post.getReadCheck() == 1) {%>
			읽지않음
		<%} else {%>
			읽음
		<%}%>
</td>
</tr>


<tr class=""><th>내 용</th><td><%=post.getPostContent() %></td></tr>
<tr class="detailBtn">
	<th colspan="2" style="border:none;background:none;padding-top:20px">
	<input type="button" value="목록" class="bottomb" onclick="javascript:history.go(-1); return false;">
	<input type="submit" value="삭제" class="bottomb" onclick="javascript:this.form.action='/nnd/sdelete';"/>
	</th>
</tr>
</table>
</form>
</div>
</section>
<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>