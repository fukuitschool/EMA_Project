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
<title>管理者ログイン画面</title>
</head>
<body class ="topbody">
<h1 class= "title">管理者ログイン画面</h1>
<form action="AdminLoginServlet" method="post" class = "form">
<table>
<tr>
<td>ユーザ―ID:<input type="text" name="id"></td></tr>
<tr><td>パスワード:<input type="password" name="pass"></td></tr>
<tr><td><p class ="login"><input type="submit" value="ログイン" size="20"></p></td></tr>
</table>
</form>
<%-- メッセージの表示 --%>
<p class="message"><%= message %></p>
<form action="index.jsp">
<p class ="login"><input type="submit" value="TOPに戻る"></p>
</form>
<br>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>