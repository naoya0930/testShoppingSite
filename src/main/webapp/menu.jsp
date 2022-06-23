<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
nav.menu {
	width: 100%; 
	height: 110px;
	background-color: #00008b;
	position: fixed;
	top: 0;
	left: 0; 
	color: white;
	overflow-x:auto;
	-webkit-overflow-scrolling: touch;
	texit-align:center;
}
ul.menu {
  display: flex;
    max-width: 1060px; /* メニューの最大幅 */
  min-width: 770px; /* メニューの最小幅 */
  
}
li.menu {
  list-style: none;
    padding: 5px;              /* 余白指定 */
    display: inline-block;      /* インラインブロック要素にする */
}
li.menu:hover {
    border: 1px solid #FFFFFF;
}
a.menu {
  text-decoration: none;
	margin: 0px;
  color: #FFFFFF;
      white-space: nowrap;
}
/* スクロールバーを非表示にする */
::-webkit-scrollbar {
  display: none;
}
form.menu {
      white-space: nowrap;
}

</style>

<nav class="menu">
	
	<ul class="menu">
		<li class="menu">
			<a class="menu" href="/shopping/ShowItemServlet?action=top">ようこそ</a>
		</li>
		<c:forEach items="${categories}" var="category">
		<li class="menu">
			<a class="menu" href="/shopping/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>
		</li>
		</c:forEach>
		<li class="menu">
			<a class="menu" href="/shopping/RankingServlet?">ランキング</a>
		</li>
		<li class="menu">
			<a class="menu" href="/shopping/CartServlet?action=show">カートを見る</a>
		</li>

		<c:choose>
			<c:when test="${isLogin eq true }">
				<li class="menu">
					<a class="menu" href="/shopping/UpdateMemberServlet">会員情報変更</a>
				</li>
				<li class="menu">
					<a class="menu" href="/shopping/SellerServlet">出品者はこちら</a>
				</li>
			</c:when>
		</c:choose>
	</ul>

	<form class="menu" action="/shopping/SearchServlet?action=search" method="post">
		<select name="category_id">
			<option value="-1">すべて</option>
			<c:forEach items="${categories}" var="category">
				<option value="${category.code}">${category.name}</option>
			</c:forEach>
		</select> 
		<input type="search" name="searchString" placeholder="キーワードを入力">
		<input type="submit" name="submit" value="検索">

	<c:choose>
		<c:when test="${isLogin ne true }">
				<li class="menu">
						<a class="menu" href="/shopping/LoginServlet?action=login">「新規会員登録/ログイン」はこちら</a>
					</li>
		</c:when>
		<c:when test="${isLogin eq true }">
				【${member_info.member_name}さんログイン中】
				<li class="menu">
					<a class="menu" href="/shopping/LoginServlet?action=logout">ログアウト</a>
				</li>
		</c:when>
	</c:choose>
	</form>


</nav>



<br>
<br>
<br>
<br>
