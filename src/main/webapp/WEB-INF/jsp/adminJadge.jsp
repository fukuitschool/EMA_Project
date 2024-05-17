<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String message = (String)request.getAttribute("message");
if(message == null){ //nullの時は何も表示しない
	message = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style.css" rel="stylesheet"/>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<title>管理者確認画面</title>
</head>
<body class ="topbody">
<div class="admin-jadge">
<h2 class ="title">管理者の確認</h2>
<form acton="AdminJadgeServlet" method="post" class = "form">
<table>
<tr>
<td><input type="password" name="checkpass"></td></tr>
</table>
<p class ="login"><input type="submit" value="送信" size="20"></p>
</form>
</div>
<%-- メッセージの表示 --%>
<p class ="message"><%= message %></p>
<form action="index.jsp">
<p class ="login"><input type="submit" value="戻る"></p>
</form>
</body>
</html>