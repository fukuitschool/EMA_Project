<%@ page import="model.EventBean"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>イベント登録確認画面</title>
</head>
<body>
<div class="register-confirm">
<%-- 表の作成 --%>
<table border="1" class="register-confirm-table">
	<tr>
		<th>イベントID</th>
		<th>イベント名</th>
		<th>イベントカテゴリー</th>
		<th>開催日</th>
		<th>開始時刻</th>
		<th>開催場所</th>
	</tr>
	<tr>
	<td><c:out value="${ editEvent.event_id }" /></td>
	<td><c:out value="${ editEvent.event_name }" /></td>
	<td><c:out value="${ editEvent.event_category }" /></td>			
	<td><c:out value="${ editEvent.event_date }" /></td>
	<td><c:out value="${ editEvent.event_time }" /></td>
	<td><c:out value="${ editEvent.event_place }" /></td>
	</tr>
	

</table>
<div class="register-confirm-btn">
	これで登録しますか？
<form action="EventEditServlet" method="post">
<input type="hidden" name="event_id" value="${ editEvent.event_id }">
<input type="hidden" name="event_name" value="${ editEvent.event_name }">
<input type="hidden" name="event_category" value="${ editEvent.event_category }">
<input type="hidden" name="event_date" value="${ editEvent.event_date }">
<input type="hidden" name="event_time" value="${ editEvent.event_time }">
<input type="hidden" name="event_place" value="${ editEvent.event_place }">
<input type="hidden" name="action" value="register">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="はい">
</form>
<form action="EventServlet" method="post">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="submit" value="いいえ">
</form>
</div>
</div>
</body>
</html>