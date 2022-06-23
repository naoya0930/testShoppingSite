<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login!</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>登録済みの方はこちらから</h3>

<form action="/shopping/LoginServlet" method="post">
e-mail：<input type="text" name="member_email"><br>
パスワード：<input type="password" name="member_pass"><br><br>
<input type="hidden" name="action" value="login">
<input type="submit" value="ログイン">
</form>

<h3>新規会員登録</h3>

<form action="/shopping/RegisternewMemberServlet" method="post">
氏名：<input type="text" name="member_name"><br>
住所：<input type="text" name="member_address"><br>
電話番号：<input type="text" name="member_tel_1" size="5">-
<input type="text" name="member_tel_2" size="5">-
<input type="text" name="member_tel_3" size="5">
<br>
e-mail：<input type="text" name="member_email"><br>
パスワード：<input type="text" name="member_pass"><br><br>
<input type="hidden" name="action" value="register">
<input type="submit" value="登録">
</form>

</body>
</html>