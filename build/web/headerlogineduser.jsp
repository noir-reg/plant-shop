<%-- 
    Document   : headerlogineduser
    Created on : Oct 1, 2022, 4:55:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="MainController?action=changeprofile">Change Profile</a></li>
            <li><a href="MainController?action=order&status=2">Complete Order</a></li>
            <li><a href="MainController?action=order&status=3">Canceled Order</a></li>
            <li><a href="MainController?action=order&status=1">Processing Order</a></li>
            <form action="MainController"><li>from <input value="${param.from}" type="date" name="from">to<input value="${param.to}" type="date" name="to">
                    <input type="submit" name="action" value="find">
            </li></form>
            
            
        </nav>
    </body>
</html>
