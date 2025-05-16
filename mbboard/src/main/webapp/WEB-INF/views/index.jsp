<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/login">로그인</a>
	<h2>전체 접속자</h2>
	<table border="1">
		<tr>
			<th>ANONYMOUS(TOTAL)</th>
			<th>MEMBER</th>
			<th>ADMIN</th>
		</tr>
		<tr>
			<td>${connetCountMapAll.ANONYMOUS}</td>
			<td>${connetCountMapAll.MEMBER}</td>
			<td>${connetCountMapAll.ADMIN}</td>
	</table>
	
	<h2>오늘 접속자</h2>
	<table border="1">
		<tr>
			<th>ANONYMOUS(TOTAL)</th>
			<th>MEMBER</th>
			<th>ADMIN</th>
		</tr>
		<tr>
			<td>${connetCountMapToday.ANONYMOUS}</td>
			<td>${connetCountMapToday.MEMBER}</td>
			<td>${connetCountMapToday.ADMIN}</td>
		</tr>
	</table>
	
	<h2>현재 서버의 접속자(톰켓 서버 안에 활성된 세션의 개수)</h2>
	<table border="1">
		<tr>
			<th>TOTAL</th>
		</tr>
		<tr>
			<td>${currentConnectCount}</td>
		</tr>
	</table>
</body>
</html>