<%@ include file="../common/top.jsp"%>

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
  返回主菜单</stripes:link></div>

<div id="Catalog">

  <h2>${sessionScope.category.name}</h2>

  <table>
    <tr>
      <th>Product ID</th>
      <th>Name</th>
    </tr>
    <c:forEach var="product" items="${sessionScope.productList}">
      <tr>
        <td>
          <a href="productForm?productId=${product.productId}">${product.productId}</a>
        </td>
        <td>${product.name}</td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/bottom.jsp"%>
