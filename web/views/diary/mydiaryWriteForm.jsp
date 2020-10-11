<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>NANOONDA_MYDIARY</title>
        <%@ include file="../../views/common/meta.jsp"%>
		<script src="/nnd/resources/ckeditor/ckeditor.js"></script>			
    </head>
    <body>
    	<%@ include file="../common/header.jsp" %>
    	<h1 class="subtit"><span>Write</span>나만 보고 싶은 나의 이야기</h1>
		<div class="writecon">   
		<div id="writeTextIn">
        <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="writer" value="<%= loginMember.getnName() %>">
          <table class="write">
            <tr>                
                <td colspan="2">
                	<input type="text" name="title" id="title">
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
                    <input type="submit" value="일기등록" class="bottomc" onclick="javascript:this.form.action='/nnd/mdinsert';"/>
                    <input type="button" value="작성취소" class="bottomc" onclick="javascript:history.go(-1); return false;">
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

