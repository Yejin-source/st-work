<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="joinForm" action="/joinMember" method="post">
		<div>아이디:</div>
		<div>
			<input type="text" id="memberId" name="memberId">
			<button type="button" id="idCheck">중복 확인</button>
		</div>
		<div>비밀번호:</div>
		<div><input type="password" id="memberPw" name="memberPw"></div>
		<div><button type="button" id="joinBtn">회원가입</button></div>
	</form>
	
	<script>
		$('#idCheck').click(function() {
			const memberId = $('#memberId').val();
			
			// 아이디 입력 여부 확인
			if(memberId == '') {
				alert('아이디를 입력하세요');
				return;
			}
			
			// 아이디 중복 확인
			$.ajax({
				url:'/isId/' + memberId
				, type: 'get'
				, success: function(data) {
					if(data == true) {
						alert('이미 사용 중인 아이디 입니다');
					} else {
						alert('사용 가능한 아이디입니다');
					}	
				}
			});
		});
	
	</script>
</body>
</html>