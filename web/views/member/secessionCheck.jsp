<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	String memberId = request.getParameter("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_SECESSION</title>
<%@ include file="../../views/common/meta.jsp"%>
<script>
 function sendDelete(){
	//자바스크립트에서 서블릿 호출이나 다른 페이지 연결 처리할 때는
	//location 객체의 href 속성을 이용함. a 태그의 href 사용과 같은 의미임
	//전송방식은 get 방식임
	
	//회원 탈퇴처리 서블릿 요청함. userid 같이 전송함
	<%-- if (confirm("회원 탈퇴시 모든 게시물은 함께 삭제 됩니다. \n정말 탈퇴하시겠습니까?") == true){    //확인
		/* var id = document.getElementById('memberIdck').value;
		location.href = "/nnd/mdel" */
		location.href = "/nnd/mdel?id=<%= memberId %>";

	 }else{   //취소

	     return false;

	 } --%>
}
</script>

</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div id="wrap">
		<div class="secessionCk">
		<span class="pop_logo"><img src="/nnd/resources/images/logofix.png"></span>
			<h1>탈퇴사유를 체크해주세요</h1>
			<p>탈퇴 하시게 되면 모든 글은 함께 삭제됩니다.</p>
			<div class="ck_list">
			<form action="/nnd/mdel">
				<input type="hidden" name ="id" value="<%= loginMember.getId() %>">
				<select name="seck">
					<option value="1">서비스불만</option>
					<option value="2">컨텐츠부족</option>
					<option value="3">이용 안함</option>
					<option value="4">기타</option>
				</select>
				<div class="btn_in">
					<button type="button" onClick="javascript:location.href='/nnd/myinfo?id=<%= loginMember.getId() %>'">취소</button>
					<input type="submit" value="탈퇴하기">
				</div>
			</form>	
			<!-- <form action="/nnd/mdel">
				<table>
					<tr>
						<td><label><input type="radio" checked name="seCk" value="1">서비스불만</label></td>
						<td><label><input type="radio" name="seCk" value="2">컨텐츠부족</label></td>
						<td><label><input type="radio" name="seCk" value="3">이용 안함</label></td>
						<td><label><input type="radio" name="seCk" value="4">기타</label></td>
					</tr>
				</table>
			</form>
			</div> -->
		</div>
	</div>
</body>
</html>