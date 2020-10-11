<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<!-- 관리자 회원정보 수정 페이지 -->
<meta charset="UTF-8">
<title>NANOONDA_LOGIN</title>
<%@ include file="../../views/common/meta.jsp"%>
<%@ include file="../common/adminheader.jsp" %>
</head>
<body>
<div id="ad_listview"> <!-- 전체 묶은 div : start -->	
<h1 class="ad_pagetit"><span>My Page</span>회원정보 수정페이지</h1>	
	<div id="wrap">
		<div class="my_page">
			
			<div class="my_table update">
				<form method="post" action="/nnd/passdateCk" onsubmit="return">
					<table id="outer" align="center" width="500" cellspacing="5" cellpadding="0" class="w3-table-all w3-small">
						<caption>회원 정보를 수정하시려면 현재 비밀번호를 확인해주세요.</caption>
						<tr>
							<th width="220">아이디</th>
							<td><%= loginMember.getId() %></td>
						</tr>

						<tr>
							<th>현재 비밀번호</th>
							<td><input style="border: 1px solid #d1d1d1" type="password" name="pwd" id="pwd"></td>
						</tr>
					</table>
					<div class="btn_in">
						<input type="submit" value="확인">
						<input onclick="location.href='/nnd/myinfo?id=<%= loginMember.getId() %>';" type="reset" value="취소">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>