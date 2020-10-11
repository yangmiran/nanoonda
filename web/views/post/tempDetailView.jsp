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
<h1 class="subtit"><span>Post</span>임시저장 편지</h1>	

<% String toNn=null;
if(request.getParameter("nName") != null){
	toNn=(String)request.getParameter("toNn");
}%>


<section id="postwrap">

<!-- Page Content -->
<div id="postCon">


<form method="post" name="form" enctype="multipart/form-data">

<table class="sendD">


<tr class="tit"><th><font>제 목</font></th><td class="titc"><%=post.getPostTitle()%> </td></tr>
<tr class="to"><th><font>받을사람</font></th><td><%=post.getPostReceiver()%></td></tr>
<tr class="date"><th><font>저장날짜</th><td><%=post.getSendDate()%></td></tr>

<tr class="file"><th><font>첨부파일</font></th><td>
		<% if(post.getPostFile() != null){ %>
		<a href="/nnd/pdown?pfile=<%=post.getPostFile()%>&prfile=<%=post.getPostRefile()%>"><%= post.getPostFile() %></a>
		<% }else{ %>
		&nbsp;
		<% } %>
	</td>
</tr>

<tr class=""><th>내 용</th><td><%=post.getPostContent() %></td></tr>
<tr class="detailBtn">
	<th colspan="2">
	<input type="button" value="목록" class="bottomb" onclick="javascript:history.go(-1); return false;">
	<input type="submit" value="수정하기" class="bottomb" onclick="javascript:this.form.action='/nnd/ptupview?postno=<%=post.getPostNo()%>';"/>
	<input type="submit" value="삭제하기" class="bottomb" onclick="javascript:this.form.action='/nnd/ptdelete?postno=<%=post.getPostNo()%>';"/>
	</th>
</tr>
</table>
</form>
</div>
</section>
<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>