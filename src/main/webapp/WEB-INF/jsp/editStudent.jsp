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
<div class ="conf-delete">
    <h2>ユーザー 編集</h2>

    <form action="student" method="POST">
	    <div>
	        <input type="hidden" name="cmd" value="EDIT" />
	        <!-- name="studentId"   Specify student by  value="${editStudent.studentId}-->
	        <input type="hidden" name="studentId" value="${editStudent.studentId}" />
	    </div>
        
        <p>
	        <input type="text" name="studentName" value="${editStudent.studentName}" required/>
	        <%-- <input type="password" name="studentPass" value="${editStudent.studentPass}" /> --%>
        </p>
        
        <input type="submit" value="編集する" />
    </form>
    <p>
        <a href="userlist">戻る</a>
    </p>
   </div>
</body>
</html>