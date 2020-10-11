<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="views/common/error.jsp"%>

<!DOCTYPE html>
<html>
<head>

<title>NANOONDA_POST</title>
<%@ include file="../../views/common/meta.jsp"%>
<style type="text/css"></style>

<script src="/nnd/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){		
	$.ajax({
		url : "/nnd/rnew",
		type : "get",
		dataType : "json", //리스트로 받을거라 json으로 받는다
		success : function(data) {
			console.log("success: " + data);

			var jsonStr = JSON.stringify(data);

			var json = JSON.parse(jsonStr);

			var values = "<tr><th>번호</th><th>제목</th><th>보낸사람</th><th>받은날짜</th></tr>";
			
			for ( var i in json.list) {
				values += "<tr><td>"
						+ (window.parseInt(i)+1)
						+ "</td><td><a href='/nnd/rdetail?receiveno="
						+ json.list[i].no
						+ "'>"
						+ decodeURIComponent(json.list[i].title).replace(
								/\+/gi, " ") + "</a></td><td>"		
						+ decodeURIComponent(json.list[i].sender).replace(
										/\+/gi, " ") +"</td><td>"
						+ json.list[i].date + "</td></tr>"
					
						
								
			} //for in

			$("#newreceive").html($("newreceive").html() + values);
		},
		error : function(jqXHR, textstatus, errorthrown) {
			console.log("error: " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	
	$.ajax({
		url : "/nnd/snew",
		type : "get",
		dataType : "json", //리스트로 받을거라 json으로 받는다
		success : function(data) {
			console.log("success: " + data);

			var jsonStr = JSON.stringify(data);

			var json = JSON.parse(jsonStr);

			var values = "<tr><th>번호</th><th>제목</th><th>받은사람</th><th>보낸날짜</th></tr>";
			for ( var i in json.list) {
				values += "<tr><td>"
					+ (window.parseInt(i)+1)
					+ "</td><td><a href='/nnd/sdetail?sendno="
					+ json.list[i].no
					+ "'>"
					+ decodeURIComponent(json.list[i].title).replace(
							/\+/gi, " ") + "</a></td><td>"		
					+ decodeURIComponent(json.list[i].sender).replace(
									/\+/gi, " ") +"</td><td>"
					+ json.list[i].date + "</td></tr>"
						
						
						
			} //for in

			$("#newsend").html($("newsend").html() + values);
		},
		error : function(jqXHR, textstatus, errorthrown) {
			console.log("error: " + jqXHR + ", " + textstatus + ", "
					+ errorthrown);
		}
	});
	
});	
	
</script>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<h1 class="subtit"><span>Post</span>우편함</h1>

<section id="postwrap">

	<!-- Page Content -->
	<div id="postCon">
	
		<div id="search" align="center">
			<form action="/nnd/psearch" method="post">
				<input type="text" name="keyword" placeholder="제목을 입력하세요." >
				<input class="bottomb" type="submit" value="편지검색" >
			</form>
		</div>
		
	<!-- 최근 받은 편지 5개 조회 출력 -->
	<div class="pt_take w3-container crear" >
		<font class="tit">최근 받은편지</font>
			<span class="more"><a href="/nnd/rlist?page=1">받은편지함<span class="ti-arrow-circle-right"></span></a></span>
			<table id="newreceive"  border="1">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>보낸사람</th>
					<th>받은날짜</th>
				</tr>
			</table>
		</div>
  
  <br>
 
	<!-- 최근 보낸 편지 5개 조회 출력 -->
	<div class="pt_send w3-container crear" >
		<font class="tit">최근 보낸편지</font>
			<span class="more"><a href="/nnd/slist?page=1">보낸편지함<span class="ti-arrow-circle-right"></span></a></span>
			<table id="newsend" border="1">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>받은사람</th>
					<th>보낸날짜</th>
				</tr>
			</table>
		</div>
	</div>
</section>
<%@ include file="../../views/common/footer.jsp"%>
</body>
</html>