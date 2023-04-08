<%-- 
    Document   : createplant
    Created on : Oct 23, 2022, 6:00:17 PM
    Author     : Admin
--%>

<%@page import="dto.Account"%>
<%@page import="dao.AccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
        <c:import url="header_loginedadmin.jsp"></c:import>
        ${requestScope.result}
        <form action="MainController" method="post" class="form">
            <table>
                <tr><td>Name</td><td><input name="pName" value="${param.pName}" required=""></td></tr> 
                <tr><td>Price</td><td><input name="pPrice" value="${param.pPrice}" required=""></td></tr> 
                <tr><td>Image</td><td><input name="pImg" value="${param.pImg}" required=""></td></tr> 
                <tr><td>Description</td><td><input name="pDescription" value="${param.pDescription}" required=""></td></tr> 
                <tr><td>Status</td><td><input name="pStatus" value="${param.pStatus}" require=""></td></tr> 
                <tr><td>Cate ID</td><td><input name="pCateID" value="${param.pCateID}" required=""></td></tr> 
                <tr><td colspan="2"><input type="submit" name="action" value="create" required=""></td></tr>         
            </table>            
        </form>
        <%} else {%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>
