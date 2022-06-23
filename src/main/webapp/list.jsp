<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>

	<jsp:include page="/menu.jsp" />

	<h3>商品一覧</h3>
	<jsp:include page="/itemList.jsp" />
<%-- 
	<c:forEach items="${items}" var="item">
		<c:if test="${item.stock != 0 }">
			<form action="/shopping/CartServlet?action=add" method="post">
				<input type="hidden" name="item_code" value="${item.item_id}">
				　商品番号　：<b>${item.item_id}</b><br> 
				　商品名　　：<b>${item.item_name}</b><br>
				　価格(税込)：<b>${item.price}円</b><br> 
				　個数　　　： 
				<select name="quantity">
					<option value="1">1
					<option value="2">2
					<option value="3">3
					<option value="4">4
					<option value="5">5
				</select> 個<br> 　在庫　　　：<b>${item.stock}個</b><br> 
				　<input type="submit" value="カートに追加">
				<br><br>
			</form>
		
		</c:if>
	</c:forEach>
--%>
</body>
</html>