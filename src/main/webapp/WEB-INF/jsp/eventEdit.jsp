<%@ page import="model.EventBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>イベント編集画面</title>
</head>
<body>
<div class="confirm-table">
<c:forEach var="event" items="${ editEvents }" >
<%-- 表の作成 --%>
<table border="1"class="edit-table">
	<tr>
		<th>イベントID</th>
		<th>イベント名</th>
		<th>イベントカテゴリー</th>
		<th>開催日</th>
		<th>開始時刻</th>
		<th>開催場所</th>
	</tr>
	<tr>
	<td><c:out value="${ event.event_id }" /></td>
	<td><c:out value="${ event.event_name }" /></td>
	<td><c:out value="${ event.event_category }" /></td>			
	<td><c:out value="${ event.event_date }" /></td>
	<td><c:out value="${ event.event_time }" /></td>
	<td><c:out value="${ event.event_place }" /></td>
	</tr>
</table>
	<form action="EventEditServlet" method="post">
		<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }" required>
		<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }" required>
		<br> 
		イベント名：<input type="text" name="name" required><br> 
		イベントカテゴリー： 
		<input type="radio" name="category" value="懇親会" required>懇親会 
		<input type="radio" name="category" value="セミナー">セミナー 
		<input type="radio" name="category" value="勉強会">勉強会 
		<input type="radio" name="category" value="その他">その他<br>
		開催日：<input type="date" name="date" required><br> 
		開始時刻：<input type="time" name="time" required><br> 
		開催場所：<input type="text" name="place" required><br> <br>

	これで登録しますか？

<input type="hidden" name="event_id" value="${ event.event_id }">
<input type="hidden" name="action" value="edit">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="はい">
</form>
<form action="EventServlet" method="post">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="戻る">
</form>
</c:forEach>
</div>
</body>
</html>