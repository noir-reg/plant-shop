<%-- 
    Document   : header_loginedadmin
    Created on : Oct 16, 2022, 10:50:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <header>
            <ul>
                <li><a href="MainController?action=manageaccounts">Manage Account</a></li>
                <li><a href="MainController?action=manageorders">Manage Orders</a></li>
                <li><a href="MainController?action=manageplants">Manage Plants</a></li>
                <li><a href="MainController?action=managecategories">Manage Categories</a></li>
                <li>Welcome ${sessionScope.name}  <a href="MainController?action=logout">Logout</a></li>

            </ul>
        </header>
    </body>
</html>
