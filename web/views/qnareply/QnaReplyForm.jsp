<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="member.model.vo.Member, qna.model.vo.Qna, qnareply.model.vo.QnaReply"%>
<%
   int qnaNum = Integer.parseInt(request.getParameter("qnum"));
	/* Member loginMember = (Member)session.getAttribute("loginMember"); */
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>NANOONDA_QNA_REPLY</title>
   <!-- 관리자용 문의사항 댓글쓰기 -->
    <%@ include file="/views/common/meta.jsp"%> 

</head>
<body>   
 <%@ include file="/views/common/adminheader.jsp" %>
 <div id="ad_listview"> <!-- 전체 묶은 div : start -->	
   <h1 class="ad_pagetit"><span>Notice Management</span><%= qnaNum %>번글 댓글 달기 페이지</h1>

   <form method="post"> <!--  qr로 바꿈 -->
   <input type="hidden" name="qnum" value="<%= qnaNum %>">
 <table class="ad_uptable" align="center" width="500" border="1" cellspacing="0" cellpadding="5">
      
      <tr>
         <th>제목</th>
         <td><input type="text" name="title" size="50"></td>
      </tr>
      
      <tr>
         <th>작성자</th>
         <td><input type="text" name="writer" readonly value="<%= loginMember.getnName()%>"></td>
      </tr>
     
      <tr>
         <th>내용</th>
         <td><textarea rows="5" cols="50" name="content"></textarea></td></tr>
      
      <tr class="adtablenone">
         <th colspan="2">
         <input type="submit" value="등록하기" onclick="javascript:this.form.action='/nnd/qrinsert'"> &nbsp;
         <input type="reset" value="작성취소">
         </th>
      </tr>
   </table>
   </form>
</div>
</body>
</html>