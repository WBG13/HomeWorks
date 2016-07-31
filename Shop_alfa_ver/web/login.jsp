<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="WEB-INF/header.jsp" %>
<%!
    Cookie[] MyCookies;
    String uid = "";
    String psw = "";
    int authLevel = 0;
%>
<table style="width: 100%;">
    <tr>
        <td style="width: 25%; height: 80%;" valign="top">
            <%@ include file="WEB-INF/navbar.jsp" %>
        </td>
        <td style="width: 75%; height: 80%">
            <%
                if (session.getAttribute(uid) == null || session.getAttribute(uid).equals("")){
                // try to use cookies
                MyCookies = request.getCookies();
                if (MyCookies != null){
                    for (Cookie c : MyCookies){
                if (c.getName().equalsIgnoreCase("credential_uid")){
                    //set the uid from cooklie value
                    uid = c. getValue();
                } else if (c.getName().equalsIgnoreCase("credential_psw")){
                    psw = c.getValue();
                }
                }
            }
            } else {
            uid = (String)session.getAttribute("uid");
            psw = (String)session.getAttribute("psw");
            }
            %>
            <form id="form1" method="post" action="loginAuthorization.do">
                <table style="width: 450px;">
                    <tr>
                        <td>
                            <span>UserName: </span>
                        </td>
                        <td>
                            <input name="uid" type="text" style="width: 250px;" value="<%=uid%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span>Password: </span>
                        </td>
                        <td>
                            <input name="psw" type="password" style="width: 250px;" value="<%=psw%>">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input name="rememberMe" type="checkbox">&nbsp;Remember me
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Sign In"
                                   style="width: 250px;"/>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
