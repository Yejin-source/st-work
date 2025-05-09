<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Board</title>
</head>
<body>
	<h1>게시글 추가</h1>
	<form id="addForm" action="${pageContext.request.contextPath}/addBoard" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>boardTitle</td>
				<td>
					<input type="text" name="boardTitle" id="boardTitle">
				</td>
			</tr>
			<tr>
				<td>boardfile</td>
				<td>
					<div>
						<button id="appendFile" type="button">파일 추가</button>
					</div>
					<div id="fileDiv">
						<input type="file" name="boardfile" class="boardfile"> <!-- 여러 개가 될 수 있으니까 id가 아닌 class 사용 -->
					</div>
				</td>
			</tr>
		</table>
		<button id="addBtn" type="button">입력</button>
	</form>
	<script>
		let flag = false;
		document.querySelector('#appendFile').addEventListener('click', ()=>{						
			flag = false; // 두 번째, 세 번째 flag=false 초기화 후...
			
			// input type=file 추가 : 앞에 파일이 더 선택되어 있다면
			let boardfiles = document.querySelectorAll('.boardfile');
			boardfiles.forEach((e)=>{
				if(e.value == '') {
					alert('공백의 boardfile이 있습니다');
					flag = true; // 공백이 존재
					console.log(flag);
					return; // foreach 콜백함수를 탈출 
				}
			});
			
			if(flag) { // 공백이 존재한다면
				return;
			}
			
			let inputFile = document.createElement('input');
			inputFile.setAttribute('type', 'file');
			inputFile.setAttribute('name', 'boardfile');
			inputFile.setAttribute('class', 'boardfile');	
			document.querySelector('#fileDiv').appendChild(inputFile);
		});
		
		document.querySelector('#addBtn').addEventListener('click', ()=>{
			// alert('addBtn Click!');
			// 폼(값) 유효성 검사 선행
			if(document.querySelector('#boardTitle').value == '') {
				alert('title을 입력하세요');
				return;
			}
			
			// 파일이 추가되지 않은 node(input type=file)를 삭제하고 진행
			let boardfiles = document.querySelectorAll('.boardfile');
			boardfiles.forEach((e)=>{
				if(e.value == '') {
					e.remove(); // node 삭제
				}
			});
			
			document.querySelector('#addForm').submit();
		});
	</script>
</body>
</html>