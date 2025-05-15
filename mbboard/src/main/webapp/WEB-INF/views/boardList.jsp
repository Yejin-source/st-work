<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Board List</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<a href="/addBoard" class="nav-link">✍️ 게시글 추가</a>

	<h1>📋 게시판 목록</h1>

	<table border="1">
		<thead>
			<tr>
				<th>No</th>
				<th>Title</th>
				<th>User</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list}">
				<tr>
					<td>${b.boardNo}</td>
					<td><a href="/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
					<td>${b.boardUser}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="paging">
		<c:if test="${page.currentPage > 1}">
			<a href="?currentPage=${page.currentPage-1}&searchWord=${page.searchWord}">이전 페이지</a>	
		</c:if>
		
		<span class="page-info">[ ${page.currentPage} / ${page.lastPage} ]</span>
		
		<c:if test="${page.currentPage < page.lastPage}">
			<a href="?currentPage=${page.currentPage+1}&searchWord=${page.searchWord}">다음 페이지</a>
		</c:if>
	</div>
	
	<form id="searchWordForm" action="/boardList" method="get">
		<input type="text" name="searchWord" placeholder="검색어를 입력하세요.">
		<button type="button" id="btn">검색</button>	
	</form>
	
	<script>
		$('#btn').click(function() {
			$('#searchWordForm').submit();
		});
	</script>
</body>
</html>