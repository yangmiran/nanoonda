<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, notice.model.vo.Notice"%>
<%
	ArrayList<Notice> list = (ArrayList<Notice>) request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_NOTICE</title>
<!--  관리자가 보는  공지사항 목록 -->
<%@ include file="../../views/common/meta.jsp"%>
<style>
.noticewritebtn{
    display: block;
    text-align: right;
}
.noticewritebtn a{
cursor: pointer;
    background: #1b87d4;
    color: #fff;
    padding: 5px 10px;
    border: 0;
    height: 40px;
    font-size: 15px;
}
#ad_listview table.serchlist {margin-top: 20px;}
</style>
</head>
<body>
	<%@ include file="../common/adminheader.jsp"%>

<!-- 여기서 부터 -->
	
	<script type="text/javascript">
		function deleteRow(ths) {
			var $ths = $(ths);
				$ths.parents("tr").remove();
		}
	</script>
<!-- 여기까지 -->	
	
<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
	
	<h1 class="ad_pagetit"><span>Notice Management</span>공지사항 관리</h1>


<span class="noticewritebtn"><a href="/nnd/views/notice/noticeWriterForm.jsp">공지글 등록</a></span>
</fieldset>
</form>
<!-- 검색 기능 영역 끝 -->	
	<table align="center" class="w3-table-all w3-small serchlist">
		<tr>
			<th width="150">글번호</th>
			<th width="*">글제목</th>
			<th width="200">등록일</th>
			<th width="300">작성자</th>
		</tr>
		<%
			for (Notice n : list) {
		%>
		<tr>
			<td><%=n.getNoticeNo()%></td>
			<td><a href="/nnd/andetail?notice_no=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle()%></a></td>
			<td><%=n.getNoticeDate()%></td>
			<td><%=n.getNname()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<!-- 페이징 처리 -->

<%-- <div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span>◀◀</span>
		<% } else { %>
			<span><a href="/nnd/rlist.ad?page=1">◀◀</a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span><font color="steelblue" size="4"><b><%= p %></b></font></span>
			<% } else { %>
				<span><a href="/nnd/rlist.ad?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span>▶▶</span>
		<% } else { %>
			<span><a href="/nnd/rlist.ad?page=<%= maxPage %>">▶▶</a></span>
		<% } %>
	</div> --%>
</div> <!-- 전체 묶은 div : end -->
</body>
</html>