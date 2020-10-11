<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.model.vo.Diary, java.util.ArrayList, java.sql.Date" %>
<%
	ArrayList<Diary> list = (ArrayList<Diary>)request.getAttribute("list");
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
<title>NANOONDA_OPENDIARY</title>
<%@ include file="../../views/common/meta.jsp"%>
<script type="text/javascript">
	function allCk(objCheck){ //전체 선택 checkbox 클릭
	  var checks = document.getElementsByName('dno');
	  for( var i = 0; i < checks.length; i++ ){
	   checks[i].checked = objCheck;
		// name이 'dno' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
	  }
	 }
</script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<h1 class="subtit"><span>Temporary Saved Diary</span>임시 저장 일기 목록</h1>
	<div class="writecon">
	<div id="tempcon">
	<form method="post" action="/nnd/tddel">
	<input type="hidden" name="writer" value="<%= loginMember.getnName() %>">
	<table class="dt" id="temp" align="center">
	<colgroup>
		<col width="10%" />
		<col width="70%" />
		<col width="20%" />
		<col width="*" />
	</colgroup>
    <thead>
    	<tr>
      	  <th><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th><th>제목</th><th>날짜</th>
   	 	</tr>
   	 </thead>
    <tbody>
    <% for(Diary d : list){ %>
		<tr>
			<td><input type="checkbox" name="dno" value="<%= d.getDiaryNo() %>" ></td>
			<td><a href="/nnd/tupview?dno=<%= d.getDiaryNo() %>&page=1"><%= d.getDiaryTitle() %></a>
				<% if(d.getDiaryOriginfile()!=null){ %>
				<span class="ti-image" style="margin-left:5px;"></span><% } %>
			</td>
			<td><%= d.getDiaryDate() %></td>
		</tr>
	<% } %>
		<tr>			            
            <td colspan="4" class="button" align="center">
            	<input type="submit" value="삭제" class="bottomb">
                <input type="button" value="뒤로" class="bottomb" onclick="javascript:location.href='views/diary/diaryWriteForm.jsp'; return false;">
            </td>
		</tr>  
    </tbody>
	</table>
	</form>
	<br>	
	<!-- 페이징 처리 -->
	<div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span class="ti-angle-double-left"></span>
		<% } else { %>
			<span><a href="/nnd/tlist?page=1" class="ti-angle-double-left"></a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span class="number"><font><b><%= p %></b></font></span>
			<% } else { %>
				<span class="number"><a href="/nnd/tlist?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span class="ti-angle-double-right"></span>
		<% } else { %>
			<span><a href="/nnd/tlist?page=<%= maxPage %>" class="ti-angle-double-right"></a></span>
		<% } %>
	</div>
	</div>
	</div>
	<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>