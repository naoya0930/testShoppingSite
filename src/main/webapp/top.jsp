<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>


<html>
<style>
.block{
    text-decoration:none; 
    border: 1px solid #000000;
	margin: 5px;
    display: inline-block;      /* インラインブロック要素にする */
    background-color:  #DDDDDD;    /* 背景色指定 */
    padding: 30px;              /* 余白指定 */
}
.block:hover{
  opacity: 0.6;
}
</style>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body>

<jsp:include page="/menu.jsp" />

<h3>ようこそ！「SD部最後の思い出」ショッピングサイトへ</h3>

<h4>商品カテゴリ</h4>
<a href="/shopping/ShowItemServlet?action=list&code=1" class="block">
	本
	<br>
	<img src="./Image/book_tate.png" width="100" height="100" >
	<br>
	もっと見る
</a>

<a href="/shopping/ShowItemServlet?action=list&code=2" class="block">
	DVD	
	<br>
	<img src="./Image/entertainment_movie.png" width="100" height="100" >
	<br>
	もっと見る
</a>
<a href="/shopping/ShowItemServlet?action=list&code=3" class="block">
	ゲーム
	<br>
	<img src="./Image/portable_game.png" width="100" height="100" >
	<br>
	もっと見る
</a>
<a href="/shopping/ShowItemServlet?action=list&code=4" class="block">
	その他	
	<br>
	<img src="./Image/other.png" width="100" height="100" >
	<br>
	もっと見る
</a>

</body>
</html>