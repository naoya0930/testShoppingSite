<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>検索結果</h3>

<c:set var="category_id" value="${category_id }"/>


<c:choose>
	<c:when test="${category_id eq -1 }">
		検索対象カテゴリ：すべて
	</c:when>
	<c:when test="${category_id ge 0 }">
		検索対象カテゴリ：${categories[category_id - 1].name}
	</c:when>
</c:choose>
<br>
検索ワード："${searchString }"
<br>
<hr>

	<jsp:include page="/itemList.jsp" />
<%-- <c:forEach items="${items}" var="item">
    <form action="/shopping/CartServlet?action=add" method="post">
        <input type="hidden" name="item_code" value="${item.item_id}">
        商品番号：<b>${item.item_id}</b><br>
        商品名：<b>${item.item_name}</b><br>
        価格(税込)：<b>${item.price}円</b><br>
        個数：
        <select name="quantity">
        <option value="1">1
        <option value="2">2
        <option value="3">3
        <option value="4">4
        <option value="5">5
        </select>
        個<br>
        在庫：<b>${item.stock}個</b><br>
        <input type="submit" value="カートに追加">
    </form>
</c:forEach> --%>

</body>
</html>