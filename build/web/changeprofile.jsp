<%-- 
    Document   : changeprofile
    Created on : Oct 22, 2022, 8:06:41 PM
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
         <%-- 
    Document   : personalpage
    Created on : Oct 1, 2022, 4:54:07 PM
    Author     : Admin
--%>

<%@page import="dto.Account"%>
<%@page import="dao.AccountDao"%>
<%@page import="dao.OrderDao"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDao.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }

            } else {
                login = true;
            }
            if (login) {%>
        <header>
            <%@include file="headerlogineduser.jsp" %>
        </header>
            <section>
            <form action="MainController" method="post" class="form">
                <h1>Change Profile</h1>
                <table>
                    
                    <tr><td>full name</td><td><input type="text" name="txtfullname" required=""value="${param.txtfullname}"></td></tr>
                    
                    <tr><td>phone</td><td><input type="text" name="txtphone"value="${param.txtphone}">${requestScope.result}</td></tr>

                    <tr><td colspan="2"><input type="submit" name="action" value="change"></td></tr>
                </table>
            </form>
        </section>
        <footer><%@include file="footer.jsp" %></footer>
            <%}else{%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>

    </body>
</html>
