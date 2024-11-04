<%--
  Created by IntelliJ IDEA.
  User: 40264
  Date: 2024/10/29
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <link rel="StyleSheet" href="css/cakestore.css" type="text/css"
        media="screen" />
  <title>cakestore</title>
</head>
<body>
  <div id="Header">


    <div id="Menu">
      <div id="MenuContent">
        <a href="cartForm"><img src="images/cart.png"/></a>
        <img src="images/separator.gif"/>
        <c:if test="${sessionScope.loginAccount == null}">
          <a href="signonForm">登录</a>
          <img src="images/separator.gif"/>
        </c:if>
        <c:if test="${sessionScope.loginAccount != null}">
          <a href="#">登出</a>
          <img src="images/separator.gif"/>
          <a href="#">我的账户</a>
          <img src="images/separator.gif"/>
        </c:if>
        <a href="help.html">?</a>
      </div>
    </div>

    <div id="Search">
      <div id="SearchContent">
        <form action="" method="post">
          <input type="text" name="keyword" size="14"/>
          <input type="submit" value="Search">
        </form>
      </div>
    </div>

    <div id="QuickLinks">
      <a href="#"><img src="images/bannner_bread.png"/></a>
      <img src="images/separator.gif" />
      <a href="#"><img src="images/bannner_little.png" /></a>
      <img src="images/separator.gif" />
      <a href="#"><img src="images/bannner_birth.png" /></a>
      <img src="images/separator.gif" />
      <a href="#"><img src="images/bannner_custom.png" /></a>
      <img src="images/separator.gif" />
      <a href="#"><img src="images/bannner_dessert.png" /></a>
    </div>

  </div>

<div id="Content">