<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>NANOONDA_OPENDIARY</title>
        <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>			
    </head>
    <body>
    	<%@ include file="../common/header.jsp" %>
    	<h1 class="subtit"><span>Write</span>당신의 이야기를 들려주세요.</h1>
		<div class="writecon">
		<div id="writeTextIn">
        <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="writer" value="<%= loginMember.getnName() %>">
          <table class="write">
            <tr>                
                <td colspan="2">
                	<input type="text" name="title" id="title2">
                	<a href="/nnd/tlist?writer=<%= loginMember.getnName() %>" id="tempmove">임시저장글</a>
                </td>                
            </tr>             
            <tr>                
                <td><textarea name="content"></textarea></td>
            </tr>
            <tr>
				<td><input type="file" name="ofile"></td>
			</tr>
            <tr>            
                <td id="btnarea2" colspan="2" align="center">
                    <input type="submit" value="일기등록" class="bottomb" onclick="javascript:this.form.action='/nnd/dinsert';"/>
                    <input type="submit" value="임시저장" class="bottomb" onclick="javascript:this.form.action='/nnd/dtemp';"/>
                    <input type="button" value="작성취소" class="bottomb" onclick="javascript:history.go(-1); return false;">
                </td>
            </tr>             
          </table>
        </form>
        </div>
        </div>
        <%@ include file="../../views/common/footer.jsp"%>
		<script>
			CKEDITOR.replace("content", { 
				height:"400"
			});
 		</script>
	</body>
</html>

