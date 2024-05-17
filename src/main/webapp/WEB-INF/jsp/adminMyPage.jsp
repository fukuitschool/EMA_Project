<%@ page import="model.EventBean" %>
<%@ page import="model.EntryBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class = "header">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
</header>
<div class ="container">
<div class = "side">
<br>
名前: ${ loginUser.getAdminName() }<br>
<br>
<form action="admindashboard" method="get">
<input type="hidden" name="admintUser" value=${ loginUser }>
<input type="hidden" name="eventList" value="${ eventList }">
<p><input type="submit" value ="戻る" class ="rebtn"></p>
</form>
</div>
<div class = "main">
<h2>　マイページ　</h2>
<h3 >　申込済イベント一覧　</h3>
<!-- 参加しているイベント一覧 -->
<table border="1" class = "smtable">
	<tr>
		<th>イベントID</th>
		<th>イベント名</th>
		<th>イベントカテゴリー</th>
		<th>開催日</th>
		<th>開始時刻</th>
		<th>開催場所</th>
		<th>キャンセル</th>
	</tr>
<c:if test="${ eventList != null }">
<c:if test="${ entryList != null }">
<c:forEach var="events" items="${ eventList }" >
	<c:forEach var="entry" items="${ entryList }" >
	<c:if test="${ entry.event_id == events.event_id }">
	<tr>
	<td><c:out value="${ events.event_id }" /></td>
	<td><c:out value="${ events.event_name }" /></td>
	<td><c:out value="${ events.event_category }" /></td>
	<td><c:out value="${ events.event_date }" /></td>
	<td><c:out value="${ events.event_time }" /></td>
	<td><c:out value="${ events.event_place }" /></td>
	<td><form action="EntryCancelServlet" method="post">
	    <input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
	    <input type="hidden" name="user" value="admin">
		<input type="hidden" name="event_id" value="${ entry.getEvent_id() }">
		<input type="submit" value="キャンセル">
	    </form>
	    </td>
	</tr>
	</c:if>
	</c:forEach>
</c:forEach>
</c:if>
</c:if>
</table>
</div>
</div>
</body>
</html>