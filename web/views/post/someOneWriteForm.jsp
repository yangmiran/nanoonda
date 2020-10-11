<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>NANOONDA_POST</title>
        <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>			
    </head>
    
<body>
<%@ include file="../common/header.jsp" %>
<h1 class="subtit"><span>PostWrite</span>누군가에게 편지보내기</h1>	
<section id="postwrap">
	<!-- Page Content -->
	<div id="postCon">
		<div id="postTextIn">
		
			<form class="postwrite someone" method="post" name="form" enctype="multipart/form-data">
			
			<input type="hidden" name="nn" value ="<%=loginMember.getnName()%>">
			<table class="pR">
			            
			<tr class="tit">                              
			<th><font>제목</font></th>
				<td><input type="text" name="title"></td>
			</tr>
			
			<tr class="to">
			<th><font>받는사람</font></th>
				<td><input type="text" name="receiver" readonly value="누군가에게.. (받는사람은 렌덤입니다)">
				</td>
			</tr>
			
			<tr class="file">
				<th><font>파일선택</font></th>
				<td><input type="file" name="pfile"></td>
			</tr>
			
			<tr class="cont">
				<th><font>내용</font></th>
				<td><textarea rows="40" cols="70" name="content"></textarea></td>
			</tr>
		
			
			<tr class="deleteBtn">
			<th colspan="2">
				<input type="button" value="뒤로" class="bottomb" onclick="javascript:history.go(-1); return false;">
				<input type="submit" value="보내기" class="bottomb" onclick="javascript:this.form.action='/nnd/psinsert';"/>
				<input type="submit" value="임시저장" class="bottomb" onclick="javascript:this.form.action='/nnd/ptsoinsert';"/>
		
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