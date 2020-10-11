<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, java.util.ArrayList"%>
<% 
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_STATUS MANAGEMENT</title>
<%@ include file = "../../views/common/meta.jsp" %>
<!-- 관리자 권한 설정 -->
</head>
<body>
 <%@ include file="../common/adminheader.jsp"%>


<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
	
	<h1 class="ad_pagetit"><span>Member Status Management</span>회원상태관리</h1>
	<div id="adsubmenu">
		<ul>
			<li><a href="/nnd/mlist">회원목록</a></li>
		<!-- 	<li><a href="/nnd/mdorlist?page=1">휴면회원관리</a></li> -->
			<li class="on"><a href="views/member/memberLevelView.jsp">회원상태관리</a></li>
		</ul>
	</div>

<!-- 회원 닉네임으로 검색 -->

<form action="/nnd/amlevel" method="post" id="idform" class="adserchone">
<input type="hidden" name="action" value="id">
<fieldset>
<legend>검색할 닉네임을 입력하세요</legend>
<span class="topserch">
<input type="search" name="keyword">
<input type="submit" value="검색">
</span>
</fieldset>
</form>

<div class="levellist w3-table-all w3-small" >
	<% if(member != null){ %>
	<form action="/nnd/amchange">
	<table align="center" cellspacing="0" cellpadding="3">
	<tr>
	<th>아이디</th>
	<th>회원등급</th>
	<th>변경등급</th>
	<tr>
	<td><input type="text" name="nname" value="<%= member.getnName() %>" readonly></td>
	<td><input type="text" 
	<%if(member.getStatus().equals("1")){%>
		value="일반회원"
	<% }else if(member.getStatus().equals("2")){%>
		value="최고관리자"
	<%}else if(member.getStatus().equals("3")) {%>
		value="일반관리자"
	<%}else{ %>
		value="휴면회원"
	<%} %>  readonly></td>
	<td>
	<input type="radio" name="status" value="1">일반
	<input type="radio" name="status" value="2">관리자
	<input type="radio" name="status" value="3">최고관리자
	</td>
	</table>
	<input type="submit" value="등급변경">
	</form>
	<% } else {  }%>
	</div>
</div>
</body>
</html>