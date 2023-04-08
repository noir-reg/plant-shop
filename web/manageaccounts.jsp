<%-- 
    Document   : manageaccounts
    Created on : Oct 16, 2022, 11:55:58 PM
    Author     : Admin
--%>

<%@page import="dao.AccountDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css">
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
            <form action="MainController" method="get">
            <input type="text" name="searchAccount" placeholder="email" value="${param.searchAccount}">

            <input type="submit" value="searchAccount" name="action">
        </form>
        <h1></h1>
        <table>
            <tr><th>ID</th>
                <th>Email</th>
                <th>Full name</th>
                <th>Status</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            <% String keyWord = request.getParameter("searchAccount");

                ArrayList<Account> list = new ArrayList<Account>();

                if (keyWord == null || keyWord.isEmpty()) {
                    list = AccountDao.getAccounts("");
                } else {

                    list = AccountDao.getAccounts(keyWord);
                }
                request.setAttribute("acList", list);
                 
            %>
            <c:forEach var="acc" items="${requestScope.acList}">
                <tr>
                    <td><c:out value="${acc.accID}"></c:out></td>
                    <td><c:out value="${acc.email}"></c:out></td>
                    <td><c:out value="${acc.fullname}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${acc.status eq 1}">active</c:when>
                            <c:otherwise>inactive</c:otherwise>
                        </c:choose></td>
                    <td><c:out value="${acc.phone}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${acc.role eq 1}">admin</c:when>
                            <c:otherwise>user</c:otherwise>
                        </c:choose></td>
                    <td>
                        <c:if test="${acc.role eq 0}">
                            <c:url var="mylink" value="MainController">
                                <c:param name="email" value="${acc.email}"></c:param>
                                <c:param name="status" value="${acc.status}"></c:param>
                                <c:param name="action" value="updateaccountstatus"></c:param>
                            </c:url>
                            <a href="${mylink}">Block/Unblock</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%} else {%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>
