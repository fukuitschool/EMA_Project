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
<div class="conf-add-form-container">
	<header>
	   <h2>ユーザー登録確認</h2>
		<p style="color: red;">下記内容で登録します。</p>
	</header>
	<p>生徒ID: <span>${addStudent.studentId}</span></p>
	<p>パスワード:<span>${hidePass}</span></p>
	<p>氏名:<span>${addStudent.studentName}</span></p>
	<p>ユーザー:<span>${userStudent}</span></p>
      
    <form action="student" method="POST">
        <input type="hidden" name="cmd" value="ADD" />
        
        <input type="hidden" name="id" value="${addStudent.studentId}" />
        <input type="hidden" name="password" value="${addStudent.studentPass}" />
        <input type="hidden" name="name" value="${addStudent.studentName}" />
        
        <div>
	        <input type="submit" value="登録" />
        </div>
    </form>
    
    <input type="submit" name="cmd" value="戻る" onclick="window.location.href='userlist'; return false;" />
<!-- こちらでも可	<a href="userlist">戻る</a> -->
</div>
</body>
</html>