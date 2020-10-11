<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>

<!-- 신고하기 모달창 -->
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<div class="w3-container">  
<div id="id01" class="w3-modal">
<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
<div class="w3-center">
  <br>
<span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
      
      </div>

      <form class="w3-container" action="/nnd/pinsert.ad">
        <div class="w3-section">
          <label><b>새로 등록할 금지어를 입력하세요</b></label>
          <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="금지어를 입력하세요" name="procontent" required>
          
          <br>
          <br>
        
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit" onclick="javascript:location.href='/nnd/pinsert.ad'" style=" width : 30%;  float : right; ">등록하기</button>
          <button onclick="document.getElementById('id01').style.display='none'" type="button" class="w3-button w3-block w3-red w3-section w3-padding" style=" width : 30%;  float : left; ">취소</button>
        </div>
      </form>

    </div>
  </div>
</div> 
            
</body>
</html>
