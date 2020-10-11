<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	Member member = (Member)session.getAttribute("pwdMember");

	/*
		서블릿에서 session으로 받으면 위에도 session으로 처리
		HttpSession session = request.getSession();
		session.setAttribute("pwdMember", pwdMember);
		response.sendRedirect("/nnd/views/member/memberUpdatePage.jsp");
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_MYPAGE</title>
<%@ include file="../../views/common/meta.jsp"%>
<script>
//패스워드 확인
function validate(){
	var pwdValue = document.getElementById("pwd").value;
	var pwdCkValue = document.getElementById("pwdck").value;
	
	if(pwdValue !== pwdCkValue){
		alert("암호의 값이 일치하지 않습니다.");
		document.getElementById("pwdck").select();
		document.getElementById("pwd").select();
		return false;
	}
	return true;
}

</script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div id="wrap">
		<div class="my_page">
			<h1 align="center">회원 정보 수정 페이지</h1>
			<%@ include file="../common/mypageMenu.jsp" %>
			
			<div class="my_table update">
				<form method="post" action="/nnd/mupdate" onsubmit="return validate();">
					<table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
						<tr>
							<th width="220">아이디</th>
							<td><input type="text" name="id" value="<%= member.getId() %>" readonly></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><input type="text" name="userid" value="<%= member.getnName() %>" readonly></td>
						</tr>
						<tr>
							<th>새 비밀번호</th>
							<td><input type="password" name="pwd" id="pwd" value="" onfocus="this.value=''"></td>
						</tr>
						<tr>
							<th>새 비밀번호 확인</th>
							<td><input type="password" id="pwdck" value="" onfocus="this.value=''"></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><input type="email" name="email" value="<%= member.getEmail() %>" readonly></td>
						</tr>
					</table>
					<div class="btn_in">
						<input type="submit" value="수정하기">
						<input onclick="location.href='/nnd/myinfo?id=<%= loginMember.getId() %>';" type="reset" value="수정취소">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 상대경로만 사용 가능함 -->
	<%@ include file="../common/footer.jsp" %>
</body>
</html>