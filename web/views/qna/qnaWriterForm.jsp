<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA_QNA</title>
<!-- 문의사항 글쓰기 -->
<%@ include file="/views/common/meta.jsp"%>
<script src="/nnd/resources/ckeditor/ckeditor.js"></script>

<!-- <style>
select{
display:none;
}
</style> -->

</head>
<body>
	<%@ include file="/views/common/header.jsp"%>

	<h1 class="subtit">
		<span>QNA Write</span>문의사항 작성하기
	</h1>
	<section id="postwrap">

		<!-- Page Content -->
		<div id="postCon" class="qnawrite">
			<div id="postTextIn">
				<form action="/nnd/qinsert" method="post"
					enctype="multipart/form-data">
					<table class="pR">
						<tr class="tit">
							<th><font>제목</font></th>
							<td><input type="text" class="sform" name="title" readonly
								value="문의사항입니다"></td>
						</tr>

						<tr>
							<th><font>작성자</font></th>
							<td><input type="text" class="sform" name="writer" readonly
								value="<%=loginMember.getnName()%>"></td>
						<tr>
							<th><font>파일 선택</font> </th>
							<td><input type="file" name="qfile" class="sform"></td>
						</tr>
						<tr>
							<th><font>내용</font></th>
							<td><textarea rows="5" cols="50" name="content"
									height="1500"></textarea></td>
						</tr>

					</table>
					
					<div class="qbtn">
							<input type="submit" value="등록하기" class="bottomb">
							<input type="reset" value="작성취소" class="bottomb"
								onclick="javascript:history.go(-1); return false;'">
							<input type="button" value="목록으로" class="bottomb"
								onclick="javascript:history.go(-1); return false;">
					</div>
					
				</form>
			</div>
		</div>
	</section>
	<script>
			CKEDITOR.replace("content", { height:"400"});
 		</script>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>