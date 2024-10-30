<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="" method="POST">
    <h3>User Information</h3>
    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.username}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="text" name="repeatedPassword"></td>
        </tr>
    </table>
    <%@ include file="includeAccount.jsp"%>

    <input type="submit" name="editAccount" value="Save Account Information" />
    </form>

    <a href="listOrders">我的订单</a>
</div>

<%@ include file="../common/bottom.jsp"%>
