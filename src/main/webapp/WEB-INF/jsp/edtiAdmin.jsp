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

    <form action="admin" method="POST">
	    <div>
	        <input type="hidden" name="cmd" value="EDIT" />
	        <!-- name="studentId"   Specify student by  value="${editStudent.studentId}-->
	        <input type="hidden" name="adminId" value="${edtiAdmin.adminId}" />
	    </div>
        
        <p>
	        <input type="text" name="adminName" value="${edtiAdmin.adminName}" required/>
	        <%-- <input type="password" name="adminPass" value="${edtiAdmin.adminPass}" /> --%>
        </p>
        
        <input type="submit" value="編集する" />
    </form>
    <p>
        <a href="userlist">戻る</a>
    </p>
   </div>
</body>
</html>