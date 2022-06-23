<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 -->
	<c:forEach items="${items}" var="item">
		<c:if test="${item.stock != 0 }">
			<form action="/shopping/CartServlet?action=add" method="post">
				<input type="hidden" name="item_code" value="${item.item_id}">
				　商品番号　：<b>${item.item_id}</b><br> 
				　商品名　　：<b>${item.item_name}</b><br>
				　価格(税込)：<b>${item.price}円</b><br> 
					　<label>在庫　　　：</label><b>${item.stock}個</b><br>
				　個数　　　： 
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
<!-- 
</body>
</html> 
-->