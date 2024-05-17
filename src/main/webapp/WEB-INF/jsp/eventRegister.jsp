<%@ page import="model.EventBean"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = (String)request.getAttribute("message");
if(message == null){ //nullの時は何も表示しない
	message = "";
}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>イベント登録画面</title>
</head>
<body>
<div class="event-register">
	<h2>イベント登録画面</h2>
	<br>
	<div class="event-form-container">
	<%-- メッセージの表示 --%>
	<div class=".event-register-form">
<%= message %>
	<form action="EventEditServlet" method="get">
		<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
		<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
		イベントID：<input type="text" name="event_id" required><br> 
		イベント名：<input type="text" name="event_name" required><br> 
		イベントカテゴリー： 
		<input type="radio" name="event_category" value="懇親会" required>懇親会 
		<input type="radio" name="event_category" value="セミナー" required>セミナー 
		<input type="radio" name="event_category" value="勉強会" required>勉強会 
		<input type="radio" name="event_category" value="その他" required>その他<br>
		　　　開催日 ：<input type="date" name="event_date" required>　　　　　　　<br> 
		　　開始時刻：<input type="time" name="event_time" required>　　　　　　　　　<br> 
		開催場所：<input type="text" name="event_place" required><br> <br> 
		<input type="submit" value="送信"><br>
	</form>
<form action="EventServlet" method="post">
<p>
<input type="submit" value="戻る"  class="rebtn">
</p>
</form>
</div>
</div>
</div>
</body>
</html>