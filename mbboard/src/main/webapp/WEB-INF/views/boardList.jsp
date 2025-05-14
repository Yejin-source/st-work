<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Board List</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>게시판</h1>

	<table border="1">
		<thead>
			<tr>
				<th>No</th>
				<th>Title</th>
				<th>Content</th>
				<th>User</th>
				<th>Update Date</th>
				<th>Create Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list}">
				<tr>
					<td>${b.boardNo}</td>
					<td><a href="/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
					<td>${b.boardContent}</td>
					<td>${b.boardUser}</td>
					<td>${b.updatedate}</td>
					<td>${b.createdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form id="" action="/boardList" method="get">
		<input type="text" name="searchWord" placeholder="검색어를 입력하세요.">
		<button type="button" id="btn">검색</button>	
	</form>
	
	<script>
	
	</script>
</body>
</html>