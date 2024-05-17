<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>EMA</title>
</head>

<body>
	<header  class="conf-add-title">
	   <h2>ユーザー登録確認</h2>
		<p style="color: red;">下記内容で登録します。</p>
	</header>
	<div class="conf-add-form-container">
	<p>管理者ID: <span>${addAdmin.adminId}</span></p>
	<p>パスワード:<span>${hidePass}</span></p>
	<p>氏名:<span>${addAdmin.adminName}</span></p>
	<p>ユーザー:<span>${userAdmin}</span></p>
      
    <form action="admin" method="post">
        <input type="hidden" name="cmd" value="ADD" />
        
        <input type="hidden" name="id" value="${addAdmin.adminId}" />
        <input type="hidden" name="password" value="${addAdmin.adminPass}" />
        <input type="hidden" name="name" value="${addAdmin.adminName}" />
        
        <div>
	        <input type="submit" value="登録" />
        </div>
    </form>

    <p>
        <input type="button" value="戻る" onclick="window.location.href='userlist'; return false;" />
    </p>
    </div>
</body>
</html>