<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>EMA</title>
</head>

<body>
  <div class ="conf-delete">
	<header>
	   <h2>ユーザー 削除</h2>
		<p style="color: red;">下記内容を削除します。</p>
	</header>
	<p>生徒ID: <span>${deleteStudent.studentId}</span></p>
	<p>パスワード:<span>${hidePass}</span></p>
	<p>氏名:<span>${deleteStudent.studentName}</span></p>
	<p>ユーザー:<span>${userStudent}</span></p>
      
    <form action="student" method="POST">
        <input type="hidden" name="cmd" value="DELETE" />
        <input type="hidden" name="studentId" value="${deleteStudent.studentId}" />
        
        <div>
	        <input type="submit" value="削除" />
        </div>
    </form>
    <p>
        <a href="userlist">戻る</a>
    </p>
   </div>
</body>
</html>