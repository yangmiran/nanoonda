<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_MYPAGE</title>
<%@ include file="../../views/common/meta.jsp"%>
<script type="text/javascript">

	/* function popup(){
		var memberId = document.getElementById('memberId').value;
		var url = "views/member/secessionCheck.jsp";
	    var name = "popup test";
	    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
	    window.open(url, name, option);
	} */

	function memberdel(){
		location.href = "views/member/secessionCheck.jsp?id=<%= member.getId() %>";
	}
	
	function moveUpdateView(){
		//요청한 회원의 정보 수정페이지를 내보내는 서블릿을 요청함
		//다시 요청한 회원의 아이디를 전송해서 처리함
		location.href = "/nnd/passdate?id=<%= member.getId() %>";
	}
	
	<%-- function sendDelete(){
		//자바스크립트에서 서블릿 호출이나 다른 페이지 연결 처리할 때는
		//location 객체의 href 속성을 이용함. a 태그의 href 사용과 같은 의미임
		//전송방식은 get 방식임
		
		//회원 탈퇴처리 서블릿 요청함. userid 같이 전송함
		if (confirm("회원 탈퇴시 모든 게시물은 함께 삭제 됩니다. \n정말 탈퇴하시겠습니까?") == true){    //확인
			location.href = "/nnd/mdel?id=<%= member.getId() %>";

		 }else{   //취소

		     return false;

		 }
	} --%>
</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1 class="subtit"><span>My Page</span>마이 페이지</h1>
	<div id="wrap">
		<div class="my_page">
			<%@ include file="../common/mypageMenu.jsp" %>
			<div class="my_table">
				<table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
					<caption><%= member.getnName() %> 님의 회원정보 페이지입니다.</caption>
					<tr>
						<th width="220">아이디</th>
						<td><%= member.getId() %></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><%= member.getnName() %></td>
					</tr>
					<%-- <tr>
						<th>암 호</th>
						<td><input type="password" value="<%= member.getPwd() %>"></td>
					</tr>
					<tr>
						<th>암호 확인</th>
						<td><input type="password" value="<%= member.getPwd() %>"></td>
					</tr> --%>
					<tr>
						<th>이메일</th>
						<td><%= member.getEmail() %></td>
					</tr>
				</table>
			</div>
			<div class="btn_in">
				<button onclick="moveUpdateView();">수정페이지로 이동</button>
				<!-- <button onclick="sendDelete();">탈퇴하기</button> -->
				<button onclick="memberdel();" id="memberId">탈퇴하기</button>
			</div>
		</div>
	</div>
	
	<!-- 상대경로만 사용 가능함 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>