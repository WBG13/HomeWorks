<%@ page contentType="text/html;charset=UTF-8" language="java"
        import="java.util.*"
        %>
<html>
  <head>
    <title>World manager page</title>
  </head>
  <body>

  <%@ include file="web_support_elements/header.jsp" %>
  <table style="width: 100%;">
    <tr>
      <td style="width: 25%; height: 80%" valign="top">
        <%@ include file="web_support_elements/navbar.jsp"%>
      </td>
      <h1>Content goes here</h1>
    </tr>
  </table>
  <%@include file="web_support_elements/footer.jsp"%>
  <%
  String uid;
  String psw;
  %>

  <%
    String name = String.valueOf(session.getAttribute("name"));
    if (Objects.equals(name, "") || name == "null") session.setAttribute("name", "guest");
  %>
  Current session use username <%=session.getAttribute("name")%>
  <a href="login.jsp">This link will move you to the login check</a>
  </body>
</html>
