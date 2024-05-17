<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>EMA</title>
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
<br/>
	<h3 class ="title">生徒 一覧</h3>
	<!-- Student List -->
	<table border="1" class ="listtable">
		<tr>
			<th>ID</th>
			<%--
				<th>パスワード</th>
			 --%>
			<th>名前</th>
			<th>編集</th>
			<th>削除</th>
		</tr>
		
		<c:forEach var="students" items="${stBeans}">										
			<tr>
				<td> ${students.studentId} </td>
				<%--
				 <td> ${students.studentPass} </td>
				 --%>
				<td> ${students.studentName} </td>
				
				<td>
					<form action="student" method="POST">
						<input type="hidden" name="cmd"  value="LOAD_EDIT"/>
						<input type="hidden" name="studentId" value="${students.studentId}" />
						<input type="submit" value="編集" />
					</form>
				</td>
				<td>
					<form action="student" method="POST">
						<input type="hidden" name="cmd"  value="LOAD_DELETE"/>
						<input type="hidden" name="studentId"  value="${students.studentId}" />
					    <input type="hidden" name="usertype" value="生徒" />
						<input type="submit" value="削除" />					
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>