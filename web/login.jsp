<%-- 
    Document   : login
    Created on : Oct 1, 2022, 4:33:47 PM
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
        <header>
            <%@include  file="header.jsp" %>
        </header>
        <section>
            <form action="MainController" method="post">
                <p style="color: red"><%=(request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%></p>
                <table>
                    <tr><td>email</td>
                        <td><input type="text" name="email" placeholder="email" ></td>

                    </tr>

                    <tr><td>password</td>
                        <td><input type="text" name="password"  placeholder="password"></td></tr>

                    <tr><td></td>
                        <td><input type="submit" name="action" value="login"></td></tr>
                    <tr><td></td>
                        <td><input type="checkbox" name="savelogin" value="savelogin">Stay sign in</td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
