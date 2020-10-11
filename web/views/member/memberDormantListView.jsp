<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="member.model.vo.Member, java.util.ArrayList, java.sql.Date"%>
<%
	ArrayList<Member> list = (ArrayList<Member>) request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_INACTIVE MEMBER MANAGEMENT</title>
<%@ include file = "../../views/common/meta.jsp" %>
<script type="text/javascript">
	function allCk(objCheck) { //전체 선택 checkbox 클릭
		var checks = document.getElementsByName('id');
		for (var i = 0; i < checks.length; i++) {
			checks[i].checked = objCheck;
			// name이 'id' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
		}
	}
		
</script>
   
</head>
<body>
 <%@ include file="../common/adminheader.jsp"%> 
	  
	
	<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
	
	<h1 class="ad_pagetit"><span>Inactive Member Management</span>휴면회원관리</h1>
	<div id="adsubmenu">
		<ul>
			<li><a href="/nnd/mlist">회원목록</a></li>
	<!-- 		<li class="on"><a href="/nnd/mdorlist?page=1">휴면회원관리</a></li> -->
			<li><a href="views/member/memberLevelView.jsp">회원상태관리</a></li>
		</ul>
	</div>



<!-- 검색기능 -->
<div id="searchdormem">
	<form action="/nnd/msearchall.ad" method="post">
		<select name="select">
	       	<option value="1" selected="selected">아이디</option>
	      	<option value="2" >닉네임</option>        
	    </select>
		<input type="text" name="text1">
		<input type="hidden" name ="typest" value="dor">
		<input type="submit" value="검색" >
	</form>
</div>
	
<form action="/nnd/mdel.ad">

	<div id="deletebutton">	
		<input type="submit"  value="전체조회" onclick="javascript: form.action='/nnd/mdorlist?page=1';"/>	
	    <input type="submit" value="휴면회원 해제" onclick="javascript: form.action='/nnd/mdorupdate.ad';"/>
		<input type="submit"  value="휴면회원 탈퇴"> 	
	</div>

	<table align="center" class="w3-table-all w3-small serchlist">
		<tr>
			<th><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th>
			<th>아이디</th>
			<th>이메일</th>
			<th>닉네임</th>
			<th>가입날짜</th>
			<th>마지막수정일</th>
			<th>신고건수</th>
			<th>로그인제한</th>
			<th>회원상태</th>

		</tr>
		 <%
			for (Member m : list) {
		%>
		<tr>
			<td  align="center"><input type="checkbox" name="id" value="<%=m.getId()%>"></td>
			<td><%=m.getId()%></td>
			<td><%=m.getEmail()%></td>
			<td><%=m.getnName()%></td>
			<td><%=m.getEnrollDate()%></td>
			<td><%=m.getLastModified()%></td>
			<td><%=m.getDcCount()%></td>
			<td><%=m.getLoginLimit()%></td>
			<td>
			<%if(Integer.parseInt(m.getStatus())==1){//일반회원 %>
				<%="일반회원"%>
			<%}else if(Integer.parseInt(m.getStatus())==2){//최고관리자 %>
				<%="최고관리자" %>
			<%}else if(Integer.parseInt(m.getStatus())==3){//일반관리자 %>
				<%="일반관리자" %>
			<%}else{//휴면회원 %>
				<%="휴면회원" %>
			<%} %>
			</td>
		</tr>
		<% } %> 
	</table>
	</form>
<!-- 페이징 처리 -->

 <div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span class="ti-angle-double-left"></span>
		<% } else { %>
			<span><a href="/nnd/mdorlist?page=1" class="ti-angle-double-left"></a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span><font color="steelblue" size="4"><b><%= p %></b></font></span>
			<% } else { %>
				<span><a href="/nnd/mdorlist?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span class="ti-angle-double-right"></span>
		<% } else { %>
			<span><a href="/nnd/mdorlist?page=<%= maxPage %>" class="ti-angle-double-right"></a></span>
		<% } %>
	</div>  
</div> <!-- 전체 묶은 div : end -->	
</body>
</html>