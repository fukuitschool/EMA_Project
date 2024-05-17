<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.EventBean" %>
<%@ page import="model.EntryBean" %>
<%@ page import="model.AdminBean" %>
<%@ page import="java.util.List" %>
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
<title>管理者ダッシュボード</title>
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
<form action="EventServlet" method="post">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<p><input type="submit" value="イベント一覧・追加" size="20" class = "btn"></p>
</form>
<br>

<form action="userlist" method="GET">
	<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
	<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
	<input type="hidden" name="action" value="DISPATCH" />
	<input type="submit" value="ユーザー一覧・編集" size="20" class = "btn" />
</form>
<br>
<form action="admindashboard" method="post">
<input type="hidden" name="adminUser" value=${ loginUser }>
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<input type="hidden" name="loginUser" value=${ loginUser }>
<p><input type="submit" value="マイページ" size="20" class = "btn"></p>
</form>
<br>
<form action="logout" method="get">
<p><input type="submit" value ="ログアウト" class ="lobtn"></p>
</form>
</div>
<div class = "main">
管理者ダッシュボード<br>
<%-- メッセージの表示 --%>
<%= message %><br>
<!-- 参加できるイベント一覧 -->
<table border="1" class = "dlist">
	<tr>
		<th>イベントID</th>
		<th>イベント名</th>
		<th>イベントカテゴリー</th>
		<th>開催日</th>
		<th>開始時刻</th>
		<th>開催場所</th>
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
	    <input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
		<input type="hidden" name="event_id" value="${ events.getEvent_id() }">
		<input type="hidden" name="user" value="admin">
		<input type="submit" value="参加します">
	    </form>
	    </td>
	</tr>
	</c:forEach>
</c:if>
</c:if>
</table>
</div>
<br>
</div>
</body>
</html>