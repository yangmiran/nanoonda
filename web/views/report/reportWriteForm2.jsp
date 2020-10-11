<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
 <!--우편함 전용 신고 글 작성  -->   
<%  int pno = Integer.parseInt(request.getParameter("pno")); //우편함의 글 번호 가져옴
	String pwriter =request.getParameter("pwriter");// 우편함의 작성자 (신고대상자)
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA REPORT</title>
 <%@ include file="../../views/common/meta.jsp"%>
  <script src="/nnd/resources/ckeditor/ckeditor.js"></script>
</head>

<!-- 신고하기 버튼 클릭했을 때 나오는 창 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>
<%@ include file="../common/header.jsp" %>
<h1 class="subtit">
		<span>Report Write</span>신고하기
	</h1>
	<section id="postwrap">
	<div id="postCon" class="qnawrite">
			<div id="postTextIn">
불법게시물, 음란, 명예훼손  등의 피해를 신고해주세요.<br>
불편을 끼쳐드려서 죄송합니다. 깨끗한 서비스를 만들어가도록 하겠습니다.</div>
<form method="post">
<input type="hidden" name="reportsender" value="<%= loginMember.getnName() %>">
<input type="hidden" name="reporttype" value="2">
<input type="hidden" name="postno" value="<%= pno %>">
<input type="hidden" name="pwriter" value="<%= pwriter %>">
	<table class="qnaform">
	 	<table class="pR">
		<tr>              
        	<th>신고 제목</th> 
      		<td><input type="text" name="reporttitle" id="title"placeholder="신고 제목을 입력하세요"></td>                
        </tr>             
        <tr>
        	<th>신고 내용</th>     
            <td><textarea rows="5" cols="50" name="content" placeholder="신고 내용을 입력하세요" ></textarea></td>
   		</tr>
   		 </table>
	</table>
	<div class="qbtn">
		<input type="submit" value="신고하기" class="bottomb" onclick="javascript:this.form.action='/nnd/rinsert2.u';"/>
		<input type="button" value="취소" class="bottomb" onclick="javascript:history.go(-1); return false;">
	</div>
</form> 
</div>
		</div>
	</section>
	<script>
			CKEDITOR.replace("content", { height:"400"});
 		</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>