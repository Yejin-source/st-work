<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Add Board</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<a href="/boardList" class="nav-link">📋 게시판 목록</a>
	
	<h1>✍️ 게시글 추가</h1>

	<form id="addBoardForm" action="/addBoard" method="post">
		<table border="1">	
			<tr>	
				<th>Title</th>
				<td>
					<input type="text" id="boardTitle" name="boardTitle">
				</td>
			</tr>
			<tr>	
				<th>Content</th>
				<td>
					<input type="text" id="boardContent" name="boardContent">
				</td>
			</tr>
			<tr>
				<th>User</th>
				<td>
					<input type="text" id="boardUser" name="boardUser">
				</td>
			</tr>	
		</table>
	
		<button type="button" id="insertBtn">추가하기</button>	
	</form>
	
	<script>
		const title = $('#boardTitle');
		const content = $('#boardContent');
		const user = $('#boardUser');
		
		$('#insertBtn').click(function() {
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
			addBoardForm.submit();
		});
	</script>
</body>
</html>