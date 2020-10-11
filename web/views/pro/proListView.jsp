<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pro.model.vo.Pro, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Pro> list = (ArrayList<Pro>)request.getAttribute("list");
	//Pro pro = (Pro)request.getAttribute("pro");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int startPage =((Integer)request.getAttribute("startPage")).intValue();
	int endPage =((Integer)request.getAttribute("endPage")).intValue();
	int maxPage =((Integer)request.getAttribute("maxPage")).intValue();
	int currentPage =((Integer)request.getAttribute("currentPage")).intValue(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA PRO</title>
<%@ include file="../../views/common/meta.jsp"%>
<script type="text/javascript">
	function allCk(objCheck) { //전체 선택 checkbox 클릭
		var checks = document.getElementsByName('pno');
		for (var i = 0; i < checks.length; i++) {
			checks[i].checked = objCheck;
			// name이 'dno' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
		}
	}
		
</script>

</head>
<body>
<%@ include file ="../common/adminheader.jsp" %>
<%@ include file="../pro/proModalView.jsp" %> 

<div id="ad_listview"> <!-- 전체 묶은 div : start -->		 

<h1 class="ad_pagetit"><span>Pro Management</span>금지어 관리</h1>
<div id="adsubmenu">
	<ul>
		<li><a href="/nnd/rlist.ad?page=1">신고 목록</a></li> 
		<li><a href="/nnd//mblacklist">블랙리스트 관리</a></li> 
		<li class="on"><a href="/nnd/plist.ad?page=1">금지어 관리</a></li>
	</ul>
</div>



<!-- 검색 기능 -->
<div id="searchdormem">
<form action="/nnd/psearch.ad" method="post">
		<select name="select">
         <option value="1" selected="selected">금지어내용</option>    
       
		<input type="text" name="text1" >
		<input type="submit" value="검색"> 
		 </select>
</form>
</div>

<form action="">
	<div id="deletebutton">	
	<input type="submit" id="prolist" value="전체보기" onclick="location.href='/nnd/plist.ad?page=1'"/>
	<input type="button" id="proinsert" value="금지어등록"  onclick="document.getElementById('id01').style.display='block'"/> 
	<!-- <input type="submit" > -->
	
	

<!-- <input type="submit"  value="경고주기" style=" float: left; width: 90px; height: 30px; cursor:pointer" onclick="javascript: form.action='/nnd/rstcheck.ad';"/>	 -->
   <input type="submit" id="prodelete" value="삭제"  onclick="javascript: form.action='/nnd/pdelete.ad'"/> 

</div>


<table align="center" class="w3-table-all w3-small serchlist">
<tr>
<th><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th>								
<th>번호</th><th>금지어내용</th><th>작성자</th><th>등록일</th></tr>
<% for(Pro p : list){%>
<tr>
<td  align="center">
<input type="checkbox" name="pno" value="<%=p.getProNo()%>"></td>
<td align="center"><%= p.getProNo() %></td>
<td align="center"><%= p.getProContent() %></td>
<td align="center"><%= p.getAdminNname() %></td>
<td align="center"><%= p.getProDate() %></td>
</tr>
<%}//for each문 %>

</table>
</form>
<br>
<!-- 페이징 처리 -->
 <div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span class="ti-angle-double-left"></span>
		<% } else { %>
			<span><a href="/nnd/plist.ad?page=1" class="ti-angle-double-left"></a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span><font color="steelblue" size="4"><b><%= p %></b></font></span>
			<% } else { %>
				<span><a href="/nnd/plist.ad?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span class="ti-angle-double-right"></span>
		<% } else { %>
			<span><a href="/nnd/plist.ad?page=<%= maxPage %>" class="ti-angle-double-right"></a></span>
		<% } %>
	</div> 

  </div> <!-- 전체 묶은 div : end -->

</body>
</html>