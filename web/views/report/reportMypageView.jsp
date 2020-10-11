<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="report.model.vo.Report, java.util.ArrayList, java.sql.Date"%>
<%
	ArrayList<Report> list = (ArrayList<Report>) request.getAttribute("list");
	int listCount = ((Integer) request.getAttribute("listCount")).intValue();
	int startPage = ((Integer) request.getAttribute("startPage")).intValue();
	int endPage = ((Integer) request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer) request.getAttribute("maxPage")).intValue();
	int currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
	int reportcount = ((Integer) request.getAttribute("reportcount")).intValue();
%>
<!DOCTYPE html>
<html>
<title>NANOONDA_MYPAGE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ include file="../../views/common/meta.jsp"%>

<script type="text/javascript">
	function allCk(objCheck) { //전체 선택 checkbox 클릭
		var checks = document.getElementsByName('rno');
		for (var i = 0; i < checks.length; i++) {
			checks[i].checked = objCheck;
			// name이 'rno' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
		}
	}
		
</script>

<style> 
	div#deletebutton {float: none;
    text-align: right;
    padding-bottom: 0;}
	div#deletebutton input {cursor: pointer;
    background: #4f4f4f;
    color: #fff;
    height: 30px;
    font-size: 9pt;
    padding: 5px 10px;} 
</style>

<body>
	<%@ include file="../common/header.jsp"%>
	<h1 class="subtit"><span>My Report</span>신고 내역</h1>
	<div id="wrap">
		<div class="my_page">
			<%@ include file="../common/mypageMenu.jsp"%>
			<div class="my_table" >
				<caption> 내가 받은 신고 건수 : <%=reportcount %>건 </caption>
				
				<hr>
				<caption> 나의 신고 내역 </caption>
				<p>불편을 끼쳐드려서 죄송합니다. 신속하게 처리하겠습니다.</p>
			
				<table>
						<form action="/nnd/rdelete.u">
							
							<div id="deletebutton">
							<pre><input type="submit"  value="삭제" style=" width:50px; height:40px" > 
							</div>
					<thead>
								<tr align="center">
									<th><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th>
									</pre>
									<th>제목</th>
									<th>신고접수일</th>
									<th>신고처리일</th>
									<th>처리상태</th>
								</tr>
					</thead>
					<tbody>
						
						<%
							for (Report r : list) {
						%>
						<tr>
							<td  align="center"><input type="checkbox" name="rno" value="<%=r.getReportNo()%>"></td>
							<td><a href="/nnd/rdetail.u?rno=<%=r.getReportNo()%>"><%=r.getReportTiltle()%></a></td>
							<td><%=r.getReportRegDate()%></td>
							<td>
								<% if (r.getReportComDate() != null) {%> 
										<%=r.getReportComDate()%>
								<%} else {%> 
										&nbsp; 
								<%}%>
							</td>
							<td>
								<% if (Integer.parseInt(r.getReportStatus()) == 0) {%> 
									<%="미처리"%> 
								<%} else if (Integer.parseInt(r.getReportStatus()) == 1) {%>
									<%="처리"%> 
								<%} else {%>
									 <%="처리중"%> 
								<%}%>
							</td>
						</tr>
						<%}%>
					</tbody>
				</table>
				</form>

	<!-- 페이징 처리 -->

	<div id="page" style="text-align: center; width: 90%; float: right">
		<% if (currentPage <= 1) {//맨 첫 페이지로 이동%>
			<span class="ti-angle-double-left"></span>  <!-- 1페이지에 있으면 이동 X -->
		<%} else {%>
			<a href="/nnd/rmypage?page=1" class="ti-angle-double-left"></a> <!-- 1페이지아니면 1페이지로 이동 O -->
		<%}%>

		<!-- 이전 그룹으로 이동 처리 -->
		<%if ((currentPage - 10) < startPage && (currentPage - 10) > 1) {%>
			<a href="/nnd/rmypage?page=<%=startPage - 10%>">[이전그룹]</a>
		<%} else {%>
			<span class="ti-angle-left"></span>
		<%}%>

		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for (int p = startPage; p <= endPage; p++) {
			if (p == currentPage) {%>
				<span class="number"><font><b><%=p%></b></font></span>
			<%} else {%>
				<span class="number"><a href="/nnd/rmypage?page=<%=p%>"><%=p%></a></span>
			<%} //else 닫기%>

		<%} //for 닫기%>

		<!-- 다음 그룹으로 이동 처리 -->
		<%if ((currentPage + 10) > endPage && (currentPage + 10) < maxPage) {%>
			<a href="/nnd/rmypage?page=<%=endPage + 10%>" class="ti-angle-right"></a>
		<%} else {%>
			<span class="ti-angle-right"></span>
		<%}%>
		
		<% if (currentPage >= maxPage) {//맨 첫 페이지로 이동%>
			<span class="ti-angle-double-right"></span> <!-- 맨끝 페이지에 있으면 이동 X -->
		<%}else{ %>
			<a href="/nnd/rmypage?page=<%=maxPage %>" class="ti-angle-double-right"></a> <!-- 맨끝 페이지아니면 맨끝 페이지로이동 O -->
		<%} %>
			</div>
				</div>
				</div>
				</div>
				 <%@ include file="../common/footer.jsp" %> 
</body>
</html>