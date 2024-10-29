<%@ include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="productForm?productId=${sessionScope.product.productId}">返回${sessionScope.productId}}</a>
</div>

<div id="Catalog">

  <table>
    <tr>
      <td>${sessionScope.product.description}</td>
    </tr>
    <tr>
      <td><b> ${sessionScope.item.itemId} </b></td>
    </tr>
    <tr>
      <td><b>${sessionScope.item.attribute1}
        ${sessionScope.item.attribute2} ${sessionScope.item.attribute3}
        ${sessionScope.item.attribute4} ${sessionScope.item.attribute5}
        ${sessionScope.product.name}
      </b></td>
    </tr>
    <tr>
      <td>${sessionScope.product.name}</td>
    </tr>
    <tr>
      <td><c:if test="${sessionScope.item.quantity <= 0}">
        缺货.
      </c:if> <c:if test="${sessionScope.item.quantity > 0}">
        ${sessionScope.item.quantity} 有现货.
      </c:if></td>
    </tr>
    <tr>
      <td><fmt:formatNumber value="${sessionScope.item.listPrice}"
                            pattern="$#,##0.00" /></td>
    </tr>

    <tr>
      <td>
        <a href="" class="Button">加入购物车</a>
      </td>
    </tr>
  </table>

</div>

<%@ include file="../common/bottom.jsp"%>
