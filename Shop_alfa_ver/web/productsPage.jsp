<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>World manager page</title>
</head>
<body>
<%System.out.println("Initializing product page");%>
<%@ include file="web_support_elements/header.jsp" %>
<table style="width: 100%;">
  <tr>
    <td style="width: 25%; height: 80%" valign="top">
      <%@ include file="web_support_elements/navbar.jsp"%>
    </td>
    <h1>Content goes here</h1>
  </tr>
</table>
<%@include file="web_support_elements/testPage.jsp"%>
</body>
</html>
