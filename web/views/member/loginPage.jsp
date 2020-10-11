<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_LOGIN</title>
<%@ include file="../../views/common/meta.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<!--mainWrap:s-->
	<div id="mainWrap">
		<div class="main_bgWrap">
			<img src="/nnd/resources/images/main.jpg" alt="배경이미지" id="random_img">
		</div>
		
		<!--main:s-->
		<main id="loginWrap">
			<div class="bg_login_wrap">
				<img src="/nnd/resources/images/logofix.png">
				<h2>당신과 함께라서<br>봄이 왔어요</h2>
				<form action="/nnd/login" method="post" class="loginform_wrap">
					<legend class="blind">로그인입력폼</legend>
					<div class="loginidWrap">
						<label for="loginNumber" class="blind">아이디</label> 
						<input id="loginNumber" type="text" class="loginNumber" name="id" value="" placeholder="나눈다 아이디" autofocus>
					</div>

					<div class="loginPwWrap">
						<label for="loginPw" class="blind">비밀번호</label> 
						<input type="password" id="loginPw" class="loginPw" name="pwd" placeholder="비밀번호">
					</div>

					<p class="login_error">아이디를 입력해주세요.</p>
					<div class="submitBtn">
						<button type="submit" class="btn_login">로그인</button>
					</div>
					
					<!-- <div class="social_login">
						<div class="list">
							<ul>
								<li><div class="icon google"></div></li>
								<li><div class="icon face"></div></li>
								<li><div class="icon naver"></div></li>
								<li><div class="icon kaka"></div></li>
							</ul>
						</div>
					</div> -->
					
					<div class="loginSerch">
						<ul>
							<li><a href="idpwdSerch.jsp#tabs-1" target="_black">아이디 찾기</a></li>
							<li><a href="idpwdSerch.jsp#tabs-2" target="_black">비밀번호 찾기</a></li>
							<li><a href="/nnd/views/email/emailok.jsp">회원가입</a></li>
						</ul>
					</div>
				</form>
			</div>
		</main>
		<!--main:e-->
		<!-- footer:s -->
		<div id="main_footer">
			<div class="footer_list">
				<a class="link_info" href="/nnd/index.jsp" target="_blank">NANOONDA</a>
			</div>
			<div class="footer_copy">
				<small class="copyright">COPYRIGHT 2020 NANOONDA CO. LTD. <br> ALL RIGHTS RESERVED.</small>
			  </div>
		</div>
		<!-- footer:e -->
	</div>
	<!--mainWrap:e-->
	<script>
		//새로고침 할때마다 이미지 변경(메인 이미지)
		function change_img(){
			var random_img=new Array();
			random_img[0]="/nnd/resources/images/main.jpg";
			random_img[1]="/nnd/resources/images/main111.jpg";
			var random_img_src=Math.floor(Math.random()*(random_img.length));
			document.getElementById("random_img").src=random_img[random_img_src];
		}
		change_img();
	</script>

	<script>
		//로그인 alert
		$(document).ready(function(){
			$(".btn_login").click(function(){
				var text = $("#loginNumber").val();
			
				if($("#loginNumber").val()==""){
					$(".login_error").fadeIn(500);
					$(".login_error").text("아이디를 입력해주세요");
					$("#loginNumber").focus();
				}else if ($("#loginPw").val()==""){
					$(".login_error").fadeIn(500);
					$(".login_error").text("비밀번호를 입력해주세요.");
					$("#loginPw").focus();
				}else {
					$(".login_error").fadeIn(500);
					$(".login_error").text("로그인되었습니다.");
				}
			});
		});
	</script>
</body>
</html>