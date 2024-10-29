<%@ include file="../common/top.jsp"%>

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
  返回主菜单</stripes:link></div>

<div id="Catalog">

  <table>
    <tr>
      <th>&nbsp;</th>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.productList}">
      <tr>
        <td>
          <a href="">${product.description}</a>
        </td>
        <td><b>
          <a href="">${product.productId}</a>
        </b></td>
        <td>${product.name}</td>
      </tr>
    </c:forEach>
    <tr>
      <td></td>
    </tr>

  </table>

</div>

<%@ include file="../common/bottom.jsp"%>