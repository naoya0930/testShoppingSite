<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update!</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>会員情報の更新</h3><br>

<form action="/shopping/UpdateMemberServlet" method="post">
氏名：<input type="text" name="member_name"><br>
住所：<input type="text" name="member_address"><br>
電話番号：<input type="text" name="member_tel_1" size="5">-
<input type="text" name="member_tel_2" size="5">-
<input type="text" name="member_tel_3" size="5">
<br>
e-mail：<input type="text" name="member_email"><br>
<input type="hidden" name="action" value="update">
<input type="submit" value="更新">
</form>

<a href="/shopping/withdrawMemberServlet">→退会される方はこちら</a>

</body>
</html>