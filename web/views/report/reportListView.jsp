<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="report.model.vo.Report, java.util.ArrayList, java.sql.Date" %>
<%
   ArrayList<Report> list = (ArrayList<Report>)request.getAttribute("list");
    int listCount = ((Integer)request.getAttribute("listCount")).intValue();
   int startPage =((Integer)request.getAttribute("startPage")).intValue();
   int endPage =((Integer)request.getAttribute("endPage")).intValue();
   int maxPage =((Integer)request.getAttribute("maxPage")).intValue();
   int currentPage =((Integer)request.getAttribute("currentPage")).intValue(); 
%>
<!DOCTYPE html>
<html>
<head>
<!-- 관리자용 신고게시판 글 보기 (모든 유저의 신고글이 보이는 페이지) -->
<meta charset="UTF-8">
<title>NANOONDA REPORT</title>
<%@ include file="../../views/common/meta.jsp"%>
<script type="text/javascript">
   function allCk(objCheck) { //전체 선택 checkbox 클릭
      var checks = document.getElementsByName('rno');
      for (var i = 0; i < checks.length; i++) {
         checks[i].checked = objCheck;
         // name이 'rno' 인 checkbox는  id가 allck인 checkbox의 checked 상태와 같게 된다. 
      }
   }
      
</script>

</head>
<body>
<%@ include file ="../common/adminheader.jsp" %> 


   
<div id="ad_listview"> <!-- 전체 묶은 div : start -->   

<h1 class="ad_pagetit"><span>Report Management</span>신고관리</h1>    

<div id="adsubmenu">
   <ul>
      <li class="on"><a href="/nnd/rlist.ad?page=1">신고 목록</a></li> 
       <li><a href="/nnd//mblacklist">블랙리스트 관리</a></li>
       <li><a href="/nnd/plist.ad?page=1">금지어 관리</a></li>
   </ul>
</div>
   
<!-- 검색 기능 -->
<div id="searchdormem">
<form action="/nnd/rsearch.ad" method="post">
      <!-- <select name="select">
            <option value="1" selected="selected">신고자닉네임</option>
           <option value="2" >신고받은자닉네임</option>   
           <option value="3" >신고제목+내용</option>         
        </select>
         <input type="text" name="text1">
          <input type="hidden" name ="typest" value="dor">
          <input type="submit" value="검색">  -->
</form>
</div>
<!-- <form action="/nnd/rtypeselect">
<div id="type">
   <input type="radio" name="type" value="미처리" style="float: left; width: 90px; height: 30px; cursor:pointer"  onclick="location.href='/nnd/rtypeselect';"/>
   <input type="hidden" name="type" value="0">
   <input type="radio" name="type" value="처리" style="float: left; width: 90px; height: 30px; cursor:pointer"   onclick="location.href='/nnd/rtypeselect';"/>
   <input type="hidden" name="type" value="1">
 <input type="submit" value="처리" style="float: left; width: 90px; height: 30px; cursor:pointer" onclick="location.href='/nnd/rsimplech';"/>
    <input type="hidden" name="type" value="1"> 
</div>
</form>    -->
<form action="" >
   <div id="deletebutton">
   <input type="submit"  value="경고주기" onclick="javascript: form.action='/nnd/rstcheck.ad';"/>   
   <input type="submit" value="처리완료" onclick="javascript: form.action='/nnd/rsimplech';"/>
   </div>

   <table align="center" class="w3-table-all w3-small serchlist">
   <tr>
   
      <th scope="col"><input type="checkbox" id="allck" name="allck" onclick="allCk(this.checked);"></th>
      <th>번호</th><th>처리상태</th><th>신고제목</th><th>신고자</th><th>신고받은자</th><th>신고일</th><th>처리일</th></tr>
         <% for(Report r : list){%>
            <tr>
            
               <td scope="col"><input type="checkbox" name="rno" value="<%=r.getReportNo()%>">
                           <input type="hidden" name="writer" value="<%=r.getreceiverNname() %>"></td>
               <td align="center"><%= r.getReportNo() %></td>
               <td align="center">
                  <% if(Integer.parseInt(r.getReportStatus())==0){%>
                     <%="미처리" %>
                  <%}else if(Integer.parseInt(r.getReportStatus())==1){ %>
                     <%="처리" %>
                  <%}else{ %>
                     <%="처리중" %>
                  <%} %>
               </td>
               <td align="center"><a href="/nnd/rdetail.ad?rno=<%=r.getReportNo()%>"><%= r.getReportTiltle() %></a></td>
               <td align="center"><%= r.getsenderNname() %></td>
               <td align="center"><%= r.getreceiverNname() %></td>
               <td align="center"><%= r.getReportRegDate() %></td>
               <td align="center">
                  <% if(r.getReportComDate()==null){%>
                     <%=" " %>
                  <%}else{ %>
                     <%=r.getReportComDate() %>
                  <%} %>
               </td>
            </tr>
         <%}//for each문 %>

      </table>
      </form>
      <br>
<!-- 페이징 처리 -->

<div id="page" style="text-align:center;">
      <% if(currentPage <= 1){ %>
         <span>◀◀</span>
      <% } else { %>
         <span><a href="/nnd/rlist.ad?page=1">◀◀</a></span>
      <% } %>
   
      <!-- 현재 페이지가 속한 페이지그룹의 숫자 출력 처리 -->
      <% for(int p = startPage; p <= endPage; p++){
         if(p == currentPage){ %>
            <span><font color="steelblue" size="4"><b><%= p %></b></font></span>
         <% } else { %>
            <span><a href="/nnd/rlist.ad?page=<%= p %>"><%= p %></a></span>
         <% } %>
      <% } %>

      <% if(currentPage >= maxPage){ %>
         <span>▶▶</span>
      <% } else { %>
         <span><a href="/nnd/rlist.ad?page=<%= maxPage %>">▶▶</a></span>
      <% } %>
   </div>
   
   

  </div>  <!-- 전체 묶은 div : end -->
   
</body>
</html>