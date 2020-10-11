<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NANOONDA REPORT</title>
</head>
<body>

<!-- 삭제 확인 Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시물 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        게시물을 정말 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary">삭제하기</button>
       	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>