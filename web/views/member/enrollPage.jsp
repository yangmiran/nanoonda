<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	Member member = (Member)request.getAttribute("member");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_SIGNUP</title>
<%@ include file="../../views/common/meta.jsp"%>

<script>
	//패스워드 확인
	function validate(){
		var pwdValue = document.getElementById("pwd").value;
		var pwdCkValue = document.getElementById("pwdck").value;
		if(pwdValue !== pwdCkValue){
			alert("암호의 값이 일치하지 않습니다.");
			document.getElementById("pwd").select();
			return false;
		}
		return true;
	}
	
	//아이디 중복 체크 확인
	function dupIdCheck(){
		$.ajax({
			url:"/nnd/idchk",
			type:"post",
			data: {id: $("#id").val()},
			success:function(data){
				if(data == "ok"){
					alert("사용 가능한 아이디입니다.");
					$("#nname").focus();
				}else{
					alert("이미 사용중인 아이디입니다.\n다시 입력하세요.");
					$("#id").select();
				}
			},
			error: function(jqXHP, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});
		
		return false;
	}
	
	//닉네임 중복체크
	function nnameCheck(){
		$.ajax({
			url: "/nnd/nnchk",
			type: "post",
			data: {nname: $("#nname").val()},
			success:function(data){
				if(data == "ok"){
					alert("사용 가능한 닉네임입니다.");
					$("#pwd").focus();
				}else{
					alert("이미 사용중인 닉네임입니다.\n다시 입력하세요.");
					$("#nname").select();
				}
			},
			error: function(jqXHP, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});
		
		return false;
	}
	
	//이메일 중복체크
	/* function emailCheck(){
		$.ajax({
			url: "/nnd/emaildateCk",
			type: "post",
			data: {email: $("#email").val()},
			success:function(data){
				if(data == "ook"){
					alert("가입 가능한 이메일입니다.");
					$("#email").focus();
					 
				}else{
					alert("이미 사용중인 이메일입니다.");
				}
			},
			error: function(jqXHP, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});
		
		return false;
	} */
	
	function resetCheck(){
		$.ajax({
			url: "/nnd/enrolleset",
			type: "post",
			data: {resetpass: $("#resetpass").val()},
			success:function(data){
				if(data == "enok"){
					alert("인증번호가 틀렸습니다. 다시 입력해주세요");
					$("#resetpass").focus();
					$('.byenrollok').attr("type", "button");
					$('.byenrollok').prop('disabled', true);
					 
				}else{
					alert("인증되었습니다.");
					$("#resetpass").focus();
					$('.byenrollok').attr("type", "submit");
					$('.byenrollok').prop('disabled', false);
				}
			},
			error: function(jqXHP, textstatus, errorthrown){
				console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
			}
		});
		
		return false;
		
	}

	/* function emailSend(){
		let clientEmail = ducument.getElementById('emailText').value;
		let emailYn = isEmail(clientEmail);
		
		closole.log('입력 이메일' + clientEmail);
		
		if(emailYn == true){
			alert("이메일 형식입니다.");
			
			$.ajax({
				type:"post",
				url:"/nnd/member/email",
				data:{userEmail:clientEmail},
				success:function(data){
					
				},error:function(e){
					alert('오류입니다. 잠시 후 다시 시도해주세요');
				}
			});
		}else{
			alert('이메일 형식에 알맞게 입력해주세요 xxx@xxx.com');
		}
	}
	
	function isEmail(asValue){
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return regExp.test(asValue);
	} */
</script>
</head>
<body>
	<div id="container" class="enrollpage">
		<!-- enrollpage_in:s -->
		<div class="enrollpage_in">
			<div class="title">
				<span class="tit_logo"><a href="/nnd/index.jsp"><img src="/nnd/resources/images/logofix.png"></a></span>
				<h1>나눈다 회원가입을 시작합니다!</h1>
				<p>이메일 인증을 해주셔야 가입이 가능합니다.<br>인증을 꼭 해주세요</p>
			</div>
			<form method="post" action="/nnd/minsert" onsubmit="return validate();">
				<table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" id="id" required autofocus> &nbsp;
					<input type="button" value="중복체크" onclick="return dupIdCheck();"></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" name="nname" id="nname" required> &nbsp;
					<input type="button" value="중복체크" onclick="return nnameCheck();"></td>
				</tr>
				<tr>
					<th>암 호</th>
					<td><input type="password" name="pwd" id="pwd" required></td>
				</tr>
				<tr>
					<th>암호확인</th>
					<td><input type="password" id="pwdck"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<ul class="emailcklist">
							<li>
								<input type="text" name="email" id="email" placeholder="가입할 이메일을 다시 입력해주세요." required>
								
							</li>
							<li>
								<!-- <input type="button" value="중복체크" onclick="return emailCheck();"></td> -->
								<input type="text" id="resetpass" placeholder="인증번호를 입력해주세요">
								<input type="button" onclick="return resetCheck()" value='인증확인'>
							</li>
	            		</ul>
						
						<!-- <button type="submit" id="emailCheck">인증번호받기</button> -->
					</td>
				</tr>
				
				<tr class="enrolljoin">
					<th>가입경로</th>
					<td>
						<select name="sellist">
							<option value="1">지인추천</option>
							<option value="2">인터넷검색</option>
							<option value="3">각종매체광고</option>
							<option value="4">기존사용</option>
							<option value="5">기타</option>
						</select>
					</td>
				</tr>
	
				<tr>
					<th colspan="2">
						<button class="byenrollok" type="button"disabled>가입하기</button>
					</th>
				</tr>
				</table>
			</form>
			
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
			<span class="copyright">© <strong>NANOONDA Communications</strong></span>
		</div>
		<!-- enrollpage_in:e -->
		
	</div>
</body>
</html>