<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, qna.model.vo.Qna, qnareply.model.vo.QnaReply"%>
<%
	ArrayList<Qna> list = (ArrayList<Qna>) request.getAttribute("list");
	String replytitle = (String) request.getAttribute("replytitle");
	QnaReply reply = new QnaReply();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_QNA</title>
<%@ include file="../../views/common/meta.jsp"%>
</head>
<body>

	<!--  유저가 보는 문의사항 목록 -->

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

	<%@ include file="../common/header.jsp"%>
	<div id="wrap">
		<div class="my_page">
			<h1 class="subtit">
				<span>My QnA</span>문의 내역
			</h1>

			<%@ include file="../common/mypageMenu.jsp"%>
			<div class="my_table">
				<caption>나의 문의사항 목록</caption>
				<div id="wrap">
					<div class="my_page">
						<div class="my_table">
							<table>
								<tr>
									<th>글번호</th>
									<th>답변여부</th>
									<th>글제목</th>
									<th>작성자</th>
									<th>첨부파일</th>
									<th>날짜</th>
								</tr>

								<%
									for (Qna q : list) {
								%>
								<tr>
									<td><%=q.getQnaNo()%></td>
									<td>
										<%
											if (reply.getReplyTitle() != null) {
										%> <a href="/nnd/qrcheck"></a>
										Y <%
 	} else {
 %> N <%
 	}
 %>
									</td>
									<td><a href="/nnd/qdetail?qnum=<%=q.getQnaNo()%>"><%=q.getQnaTitle()%></a></td>
									<td><%=q.getNname()%></td>
									<td align="center">
										<%
											if (q.getQnaOriginalFilepath() != null) {
										%> ◎ <%
											} else { //첨부파일이 없을 때
										%>
										&nbsp; <%
 	}
 %>
									</td>
									<td><%=q.getQnaDate()%></td>
								</tr>
								<%
									}
								%>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>