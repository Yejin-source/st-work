<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Member</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		// sn1 입력 완료 시 sn2로 포커스 이동
		$('#sn1').keyup(function() {
			if($(this).val().length == 6) {
				$('#sn2').focus();
			}
		});
		
		// 외부 API 서버를 호출 - 비동기 구현 필수
		$('#sn2').blur(function() {
			let sn1 = $('#sn1').val();
			let sn2 = $('#sn2').val();

			// 주민번호 앞 6자리 + 뒤 7자리 and 숫자 형식인지 확인
			if(sn1.length == 6 && sn2.length == 7 && !isNaN(sn1) && !isNaN(sn2)) {
				$.ajax({
					url:'http://localhost:9999/isSn/'+$('#sn1').val()+$('#sn2').val()
					, type: 'get'
					, success: function(data) {
									// 'true' or 'false'
									if(data == true) {
										alert('주민번호 인증 성공');
										
										// 성별
										if(Number($('#sn2').val().substr(0, 1)) % 2 == 0) {
											$('#gender').val('여');
										} else {
											$('#gender').val('남');	
										}
						
										// 나이
										// 생년월일
										const century = (sn2[0] == '1' || sn2[0] == '2') ? 1900 : 2000;
										const birthYear = century + Number(sn1.substr(0, 2));
										const birthMonth = Number(sn1.substr(2, 2));
										const birthDay = Number(sn1.substr(4, 2));
										
										// 오늘 날짜
										const today = new Date();
										const thisYear = today.getFullYear();
										const thisMonth = today.getMonth() + 1;
										const thisDate = today.getDate();
										
										// 생일이 지나지 않은 경우 -1
										let age = thisYear - birthYear;
										if(thisMonth < birthMonth) {
											age--;
										} else if (thisMonth == birthMonth && thisDate < birthDate) {
											age--;
										}
										
										$('#age').val(age);
									} else {
										alert('주민번호 인증 실패');
									}
								}
				});
			} else {
				alert('주민번호 형식이 올바르지 않습니다.');
			}
		});
		
		// 내부 API 서버를 호출 - 비동기 구현 필수 X -> 편의상 비동기로 구현
		$('#idckBtn').click(function(){
			// ID 입력 여부 확인
			if($('#idck').val() == '') {
				alert('아이디를 입력하세요.');
				return;
			}
			
			// ID 중복 확인
			$.ajax({
				url:'/isId/'+$('#idck').val()
				, type: 'get'
				, success: function(data) {
					if(data == true) {
						alert('이미 사용 중인 아이디 입니다.');
					} else {
						alert('사용 가능한 아이디입니다.');
						$('#id').val($('#idck').val());
					}	
				}
			});
		});
		
		$('#btn').click(function(){
			const pw = $('#pw').val();
			const pw2 = $('#pw2').val();
			
			// 비밀번호 입력 여부 확인
			if(pw == '' || pw2 == "") {
				alert('비밀번호를 입력하세요.');
				return;
			}
			
			// 비밀번호 일치 여부 확인
			if(pw != pw2) {
				alert('비밀번호가 일치하지 않습니다.')
				return;
			}
			
			// 모든 조건이 만족할 경우 submit
			$('#joinForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>회원가입</h1>
	
	<hr>
	
	<h2>주민번호확인</h2>
	<table border="1">
		<tr>
			<th>주민번호</th>
			<td>
				<input type="text" id="sn1"> <!-- keyup, length 6, focus sn2 -->
				- 
				<input type="text" id="sn2"> <!-- blur length == 7, snapi 호출, true gender+age, false alert 잘못된 주민번호  -->
			</td>
		</tr>
	</table>
	
	<hr>
	
	<h2>ID검색</h2>
	<table border="1">
		<tr>
			<th>ID검색</th>
			<td>
				<input type="text" id="idck">
				<button type="button" id="idckBtn">ID검색</button>
			</td>
		</tr>
	</table>
	
	<hr>
	
	<form id="joinForm" action="/joinMember" method="post">
		<table border="1">
			<tr>
				<th>성별</th>
				<td><input type="text" id="gender" name="gender" readonly></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" id="age" name="age" readonly></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="id" name="id" readonly></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="pw" name="pw">
					확인 - <input type="password" id="pw2" name="pw2">
				</td>
			</tr>
		</table>
		<button type="button" id="btn">회원가입</button>
	</form>
</body>
</html>