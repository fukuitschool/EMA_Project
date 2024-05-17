<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="java.util.*, model.*" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>EMA</title>
</head>

<body>


	<h1 class="listtitle">ユーザー 一覧</h1>
			<!-- クリックでユーザー登録へ飛ぶ -->
			<p class = "uregbtn"><input type="button" value="ユーザー登録" onclick="window.location.href='userType'; return false;" /></p>


			<!-- Student List -->
			<jsp:include page="../jsp/studentList.jsp" />

			
			<!-- Admin List -->
			<jsp:include page="../jsp/adminList.jsp" />
			
			<form action="admindashboard" method="get">
			<input type="hidden" name="admintUser" value=${ loginUser }>
			<p><input type="submit" value ="戻る" class ="rebtn"></p>
</form>
</body>
</html>