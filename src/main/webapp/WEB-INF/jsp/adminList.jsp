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

<body class ="listbg">
<br/>
 
	<h3 class ="title">管理者 一覧</h3>
	<!-- Admin List -->
	<table border="1" class ="listtable">
		<tr>
			<th>ID</th>
			<!-- <th>パスワード</th> -->
			<th>名前</th>
			<th>編集</th>
			<th>削除</th>
		</tr>
		
		<c:forEach var="admin" items="${adminBeans}">										
			<tr>
				<td> ${admin.adminId} </td>
				<%--
				 <td> ${admin.adminPass} </td>
				 --%>
				<td> ${admin.adminName} </td>
				
				<td>
					<form action="admin" method="POST">
						<input type="hidden" name="cmd"  value="LOAD_EDIT"/>
						<input type="hidden" name="adminId" value="${admin.adminId}" />
						<input type="submit" value="編集" />
					</form>
				</td>
				<td>
					<form action="admin" method="POST">
						<input type="hidden" name="cmd"  value="LOAD_DELETE"/>
						<input type="hidden" name="adminId"  value="${admin.adminId}" />
					    <input type="hidden" name="usertype" value="管理者" />
						<input type="submit" value="削除" />	
					</form>
				</td>				
			</tr>
		</c:forEach>
	</table>

</body>
</html>