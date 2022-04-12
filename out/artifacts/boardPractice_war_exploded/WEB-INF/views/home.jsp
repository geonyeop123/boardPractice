<%--
  Created by IntelliJ IDEA.
  User: yeop
  Date: 2022/04/09
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>BOARD</title>
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/reset.css">
</head>
<body>
<div class="header">
	<div class="logo">LOGO</div>
	<div class="navi">
		<a class="home navi_contents active" href='<c:url value="/"/>'><div>HOME</div></a>
		<a class="board navi_contents" href='<c:url value="/board/list"/>'><div>BOARD</div></a>
		<a class="login navi_contents" href="#"><div>LOGIN</div></a>
	</div>
</div>
<div class="mainContainer">
	<div class="titleContainer">
      <h1 class="title bold">HOME</h1>
    </div>
</div>
</body>
</html>
