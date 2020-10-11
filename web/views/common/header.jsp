<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%
   Member loginMember = (Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA</title>
<script>
//상단 알림 오버시 컬러변경
$(document).ready(function(){
   $("#postbtn").mouseover(function(){
      $("#postbtn img").attr("src", "/nnd/resources/images/post_on.png");
   });
   $("#postbtn").mouseout(function(){
      $("#postbtn img").attr("src", "/nnd/resources/images/post_off.png");
   });
   
   $("#replybtn").mouseover(function(){
      $("#replybtn img").attr("src", "/nnd/resources/images/reply_on.png");
   });
   $("#replybtn").mouseout(function(){
      $("#replybtn img").attr("src", "/nnd/resources/images/reply_off.png");
   });
   
   //서브메뉴
   $("#headbar .headbar_menu #category ul li.sub").mouseover(function(){
      $(this).css('color', '#9e5bd8');
      $(this).css('font-weight','bold');
      $('#headbar .headbar_menu #category .sublist').show();
   });
    $("#headbar .headbar_menu #category ul li.sub").mouseout(function(){
      $(this).css('color', '#6f6f6f');
      $(this).css('font-weight','normal');
      $('#headbar .headbar_menu #category .sublist').hide();
   });
});
//스크롤시 메뉴 픽스
var ScrollTop = 0,
top = 15;
$(window).scroll(function(event) {
   var st = $(this).scrollTop();
   if (Math.abs(ScrollTop - st) <= top) return;
   if ((st > ScrollTop) && (ScrollTop > 0)) {
      $("#headbar .headbar_menu").addClass("on");
   } else {
      $("#headbar .headbar_menu").removeClass("on");
   }
   ScrollTop = st;
});
</script>
</head>
<body>
   <header id="headbar" align="center">
      <div id="hd_logo" align="center">
         <div class="alarm">
          <span class="btn" id="postbtn"><a href="/nnd/pnotread"><img src="/nnd/resources/images/post_off.png"></a></span>
            <span class="btn" id="replybtn"><a href="/nnd/rring?writer=<%= loginMember.getnName() %>"><img src="/nnd/resources/images/reply_off.png"></a></span>
         </div>
         <a href="/nnd/index.jsp" class="ct_logo"><img src = "/nnd/resources/images/logofix.png"></a>
         <div class="top_menu">
            <ul>
               <li class="name"><%= loginMember.getnName() %> 님</li>
               <li class="bar"><a href="/nnd/myinfo?id=<%= loginMember.getId() %>">mypage</a></li>
               <li><a href="/nnd/logout">logout</a></li>
            </ul>
         </div>
      </div>
      
      <div class="headbar_menu">
         <div class="menu_in clear">
            <div id="category">
               <ul>
                  <li><a href="/nnd/calenderlist">HOME</a></li>
                  <li><a href="/nnd/dlist?page=1">OPEN DIARY</a></li>
                  <li><a href="/nnd/mdlist?page=1">MY DIARY</a></li>
                  <li class="sub">
                     <a href="/nnd/views/post/postListView.jsp">POST</a>
                     <div class="sublist">
                        <ul>
                           <li><a href="/nnd/slist?page=1">보낸편지함</a></li>
                           <li><a href="/nnd/rlist?page=1">받은편지함</a></li>
                           <li><a href="/nnd/views/post/postWriteForm.jsp">친구에게 보내기</a></li>
                           <li><a href="/nnd/views/post/someOneWriteForm.jsp">누군가에게 보내기</a></li>
                           <li><a href="/nnd/ptlist?page=1">임시저장함</a></li>
                        </ul>
                     </div>
                  </li>
               </ul>
               
            </div>
            <div id="board">
               <ul>
                  <li><a href="/nnd/nlist">Notice</a></li>
                  <li><a href="/nnd/views/qna/qnaWriterForm.jsp">QnA</a></li>
               </ul>
            </div>
         </div>
      </div>
   </header>   
</body>
</html>