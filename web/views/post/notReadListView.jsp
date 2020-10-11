<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="java.util.ArrayList, post.model.vo.Post, java.sql.Date" import="member.model.vo.Member"%>
<%
   ArrayList<Post> list = (ArrayList<Post>)request.getAttribute("list");

%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>NANOONDA_POST</title>
	<%@ include file="../../views/common/meta.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	
		<h1 class="subtit"><span>Post</span>안 읽은 편지함</h1>	

<section id="postwrap">

<!-- Page Content -->
<div id="postCon">


<%if(loginMember !=null){ %>

	<table class="dt" align="center">

	<colgroup>
			<col width="45%" />
			<col width="20%" />
			<col width="10%" />
			<col width="25%" />
			<col width="*" />
	</colgroup>
   
<tr>
	<th>제목</th>
	<th>보낸사람</th>
	<th>첨부파일</th>
	<th>받은날짜</th>
</tr>
<tbody>

<% for(Post p : list) { %>
<tr>
<td><a href="/nnd/pnrdetail?receiveno=<%=p.getPostNo()%>"><%=p.getPostTitle()%></a></td>
<td><%=p.getPostSender()%></td>
<td><%if(p.getPostFile() != null){ %>
◎
<% }else{%>
&nbsp;
<% } %>
</td>
<td><%=p.getSendDate()%></td>
</tr>
 <% } %>
</tbody>
</table>
<%} else{%>
&nbsp;
<%} %>
</div>
</div>
</section>
<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>