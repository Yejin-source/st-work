<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Update Board</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<a href="/boardOne?boardNo=${board.boardNo}" class="nav-link">⬅ 이전 페이지</a>

	<h1>✏️ 게시글 수정</h1>

	<form id="updateBoardForm" action="/updateBoard" method="post">
		<table border="1">	
			<tr>
				<th>No</th>
				<td>
					${board.boardNo}
					<input type="hidden" name="boardNo" value="${board.boardNo}">
				</td>
			</tr>
			<tr>	
				<th>Title</th>
				<td>
					<input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle}">
				</td>
			</tr>
			<tr>	
				<th>Content</th>
				<td>
					<input type="text" id="boardContent" name="boardContent" value="${board.boardContent}">
				</td>
			</tr>
			<tr>
				<th>User</th>
				<td>
					<input type="text" id="boardUser" name="boardUser" value="${board.boardUser}">
				</td>
			</tr>	
		</table>
	
		<div>
			<button type="button" id="updateBtn">수정하기</button>	
		</div>
	</form>
	
	<script>
		const title = $('#boardTitle');
		const content = $('#boardContent');
		const user = $('#boardUser');
			
		$('#updateBtn').click(function() {

			// 유효성 검사
			if(title.val() == '') {
				alert('제목을 입력하세요');
				return;
			}
			
			if(content.val() == '') {
				alert('내용을 입력하세요');
				return;
			}
			
			if(user.val() == '') {
				alert('작성자를 입력하세요');
				return;
			}
			
			// 모든 검사를 통과하면 -> form 전송
			updateBoardForm.submit();
		});
	</script>
</body>
</html>