<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
<!--  공지사항 글쓰기 -->
<%@ include file="/views/common/meta.jsp"%>
<style>
select{ 
display:none;
}
</style>

</head>
<body>
<%@ include file="../common/adminheader.jsp" %> 
<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
<h1 class="ad_pagetit"><span>Notice Management</span>공지글 등록</h1>

<form action="/nnd/aninsert" method="post"   enctype="multipart/form-data">
<table class="ad_uptable" align="center" width="500" border="1" cellspacing="0" cellpadding="5" style="margin:0 auto"; >

<tr>
<th>제목</th>
<td>
<input type="text" name="title"  rows="5"  cols="50" size="50" height="50px"></td>
</tr>

<tr>
<th>작성자</th>
 <td>
 <input type="text" name="writer"  size="50" readonly  value="<%= loginMember.getnName() %>"></td>
</td>
</tr>
 
 <tr>
 <th>파일 선택 : </th>
 <td><input type="file" name="ofile">
 </td>
 </tr>

<tr>
<th>내용</th>
<td><textarea rows="5"  cols="50" name="content" size="1000" style=" width:700px; height:500px"></textarea></td>

</tr>

<tr class="adtablenone">
<th colspan="2" >
<input type="submit" value="등록하기" class="bottomb" onclick="javascript:this.form.action='/nnd/aninsert';"/>
<input type="reset" value="작성취소" class="bottomb" onclick="javascript:history.go(-1); return false;'">
<input type="button" value="목록으로" class="bottomb" onclick="javascript:history.go(-1); return false;">
</th>
</tr>
</table>
</form>
</table>
</div>

</body>
</html>