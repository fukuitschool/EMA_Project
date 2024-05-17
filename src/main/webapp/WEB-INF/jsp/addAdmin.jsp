<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/style.css">
	<title>EMA</title>
</head>
<body>
<%-- <%= reqStudens %> --%>
<div class = "add-admin">
	<div class="add-admin-title">
		<h1>ユーザー登録</h1>
		<h3>管理者登録</h3>
	 ID：半角数字4桁で設定してください。<br>
	 パスワード：半角数字8桁で設定してください。
	 <br>
	<jsp:include page="../jsp/errorMsgs.jsp" />
	</div>
	<br>
	<div class="form-container">
	<div style="display: flex; flex-direction: row; gap: 1rem;">
		ユーザー種別:
		<div style="display: flex; align-items: center; gap: 1rem;">
			<form action="userType" method="GET">
				<input type="hidden" name="usertype" value="ADMIN"/>
				<input type="submit" value="管理者" autofocus />
			</form>
			<form action="userType" method="GET">
			    <input type="hidden" name="usertype" value="STUDENT" />
				<input type="submit" value="生徒" />
			</form>
	 </div>
	 </div>
		
	<form action="admin" method="POST">
		<input type="hidden" name="cmd" value="LOAD_ADD"/>
	    <input type="hidden" name="usertype" value="管理者" />
	   
		　　　　ID:<input type="text" name="id" required/><br/>
		パスワード:<input type="password" name="password" required/><br/>
		　　　名前:<input type="text" name="name" required/><br/>
		
		<input type="submit" value="送信"/>
	</form>
	<p class= "return-link"><a href="userlist">戻る</a></p>
</div>
</body>
</html>