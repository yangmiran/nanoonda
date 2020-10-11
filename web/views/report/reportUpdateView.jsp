<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="report.model.vo.Report"%>

<% Report report = (Report)request.getAttribute("report");%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA MY PAGE</title>
 <%@ include file="../../views/common/meta.jsp"%>
</head>

<!-- 신고 상세보기에서 수정버튼 클릭했을 때 나오는 창 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body>
 <%@ include file="../common/header.jsp" %>
    <h1 class="subtit"><span>Edit</span>신고글 수정하기</h1>
	<div class="writecon">   
    <form action="/nnd/rupdate2" method="post">      
    <input type="hidden" name="rno" value="<%= report.getReportNo() %>">
	<input type="hidden" name="writer" value="<%= report.getsenderNname()%>">

    	<table class="write">
           <tr>                
               <td>
               	<input type="text" name="title" id="title" value=<%= report.getReportTiltle() %>>
               </td>                
           </tr>             
           <tr>                
               <td><textarea rows="10" cols="50" name="content"><%= report.getReportContent() %></textarea></td>
           </tr>
           <tr>                 
               <td id="btnarea" colspan="2"  class="button" align="center">
                   <input type="submit" value="신고내역수정" class="bottomb">
                   <input type="button" value="수정취소" class="bottomb" onclick="javascript:history.go(-1); return false;">
               </td>
           </tr>             
         </table>
       </form>
    </div>
    <%@ include file="../../views/common/footer.jsp"%>
	<script>
		CKEDITOR.replace("content", { height:"400"});
	</script>
</html>