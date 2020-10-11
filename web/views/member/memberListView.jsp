<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="member.model.vo.Member, java.util.ArrayList, java.sql.Date"%>
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
<title>NANOONDA_MEMBER MANAGEMENT</title>
<style type="text/css">
form.sform {
	display: none;
	
}
</style>
<%@ include file = "../../views/common/meta.jsp" %>

<!-- 이거 있어야 됨 두줄 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script type = "text/javascript">
//jquery 로 이벤트 처리 : form 을 보이게 안보이게 처리함
$(function(){
	//작성된 이벤트 처리 코드는 실행 대기 상태가 됨
	$("input[name=item]").on("change", function(){ //5개의 input 태그에 on으로 이벤트 연결함
		    //5개 전부다 바뀌면 다음 function이 구동되라의 의미임 ~callback 함수라고함
		   //이벤트가 발생한 radio와 연결된 폼만 보이게 하고, 나머지 폼은 아보이게 처리함
		   $("input[name=item]").each(function(index){ // each() 루프 돌리기 ~(index)사용가능
			   //radio 하나씩 checked인지 확인하고
			   //해당 인덱스 순번의 radio가 체크상태인지 확인
			   //배열 0부터 checked 됬는지 확인  //라디오버튼 순서와 폼 순서를 맞춰 index로 연결
			   if($(this).is(":checked")){  
				   $("form.sform").eq(index).css("display","block");
			   }else{
				   $("form.sform").eq(index).css("display","none");
				  
			   }   
			   
		   }); 
	});   
	                    
});
</script>

</head>

<body>
	 <%@ include file="../common/adminheader.jsp"%> 
	  
	 


<div id="ad_listview"> <!-- 전체 묶은 div : start -->	

<h1 class="ad_pagetit"><span>Member Management</span>회원관리</h1>

<div id="adsubmenu">
	<ul>
		<li class="on"><a href="/nnd/mlist">회원목록</a></li>
		<!-- <li><a href="/nnd/mdorlist?page=1">휴면회원관리</a></li> -->
		<li><a href="views/member/memberLevelView.jsp">회원상태관리</a></li>
	</ul>
</div>
	
<form action="" class="ad_serch_in">
<!-- 2. 항목별 검색 기능 추가 -->
<fieldset id = "ss" class="ad_serch clear"> <!-- 항목들의 영역을 묶어주기 위해서 사용 -->
<legend>검색 분류</legend>
<span class="topserch">
	<input type="radio" name="item" id="uid"><label>회원 아이디</label>
	<input type="radio" name="item" id="unname"><label>닉네임</label>
	<input type="radio" name="item" id="uage"><label>가입날짜</label>
	&nbsp;&nbsp;<a href="/nnd/mlist">전체조회</a>
	<!-- <a href="/nnd/adeleteuser">탈퇴</a> -->
</span>
</fieldset>
</form>
<!-- 검색 기능 영역 끝 -->

<!-- 회원 아이디로 검색 폼 -->
<form action="/nnd/msearch" method="post" id="idform" class="sform ad_serch_in">
<input type="hidden" name="action" value="id"> 
<fieldset class="ad_serch clear">
<legend>아이디 검색</legend>
<span class="topserch">
	<input type="search" name="keyword">
	<input type="submit" value="검색">
</span>
</fieldset>
</form>
<!-- 닉네임 검색 폼 -->
<form action="/nnd/msearch" method="post" id="nnameform" class="sform ad_serch_in">
<input type="hidden" name="action" value="nname">
<fieldset class="ad_serch clear">
<legend>닉네임 검색</legend>
<span class="topserch">
	<input type="text" name="keyword" >
	<input type="submit" value="검색">
</span>
</fieldset>
</form>

<!-- 가입날짜 검색 폼 -->
<form action="/nnd/msearch" method="post" id="enrollform" class="sform ad_serch_in">
<input type="hidden" name="action" value="enrolldate">
<fieldset class="ad_serch clear">
<legend>가입날짜 검색</legend>
<span class="topserch">
	<input type="date" name="begin" > ~ &nbsp;
	<input type="date" name="end" >
	<input type="submit" value="검색">
</span>
</fieldset>
</form>


	<table align="center" class="w3-table-all w3-small serchlist">
		<tr>
			<th>아이디</th>
			<th>이메일</th>
			<th>닉네임</th>
			<th>가입날짜</th>
			<!-- <th>마지막수정일</th> -->
			<th>신고건수</th>
			<th>로그인제한</th>
			<th>회원상태</th>

		</tr>


		 <%
			for (Member m : list) {
		%>
		<tr>
			<td><%=m.getId()%></td>
			<td><%=m.getEmail()%></td>
			<td><%=m.getnName()%></td>
			<td><%=m.getEnrollDate()%></td>
			<%-- <td><%=m.getLastModified()%></td> --%>
			<td><%=m.getDcCount()%></td>
			<td><%=m.getLoginLimit()%></td>
			<td>
				<%if(Integer.parseInt(m.getStatus())==1){//일반회원%>
					<%="일반회원" %>
				<%}else if(Integer.parseInt(m.getStatus())==4){ //휴면회원%>
					<%="휴면회원" %>
				<%}else{ //관리자%>
					<%="관리자" %>
				<%} %>
			</td>
		</tr>
		<% } %> 
	</table>
	<br>
<!-- 페이징 처리 -->

 <div id="page" style="text-align:center;">
		<% if(currentPage <= 1){ %>
			<span class="ti-angle-double-left"></span>
		<% } else { %>
			<span><a href="/nnd/mlist?page=1" class="ti-angle-double-left"></a></span>
		<% } %>
	
		<!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
		<% for(int p = startPage; p <= endPage; p++){
			if(p == currentPage){ %>
				<span><font color="steelblue" size="4"><b><%= p %></b></font></span>
			<% } else { %>
				<span><a href="/nnd/mlist?page=<%= p %>"><%= p %></a></span>
			<% } %>
		<% } %>

		<% if(currentPage >= maxPage){ %>
			<span class="ti-angle-double-right"></span>
		<% } else { %>
			<span><a href="/nnd/mlist?page=<%= maxPage %>" class="ti-angle-double-right"></a></span>
		<% } %>
	</div> 
	
	

  </div> <!-- 전체 묶은 div : end -->
</body>
</html>