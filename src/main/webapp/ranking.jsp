<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ranking</title>
<style>
	label{
	display:inline-block;
	text-align: right;
	width: 153px;
	}
</style>
</head>
<body>

	<jsp:include page="/menu.jsp" />

	<h3>売れ筋ランキング</h3>
	
	
	<c:forEach items="${ranking}" var="item" varStatus="stat">
		
		<c:if test="${item.stock != 0}">
			<form action="/shopping/CartServlet?" method="post">
				<input type="hidden" name="item_code" value="${item.item_id}">

				<h3><b>　No. ${stat.count}</b></h3>
				　<label>商品番号　：</label><b>${item.item_id}</b><br> 
				　<label>商品名　　：</label><b>${item.item_name}</b><br>
				　<label>価格(税込)：</label><b>${item.price}円</b><br> 
				　<label>在庫　　　：</label><b>${item.stock}個</b><br>
				　<label>個数　　　： </label>
				<select name="quantity">
				<c:forEach begin="1" end="${item.stock}" varStatus="loop">
				<option value="${loop.count}">${loop.count}
				</c:forEach>
				</select> <b>個</b>　　　　　　 
				　<input type="submit" value="カートに追加">
				<br>
				<hr>
				<br>
			</form>
		</c:if>
	</c:forEach>

</body>
</html>