<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>本当に退会しますか？</h3><br>

<a href="/shopping/withdrawMemberServlet?action=withdraw">→退会される方はこちら</a><br><br>
<a href="/shopping/ShowItemServlet?action=top">→退会されない方はこちら</a>

</body>
</html>