<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMA</title>
</head>
<body>

<h1>ERROR</h1>

<h3>外部キーを持つユーザーは削除できません</h3>


ダッシュボードへ戻ります

<form action="userlist" method="GET">
	<input type="hidden" name="action" value="DISPATCH" />
	<input type="submit" value="ユーザー一覧画面・登録・編集・削除" />
</form>

<br />

</html>