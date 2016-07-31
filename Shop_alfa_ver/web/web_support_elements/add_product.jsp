<%@ page contentType="text/html;charset=UTF-8" language="java" %>
${message}<br>
<form id="form1" method="post" action="ProductAddChecker.go">
  <table style="width: 450px;">
    <tr>
      <td>
        <span>Product name: </span>
      </td>
      <td>
        <input name="productName" type="text" style="width: 250px;" value=""/>
      </td>
    </tr>
    <tr>
      <td>
        <span>Product image </span>
      </td>
      <td>
        <input name="productImage" type="password" style="width: 250px;" value="">
      </td>
    </tr>
    <tr>
      <td>
        <span>Product amount: </span>
      </td>
      <td>
        <input name="productAmount" type="text" style="width: 250px;" value=""/>
      </td>
    </tr>
    <tr>
      <td>
        <span>Product price: </span>
      </td>
      <td>
        <input name="productPrice" type="text" style="width: 250px;" value=""/>
      </td>
    </tr>
    <tr>
      <td>
        <span>Product description: </span>
      </td>
      <td>
        <input name="productDescription" type="text" style="width: 250px;" value=""/>
        <input type="hidden" name="category" type="text" value="product"/>
      </td>
    <tr>
      <td colspan="2" align="right">
        <input type="submit" value="Add product"
               style="width: 250px;"/>
      </td>
    </tr>
  </table>
</form>
