<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NANOONDA</title>
<%@ include file = "../../views/common/meta.jsp"%>
</head>
<script>
function emailCheck(){
	$.ajax({
		url: "/nnd/emaildateCk",
		type: "post",
		data: {email: $("#email").val()},
		success:function(data){
			if(data == "ook"){
				alert("가입 가능한 이메일입니다.");
				$("#email").focus();
				$('.byenrollok').attr("type", "submit");
				//$('.byenrollok').prop('disabled', true);
			}else{
				alert("이미 사용중인 이메일입니다.");
				$('.byenrollok').attr("type", "button");
				//$('.byenrollok').prop('disabled', false);
			}
		},
		error: function(jqXHP, textstatus, errorthrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
		}
	});
	
	return false;
}


</script>
<body>
	<div id="container" class="enrollpage sub">
	    <div class="enrollpage_in">
	    	<div class="title">
				<span class="tit_logo"><a href="/nnd/index.jsp"><img src="/nnd/resources/images/logofix.png"></a></span>
				<h1>나눈다 회원가입을 시작합니다!</h1>
				<p>이메일 인증을 해주셔야 가입이 가능합니다.<br>인증을 꼭 해주세요</p>
			</div>
	        <form action="/nnd/views/email/enroll.jsp" method="get">
	            <div class="numbernroll">
	            	<input type="text" name="email" id="email" placeholder="가입할 이메일을 입력해주세요" value="" required>
	            	<p class="none">이메일을 보내는 중입니다. 잠시만 기다려 주세요.</p>
	            	<!-- <button type="button" id="emailCheck" onclick='resetCheck()'>중복체크</button> -->
	            	<button type="button" onclick="return emailCheck();">중복체크</button>
	            </div>
	             <div class="byenroll">
	                <button class="byenrollok" type="button">인증메일 보내기</button>
	            </div>
	        </form>
	        
	    </div>
    </div>
</body>


</html>