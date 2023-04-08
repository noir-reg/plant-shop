<%-- 
    Document   : header
    Created on : Oct 1, 2022, 2:54:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css " type="text/css">
    </head>
    <body>
        <header>
            <nav><ul>
                    <li><a href="index.jsp"><img src="images/logo.jpg" id="logo"></a></li>
                    <li><a href="MainController?action=">Home</a></li>
                    <li><a href="registation.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="MainController?action=viewcart">View Cart</a></li>
                    
                    <li>
                        <form action="MainController" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="${param.txtsearch}">
                            <select name="searchby">
                                <option value="byname">
                                    by name
                                </option>
                                <option value="bycate">
                                    by category
                                </option>
                            </select>
                            <input type="submit" value="search" name="action"/>
                        </form>
                    </li>
                </ul></nav>
        </header>
    </body>
</html>
