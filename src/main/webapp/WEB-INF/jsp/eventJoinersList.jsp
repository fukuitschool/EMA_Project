<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.*,  model.EventEntryBean"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%--
 <%
 	List<EventEntryBean> reqEEBeans = (List<EventEntryBean>)  request.getAttribute("eeAdmin");  
 
	List<EventEntryBean> reqEEBeans2 = (List<EventEntryBean>)  request.getAttribute("eeStudent");  
 %>
 
 --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>EMA</title>
</head>
<body>
<div class="event-form-container">

<h1>参加者イベント</h1>
<table border="1" class ="event_list_table">
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>カテゴリ</th>
		<th>日付</th>
		<th>開始時間</th>
		<th>場所</th>
	</tr>
<c:if test="${ eventList != null }">
	<c:forEach var="events" items="${ eventList }" >
	<tr>
	<td><c:out value="${ events.event_id }" /></td>
	<td><c:out value="${ events.event_name }" /></td>
	<td><c:out value="${ events.event_category }" /></td>
	<td><c:out value="${ events.event_date }" /></td>
	<td><c:out value="${ events.event_time }" /></td>
	<td><c:out value="${ events.event_place }" /></td>
	</tr>
	</c:forEach>
</c:if>
</table>

<hr/>

<h1>イベント参加者一覧</h1>


<h3>Student</h3>
<div class="entry-table-st">
<table border="1">
   <tr>
       <th>生徒ID</th>
       <th>氏名</th>
       
   </tr>
   <c:forEach var="entryStd" items="${eeStudent}">
       <tr>
           <td>${entryStd.studentId}</td>
           <td>${entryStd.studentName}</td>
		   
           
		   <%--
		   --%>
       </tr>
   </c:forEach>
</table>
</div>
<h3>Admin</h3>

<div class="entry-table-ad">
<table border="1">
   <tr>
       <th>管理者ID</th>
       <th>氏名</th>
      
   </tr>
   <c:forEach var="entryAdmin" items="${eeAdmin}">
       <tr>
           <td>${entryAdmin.adminId}</td>
           <td>${entryAdmin.adminName}</td>
           
          
           <%--
            --%>
       </tr>
   </c:forEach>
</table>
<br/>
</div>

<br/>
<form action="EventServlet" method="post">
<input type="hidden" name="adminId" value="${ loginUser.getAdminId() }">
<input type="hidden" name="adminPass" value="${ loginUser.getAdminPass() }">
<p><input type="submit" value="イベント一覧へ戻る" size="20" class = "btn"></p>
</form>
</div>

</body>
</html>