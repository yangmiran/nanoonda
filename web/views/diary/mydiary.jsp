<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.model.vo.Diary, java.util.ArrayList" %>
<%
	ArrayList<Diary> list = (ArrayList<Diary>)request.getAttribute("list");
	ArrayList<Diary> oplist = (ArrayList<Diary>)request.getAttribute("oplist");
	/* System.out.println(list+"====jsp+++++++++++++"); */
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_MYDIARY</title>
<%@ include file="../../views/common/meta.jsp"%>

<link href='/nnd/resources/css/calendar1.css' rel='stylesheet' />
<link href='/nnd/resources/css/calendar2.css' rel='stylesheet' />
<script src='/nnd/resources/js/calendar1.js'></script>
<script src='/nnd/resources/js/calendar2.js'></script>
<script src='/nnd/resources/js/calendar3.js'></script>

<script>
	
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list'],
      header: {
        left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay'
      },
      locale : "ko",
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      
      events: [
<%		
		 for(Diary d : list){
%>
	        {
	          url:'/nnd/ddetail?dno=<%= d.getDiaryNo() %>',
	          title:'<%= d.getDiaryTitle() %>',
	          start: '<%= d.getDiaryDate() %>',
	          color : "#6f6fff", 
	          textColor : "#ffffff"
	        },
<%
		}
%>	        
<%		
	for(Diary of : oplist){
%>
			{
	          url:'/nnd/mddetail?dno=<%= of.getDiaryNo() %>',
	          title:'<%= of.getDiaryTitle() %>',
	          start: '<%= of.getDiaryDate() %>',
	          color : "#ffac6f", 
	          textColor : "#ffffff"
	        },
<%
		}
%>

/*         {
          title: 'ㅇㅇㅇ',
          start: 'ㄹㄹㄹ'
        } */
     
      ]
    });

    calendar.render();
    
  });
	
</script>

</head>
<body>
	<%@ include file="../../views/common/header.jsp"%>
	<h1 class="subtit"><span>Diary</span>다이어리</h1>
	<section class="fullCalendar">
		<div id='calendar'>
			<ul class="calbtn">
				<li><button onclick="location.href='/nnd/dlist?page=1'">오픈다이어리 리스트</button></li>
				<li><button onclick="location.href='/nnd/mdlist?page=1'">마이다이어리 리스트</button></li>
			</ul>
		</div>
		
	</section>
	
	
	<!-- footer:s -->
	<%@ include file="../../views/common/footer.jsp"%>
	<!-- footer:e -->
</body>
</html>