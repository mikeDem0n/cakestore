<%@ include file="../common/top.jsp"%>

<div id="Catalog">
  <form>
    <p>请输入你的用户名和密码。</p>
    <c:if test="${requestScope.signOnMsg !=null}">
      <p>${requestScope.signOnMsg}</p>
    </c:if>
    <p>
      用户名:<input type="text" name="username"> <br />
      密码:<input type="text" name="username">
    </p>
    <input type="submit" value="login">
  </form>
  需要一个用户名和密码？
  <a href="registerForm">现在注册吧！</a>
</div>

<%@ include file="../common/bottom.jsp"%>