<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA</title>
<%@ include file="views/common/meta.jsp"%>

</head>
<body>
	<!-- main_header:s -->
	<header id="main_header">
		<div class="hd_fix">
			<div class="logo">
				<a href="index.jsp"><img src="resources/images/logo2.png" id="main_bg"></a>
			</div>
			<div class="main_menu">
				<ul>
					<li><a href="views/member/loginPage.jsp">로그인</a></li>
					<li><a href="views/email/emailok.jsp">회원가입</a></li>
					<li><a href="#main_page">이용방법</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!-- main_header:e -->

	<!-- container:s -->
	<div id="container">
		<!-- main:s -->
		<section id="main">
			<div class="main_img">
				<img src="resources/images/main1.png">
			</div>
			<div id="main_page" class="main_page">
				<img src="resources/images/mainpage.jpg">
			</div>
		</section>
		<!-- main:e -->
		
		
	</div>
	<!-- container:e -->

	<!-- footer:s -->
	<%@ include file="views/common/footer.jsp"%>
	<!-- footer:e -->

	<!-- top:s -->
	<div class="top">
		<span><a href="#"><img src="resources/images/ico_top.png"></a></span>
	</div>
	<!-- top:e -->
</body>
</html>