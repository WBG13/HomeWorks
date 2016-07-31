<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.sql.*," %>
<%@ page import="com.firstshop.dbhelper.DBManager" %>
<%@ page import="com.firstshop.dbhelper.MySQLServerConnectionBehavior" %>


<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Welcome to the world manager</title>
</head>
<body>

<table style="width: 100%;">
    <tr>
        <td style="width: 75%; height: 80%" valign="top">
            <%
                DBManager dbm = new DBManager();
                StringBuilder sb = new StringBuilder("<html><body>");

                try {
                    sb.append("<table border=1>" +
                            "<tr><td>Product name</td><td>Amount</td>" +
                            "<td>Price</td>");
                    String query = String.valueOf(session.getAttribute("request"));
                    if (query.equals("") || query.equals("null")) query = "SELECT * FROM products;";
                    dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
                    dbm.openConnection();
                    ResultSet rs = dbm.ExecuteQueryResultSet(query);
                    while (rs.next()) {
                        String name = rs.getString("product_name");
                        int amount = Integer.parseInt(rs.getString("product_amount"));
                        String imageLocation = rs.getString("product_image");
                        int id = Integer.parseInt(rs.getString("product_id"));
                        int price = Integer.parseInt(rs.getString("product_price"));
                        System.out.println("ERROR 3");
                        sb.append("<tr><td>" + name + "</td>" +
                                "<td>" + amount + "</td>" +
                                "<td>" + price + "</td>");
                    }
                    sb.append("</table>");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(sb);
            %>


            <%=sb%>
        </td>
    </tr>
</table>
<%@include file="footer.jsp" %>
</body>
