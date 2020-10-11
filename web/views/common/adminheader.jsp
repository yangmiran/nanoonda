<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="member.model.vo.Member"%>
 <%     
	Member loginMember = (Member)session.getAttribute("loginMember");
	                                  
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>


* {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
  
}

/* Style the top navigation bar */
.topnav {
  
  /* overflow: hidden; */
 /*  background-color: #333; */
 background-color: #2a3954;
  text-align:center
   
}

/* Style the topnav links */
.topnav a {
  /* float: left; */
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  width: 200px;
  margin-left: 600;
  
}

/* Change color on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav ul{
	/* display:inline-block ; */
}
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
}

/* Style the content */
.content {
  background-color: #ddd;
  padding: 10px;
  height: 400px; /* Should be removed. Only for demonstration */
}
 
</style>
<title>NANOONDA</title>
<script>
var ScrollTop = 0,
top = 15;
$(window).scroll(function(event) {
   var st = $(this).scrollTop();
   if (Math.abs(ScrollTop - st) <= top) return;
   if ((st > ScrollTop) && (ScrollTop > 0)) {
      $(".topnav").addClass("on");
   } else {
      $(".topnav").removeClass("on");
   }
   ScrollTop = st;
});
</script>
</head>
<body>
<div class="toplogo clear">
	<!-- 현재 로고만 넣어둔 상태, 이미지로 변경해야할지 결정할 것 -->
	<!-- <h1 id="logo">NANOONDA</h1> -->
	<div id="logo">
	<a href="/nnd/seinsert" ><img src="/nnd/resources/images/admin_logo.png" ></a>
	</div>
	<!-- 아이콘  -->
	<div id="adminicon">
		
		<p><%=loginMember.getnName()%>님 환영합니다.</p>
		<div id="logouticon">
			<a href="/nnd/logout">로그아웃</a>
		</div>
		<br>
		<br>
		<div id="adminmypage">
			<a href="/nnd/myinfo?id=<%=loginMember.getId()%>">my page  |</a>
			<a href="/nnd/calenderlist">userpage</a>
		</div>
	</div> <!-- 로그인 완료후 값 가져온 걸로 바꾸기 -->
</div>
<!--top 메뉴바-->
<div class="topnav">
<ul>
	<li><a href="/nnd/mlist">회원관리</a></li>
	<li><a href="/nnd/rlist.ad?page=1">신고관리</a></li>
	<li><a href="/nnd/anlist?page=1">공지사항관리</a></li>
	<li><a href="/nnd/aqlist">문의사항관리</a></li>
	<!-- <li><a href="/nnd/seinsert">통계관리</a></li> -->
</ul>
</div>

</body>
</html>