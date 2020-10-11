<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="post.model.vo.Post" %>
<% 
	Post post = (Post)request.getAttribute("post");
%>
<!DOCTYPE html>
<html>
<head>

 <meta charset="utf-8">
        <title>NANOONDA_POST</title>
        <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>
				
    </head>
    
<body>
<%@ include file="../common/header.jsp" %>
<h1 class="subtit"><span>ModifyWrite</span>수정하기</h1>	
<section id="postwrap">

	<!-- Page Content -->
	<div id="postCon">	
<div id="postTextIn">


<form class="postwrite" method="post" name="form" enctype="multipart/form-data">

<input type="hidden" name="nn" value="<%=loginMember.getnName()%>">
<input type="hidden" name="no" value="<%=post.getPostNo() %>">
<input type="hidden" name="pfile" value="<%=post.getPostFile() %>">
<input type="hidden" name="prfile" value="<%=post.getPostRefile()%>">

 <table class="pR" width="700" border="2" >
 
 <tr class="tit"> 
<th><font>제목</font></th>
	<td><input type="text" name="title" value="<%=post.getPostTitle()%>"></td></tr>

<tr class="to">
<th><font>받는사람</font></th>
	<td><input type="text" name="receiver" value="<%=post.getPostReceiver()%>"></td></tr>

<tr class="file">
	<th><font>파일선택</font></th>
	<td>
		<%if(post.getPostFile() != null){ %>
		<%= post.getPostFile() %> &nbsp;
		<input type="checkbox" name="deleteFlag" value="yes"> 파일삭제 <br>
		<% }else{ %>
			&nbsp;<br>
			<input type="file" name="upfile">	
		<% } %>
	</td>
</tr>

<tr class="cont">
	<th><font>내용</font></th>
	<td><textarea rows="40" cols="70" name="content"><%=post.getPostContent()%></textarea></td>
</tr>
<tr>

	<tr class="detailBtn">
		<th colspan="2">
			<input type="submit" value="목록" class="bottomb" onclick="javascript:this.form.action='/nnd/ptlist?page=1';">
			<input type="submit" value="다시저장" class="bottomb" onclick="javascript:this.form.action='/nnd/ptupdate';"/>
			<input type="submit" value="보내기" class="bottomb" onclick="javascript:this.form.action='/nnd/ptsinsert';"/>		
		</th>
	</tr>
</table>
</form>
</div>
</div>
</section>
<script>
			CKEDITOR.replace("content", { height:"400"});
 		</script>
 		<%@ include file="../common/footer.jsp"%>
</body>
</html>