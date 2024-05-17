<%@ page import="model.EventBean" %>
<%@ page import="model.AdminBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント一覧画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body class = "elbody">
<h2>イベント一覧画面</h2><br>
<form action="EventServlet" method="get">
<input type="hidden" name="action" value="register">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<p class= "regbtn"><input type="submit" value="新規登録"></p>
</form>
<%-- 表の作成 --%>
<table border="1" class ="event_list_table">
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>カテゴリ</th>
		<th>日付</th>
		<th>開始時間</th>
		<th>場所</th>
		<th>参加者一覧</th>
		<th>編集・削除</th>
	</tr>
<c:if test="${ eventList != null }">
	<c:forEach var="events" items="${ eventList }" >
	<tr>
	<td><c:out value="${ events.event_id }" /></td>
	<td><c:out value="${ events.event_name }" /></td>
	<td><c:out value="${ events.event_category }" /></td>
	<td><c:out value="${ events.event_date }" /></td>
	<td><c:out value="${ events.event_time }" /></td>
	<td><c:out value="${ events.event_place }" /></td>
	<td>
		<form action="eventAll" method="GET">
			<input type="hidden" name="cmd" value="LIST_PARTICIPANTS" /> 
			<input type="hidden" name="eventId" value="${ events.event_id }" /> 
				<input type="hidden" name="status" value="Joined" /> 
			<input type="submit" value="イベント参加者一覧" />
		</form>
	</td>
	<td><form action="EventServlet" method="get">
	    <select name="action" size="1">
		<option value="edit">編集</option>
		<option value="delete">削除</option>
		</select>
		<input type="hidden" name="event_id" value="${ events.getEvent_id() }">
		<input type="submit" value="実行">
		<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
		<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
	    </form></td>
	</tr>
	</c:forEach>
</c:if>
</table>
<form action="admindashboard" method="get">
<p><input type="submit" value="戻る" class ="rebtn"></p>
</form>
</body>
</html>