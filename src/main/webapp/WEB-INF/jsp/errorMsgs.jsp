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
	<c:if test="${not empty unavailableIdMsg }">
		<p>${unavailableIdMsg}</p>
	</c:if>
	<c:if test="${not empty invalidIdMsg}">
		<p>${invalidIdMsg}</p>
	</c:if>
	<c:if test="${not empty invalidPassMsg}">
		<p>${invalidPassMsg}</p>
	</c:if>
	
	
	
	<%-- 	<div>
		<p>${requestScope.unavailableIdMsg}</p>
	</div> --%>
</body>
</html>