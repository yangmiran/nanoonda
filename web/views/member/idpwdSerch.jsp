<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_SERCH</title>
<%@ include file="../../views/common/meta.jsp"%>
<script>
//탭메뉴 스크립트
  $( function() {
	$( "#tabs" ).tabs();
  } );
  
/*  function serchok(){
	 location.href='history.go(-1)';
 } */
 
//이메일 중복체크
	function emailCheck(){
		$.ajax({
			url: "/nnd/emaildateCk",
			type: "post",
			data: {email: $("#pwdemail").val()},
			success:function(data){
				if(data == "ook"){
					alert("가입하지 않은 이메일입니다. \n 다시 입력해주세요.");
					//$("#pwd").focus();
					 $('.btnfindpWd').attr("type", "button");
					 $('.btnfindpWd').css("display", "none");
					 
				}else{
					alert("가입한 이메일입니다.");
					$('.btnfindpWd').attr("type", "submit");
					$('.btnfindpWd').css("display", "block");
				}
			},
			error: function(jqXHP, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});
		
		return false;
	}
 	
	 function emailreset() {
		 location="/nnd/passdateCk";
		 //$('#pwdemail').val('sksnsek20813ekd');
	 }
</script>
</head>
<body>
<div id="subWrap">
	<!--header:s-->
	<div class="title">
		<span class="tit_logo"><a href="/nnd/index.jsp"><img src="/nnd/resources/images/logofix.png"></a></span>
	</div>
	<!--header:e-->

	
	<section id="tabs" class="findWrap">
		<ul class="findTitle">
			<li><a href="#tabs-1">아이디 찾기</a></li>
			<li><a href="#tabs-2">비밀번호 찾기</a></li>
		</ul>
		<div id="tabs-1" class="findWrapIn">
			<strong>아이디찾기</strong>
			<div class="emailSerch">
				<h3>이메일 주소로 찾기</h3>
				<div class="cont">
					<div class="contTop">
						<form action="/nnd/searchid" method="post" class="nameform_wrap">
							<div class="pwdput">
								<h4>이메일 입력</h4>
								<p>회원가입시 인증된 이메일로 입력해 주세요.</p>
								<div class="fminput">
									<label for="email" class="blind">이메일</label>
									<input id="email" type="text" class="email" name="email">
								</div>
							</div>
							<!-- <div class="pwdput">
								<h4>비밀 번호 입력</h4>
								<p>비밀 번호를 입력해 주세요.</p>
								<div class="pwdinput">
									<label for="pwd" class="blind">비밀 번호</label>
									<input id="pwd" type="password" class="pwd" name="" value="" >
								</div>
							</div> -->
							<button type="submit" class="btnfindpWd">아이디 찾기</button>
						</form>
					</div>
					
				</div>
			</div>
		</div>

		<div id="tabs-2" class="findWrapIn">
         <strong>비밀번호 찾기</strong>
         <div class="emailSerch">
            <h3>이메일 주소로 찾기</h3>
            <div class="cont">
               <div class="contTop">
                  <h4>이메일 주소 입력</h4>
                  <p>회원가입시 인증된 아이디 / 이메일로 입력해 주세요.</p>
                  <form action="/nnd/mresetpwd" class="emailform_wrap" method="post">
                  <!-- <form action="/nnd/views/email/searchPw.jsp" class="emailform_wrap" method="post"> -->
                     <legend class="blind">아이디 입력</legend>
                     <div class="fminput">
                        <label for="findEmail" class="blind">아이디</label>
                        <input id="" type="text" class="findEmail" name="id" placeholder="아이디를 입력해주세요">
                        <!-- <input type="button" id="emailckbtn" value="중복체크" onclick=""></td> -->
                     </div>
                     
                     <legend class="blind">이메일 주소 입력</legend>
                     <div class="fminput">
                        <label for="findEmail" class="blind">이메일 주소</label>
                        <input id="pwdemail" type="text" class="findEmail" name="email" placeholder="이메일을 입력해주세요">
                        <!-- <input type="button" id="emailckbtn" value="중복체크" onclick="return emailCheck();"> --></td>
                     </div>
                     
                     <div class="txtBox">
                        <span>
                           비밀번호는 회원가입시 가입하신 이메일로 보내드립니다.<br>
                           이메일이 생각나지 않을 경우 문의 주세요
                        </span>
                     </div>
                     <button type="submit" class="btnfindpWd" >비밀번호 찾기</button>
                  </form>
               </div>
            </div>
         </div>
      </div>
	</section>
	<!--findWrap:e-->
</div>
<!--subWrap:e-->
</body>
</html>