<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${loginMember.memberId}님 상세정보</h1>
	<div>
		<a href="/logout">로그아웃</a>
	</div>
	<div>
		MEMBER_ROLE : ${loginMember.memberRole}
	</div> 
	<div>
		<a href="">비밀번호 수정</a>
	</div> 
</body>
</html>