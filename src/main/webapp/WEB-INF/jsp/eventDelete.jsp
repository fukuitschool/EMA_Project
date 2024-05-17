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
<title>イベント削除確認画面</title>
</head>
<body>
<div class="confirm-table">
<c:forEach var="event" items="${ deleteEvents }" >
<%-- 表の作成 --%>
<table border="1">
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
	本当に削除しますか？
<form action="EventEditServlet" method="post">
<input type="hidden" name="delete_id" value="${ event.event_id }">
<input type="hidden" name="action" value="delete">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="はい">
</form>
<form action="EventServlet" method="post">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="いいえ">
</form>
</c:forEach>
</div>
</body>
</html>