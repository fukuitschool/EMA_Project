<%@ page import="model.EventBean" %>
<%@ page import="model.EntryBean" %>
<%@ page import="model.StudentBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String message = (String)request.getAttribute("message");
if(message == null){ //nullの時は何も表示しない
	message = "";
}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒用ダッシュボード</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class = "header">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
</header>
<div class ="container">
<div class = "side">
<br>

名前: ${ loginUser.getStudentName() }<br>
<form action="studentdashboard" method="post">
<input type="hidden" name="studentUser" value=${ loginUser }>
<p><input type="submit" value="マイページ" size="20" class ="btn"></p>
</form>
<form action="logout" method="get">
<p><input type="submit" value ="ログアウト" class ="lobtn"></p>
</form>
</div>
<div class = "main">
<h2>生徒ダッシュボード</h2>
<h3>イベント参加画面</h3><br>
<%-- メッセージの表示 --%>
<%= message %><br>
<!-- 参加できるイベント一覧 -->
<table border="1" class = "student-dlist">
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>カテゴリ</th>
		<th>日付</th>
		<th>開始時間</th>
		<th>場所</th>
		<th>参加状態</th>
	</tr>
<c:if test="${ entryList != null }">
<c:if test="${ eventList != null }">
	<c:forEach var="events" items="${ eventList }" >
	<tr>
	<td><c:out value="${ events.event_id }" /></td>
	<td><c:out value="${ events.event_name }" /></td>
	<td><c:out value="${ events.event_category }" /></td>
	<td><c:out value="${ events.event_date }" /></td>
	<td><c:out value="${ events.event_time }" /></td>
	<td><c:out value="${ events.event_place }" /></td>
	<td><form action="EventEntryServlet" method="post">
	    <input type="hidden" name="user" value="student">
		<input type="hidden" name="event_id" value="${ events.getEvent_id() }">
		<input type="submit" value="参加します">
	    </form>
	    </td>
	</tr>
	</c:forEach>
</c:if>
</c:if>
</table>
</div>
</div>
</body>
</html>