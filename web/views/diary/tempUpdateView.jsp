<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="diary.model.vo.Diary" %>
<%
	Diary diary = (Diary)request.getAttribute("diary");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_OPENDIARY</title>
	<%@ include file="../../views/common/meta.jsp"%>
	<script src="/nnd/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>Write</span>당신의 이야기를 들려주세요.</h1>
		<div class="writecon">      
		<div id="writeTextIn">
        <form action="/nnd/tupdate" method="post" enctype="multipart/form-data">     
        <input type="hidden" name="dno" value="<%= diary.getDiaryNo() %>">
		<input type="hidden" name="page" value="<%= currentPage %>">		
		<input type="hidden" name="ofile" value="<%= diary.getDiaryOriginfile() %>">
		<input type="hidden" name="rfile" value="<%= diary.getDiaryRenamefile() %>">
          <table class="write">
            <tr>                
                <td>
                	<input type="text" name="title" id="title" value=<%= diary.getDiaryTitle() %>>
                </td>                
            </tr>             
            <tr>                
                <td><textarea rows="10" cols="50" name="content"><%= diary.getDiaryContent() %></textarea></td>
            </tr>
            <tr>
	            <td><% if(diary.getDiaryOriginfile() != null){ //첨부파일이 있다면 %>
	     			 <%= diary.getDiaryOriginfile() %>
	      			<input type="checkbox" name="delflag" value="yes">사진삭제 <!--체크 선택시 파일 삭제  -->
	   				<% } //파일 없으면 업로드만 나옴  //전에있던 파일까지 같이 가야되므로 upfile %>
	   				<input type ="file" name="upfile" id="attach"> <!-- multiful여러개 선택가능 -->
	   			</td>
	   		</tr>
            <tr>                 
                <td id="btnarea" colspan="2"  class="button" align="center">
                    <input type="submit" value="일기등록" class="bottomb">
                    <input type="button" value="뒤로가기" class="bottomb" onclick="javascript:history.go(-1); return false;">
                </td>
            </tr>             
          </table>
        </form>
        </div>
        </div>
        <%@ include file="../../views/common/footer.jsp"%>     
		<script>
			CKEDITOR.replace("content", { height:"400"});
 		</script>
</body>
</html>