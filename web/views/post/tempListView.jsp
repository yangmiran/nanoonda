<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, post.model.vo.Post" %>
<%
   ArrayList<Post> list = (ArrayList<Post>)request.getAttribute("list");
   int listCount = ((Integer)request.getAttribute("listCount")).intValue();
   int startPage =((Integer)request.getAttribute("startPage")).intValue();
   int endPage =((Integer)request.getAttribute("endPage")).intValue();
   int maxPage =((Integer)request.getAttribute("maxPage")).intValue();
   int currentPage =((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>NANOONDA_POST</title>
	<%@ include file="../../views/common/meta.jsp"%>

<script type="text/javascript">
   function allCk(objCheck){ //전체 선택 checkbox 클릭
     var checks = document.getElementsByName('no');
     for( var i = 0; i < checks.length; i++ ){
      checks[i].checked = objCheck;
      // name이 'dno' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
     }
    }
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
<h1 class="subtit"><span>Post</span>임시저장함</h1>	
<section id="postwrap">
<!-- Page Content -->
<div id="postCon">	


<%if(loginMember !=null){ %>

	<table class="dt" id="diary" align="center">
<caption class="postcount"><font><span class="icon ti-email"></span> 임시저장편지: <%=listCount %>통</font></caption>

<br><br>
<form class="ppostlist" action="/nnd/ptldel" method="post">


	<colgroup>
			<col width="10%" />
			<col width="45%" />
			<col width="20%" />
			<col width="10%" />
			<col width="15%" />
			<col width="*" />
	</colgroup>

<tr>
	<th><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th></th>
	<th>제목</th>
	<th>받을사람</th>
	<th>첨부파일</th>
	<th>저장날짜</th>
</tr>

<tbody>
<% for(Post p : list) { %>
<tr><td>
<input type="checkbox" name="no" class="bottom" value="<%=p.getPostNo()%>"></td>
<td><a href="/nnd/ptdetail?tempno=<%=p.getPostNo()%>"><%=p.getPostTitle()%></a></td>
<td><%=p.getPostReceiver()%></td>
<td><%if(p.getPostFile() != null){ %>
<span class="ti-save"></span> <!-- 첨부파일 -->
<% }else{%>
&nbsp;
<% } %>
</td>
<td><%=p.getSendDate()%></td>
</tr>
<% } %>
<tr class="deleteBtn">
  <td colspan="1">
 <input class="bottomb" type="submit" value="삭제">
 </td>
 </tr>
</tbody>
</table>
</form>

<!-- 페이징처리 -->
<br>
<div id="page" style="text-align: center;">
<% if(currentPage<=1){ %>
  <span class="ti-angle-double-left"></span>
<%}else{ %>
   <span><a href="/nnd/ptlist?page=1" class="ti-angle-double-left"></a></span>
<%} %>


<% if((currentPage -10)<startPage && (currentPage -10) >1){ %>
   <a href="/nnd/ptlist?page=<%=startPage-10 %>" class="ti-angle-left"></a>
<%}else{ %>
<span class="ti-angle-left"></span>
<%} %>


<%for(int sp= startPage ; sp<=endPage; sp++){ 
   if(sp ==currentPage){%>
   <span class="number"><font><b><%=sp %></b></font></span>
   <%}else{ %>
   <span class="number"><a href="/nnd/ptlist?page=<%=sp %>"><%=sp %></a></span>
   <%} %>

<%} %>

<% if((currentPage +10)>endPage && (currentPage +10) <maxPage){ %>
   <a href="/nnd/ptlist?page=<%=endPage+10 %>" class="ti-angle-right"></a>
<%}else{ %>
<span class="ti-angle-right"></span>
<%} %>


<% if(currentPage>=maxPage){ %>
  <span class="ti-angle-double-right"></span>
<%}else{ %>
   <span><a href="/nnd/ptlist?page=<%=maxPage %>" class="ti-angle-double-right"></a></span> 
<%} %>

<%} else{%>
&nbsp;
<%} %>
</div>
</div>
</section>
</body>
</html>