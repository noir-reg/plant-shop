<%-- 
    Document   : registation
    Created on : Oct 1, 2022, 4:44:17 PM
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
            <form action="MainController" method="post" class="form">
                <h1>Register</h1>
                <table>
                    <tr><td>email</td><td><input type="email" name="txtemail" required="" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" value="${requestScope.txtemail}" ></td></tr>
                    <tr><td>full name</td><td><input type="text" name="txtfullname" required="" value="${requestScope.txtfullname}"></td></tr>
                    <tr><td>password</td><td><input type="password" name="txtpassword" required="" title="Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters"
                                                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                    value="${requestScope.txtpassword}"></td></tr>
                    <tr><td>phone</td><td><input type="text" name="txtphone" value="${requestScope.txtphone}">${requestScope.ERROR}</td></tr>

                    <tr><td colspan="2"><input type="submit" name="action" value="register"></td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
