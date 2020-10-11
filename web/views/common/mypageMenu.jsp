<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_MYPAGE</title>
</head>
<body>
<div class="menu_box">
	<ul class="clear">
		<li>
			<a href="views/post/postListView.jsp">
				<img src = "/nnd/resources/images/iconmonstr-email.png">
				<span>post</span>
				<p>우편함</p>
			</a>
		</li>
		<li>
			<a href="/nnd/dlist?page=1">
				<img src = "/nnd/resources/images/iconmonstr-note.png">
				<span>open diary</span>
				<p>오픈일기장</p>
			</a>
		</li>
		<li class="on">
			<a href="/nnd/myinfo"> <!-- 나 자신 -->
				<img src = "/nnd/resources/images/iconmonstr-user.png">
				<span>mypage</span>
				<p>마이페이지</p>
			</a>
		</li>
		<li>
			<a href="/nnd/mdlist?page=1">
				<img src = "/nnd/resources/images/iconmonstr-notee.png">
				<span>my diary</span>
				<p>마이다이어리</p>
			</a>
		</li>
		<li>
			<a href="/nnd/qslist">
				<img src = "/nnd/resources/images/iconmonstr-speech.png">
				<span>qna</span>
				<p>문의하기</p>
			</a>
		</li>
		<li>
			<a href="/nnd/rmypage?page=1">
				<img src = "/nnd/resources/images/iconmonstr-info.png">
				<span>Report</span>
				<p>나의신고내역</p>
			</a>
		</li>
	</ul>
</div>
</body>
</html>