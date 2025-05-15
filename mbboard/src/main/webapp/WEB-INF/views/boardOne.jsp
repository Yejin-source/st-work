<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Board One</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<a href="/" class="nav-link">📋 게시판 목록</a>

	<h1>🔍 게시글 상세보기</h1>

	<a href="/updateBoard?boardNo=${board.boardNo}" class="nav-link">✏️ 수정하기</a>
	<a href="#" id="deleteLink" class="nav-link">❌ 삭제하기</a>
	
	<form id="deleteForm" action="/deleteBoard" method="post" style="display:none">
		<input type="hidden" name="boardNo" value="${board.boardNo}"></input>
	</form>


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
			<tr>
				<td>${board.boardNo}</td>
				<td>${board.boardTitle}</td>
				<td>${board.boardContent}</td>
				<td>${board.boardUser}</td>
				<td>${board.updatedate}</td>
				<td>${board.createdate}</td>
			</tr>
		</tbody>
	</table>
	
	<script>
		$('#deleteLink').click(function(e) {
			e.preventDefault(); // 기본 동작 막기 -> 링크처럼(href="#") 동작하지 않도록 차단
			if(confirm('정말 삭제하시겠습니까?')) { // [확인], [취소]
				$('#deleteForm').submit();
			}
		});
	</script>
</body>
</html>