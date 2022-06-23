<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/menu.jsp" /><br>
	<h2>出品情報</h2>
	<%-- 渡された販売情報が空の時--%>
	<c:if test="${!empty isChanged}">
		<h3>${isChanged}</h3>
	</c:if>

	<c:if test="${empty sellerItems}">
		<h3>出品された商品はありません</h3>
	</c:if>
	
	<c:if test="${!empty sellerItems}">
		<table border="1">
			<tr>
				<td>商品名</td>
				<td>カテゴリ</td>
				<td>在庫</td>
				<td>値段</td>
				<td>取り扱い開始日</td>
				<td>取り扱い停止</td>
			</tr>
			<c:forEach items="${sellerItems}" var="item">
				<tr>
					
					<td align="center">${item.item_name}</td>
					<td align="center">${categories[item.category_id-1].name}</td>
					<td align="right">
						<form action="/shopping/SellerItemControllerServlet?action=update&column=stock&id=${item.item_id}&item_name=${item.item_name}" method="post">
						<input type="number" value="${item.stock}" name="c_value" required>
						<input type="submit" name=update value="更新">
						</form>
						</td>
					<td align="right">
						<form action="/shopping/SellerItemControllerServlet?action=update&column=price&id=${item.item_id}&item_name=${item.item_name}" method="post">
						<input type="number" value="${item.price}" name="c_value" required>円
						<input type="submit" name=update value="更新">
						</form></td>
					<td align="right">${item.start_date}</td>
					<td align="right">
					<a href="/shopping/SellerItemControllerServlet?action=stop&id=${item.item_id}&item_name=${item.item_name}">取り扱い停止</a></td>

				</tr>


			</c:forEach>
		</table>
		<br>
	</c:if>

	<h2>新規出品</h2>
	<form action="/shopping/SellerItemControllerServlet?action=add" method="post">
		<table border="1">
			<tr>
				<td>商品名</td>
				<td>カテゴリ</td>
				<td>在庫</td>
				<td>値段</td>
			</tr>
			<tr>
				<td><input type="text" name="item_name" required></td>
				<td><select name="category_id">
						<c:forEach items="${categories}" var="category">
							<option value="${category.code}">${category.name}</option>
						</c:forEach>
				</select></td>
				<td><input type="number" name="stock" required></td>
				<td><input type="number" name="price" required></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="新規出品">
		
	</form>
</body>

</html>