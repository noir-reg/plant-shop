<%-- 
    Document   : manageorders
    Created on : Oct 23, 2022, 12:04:37 PM
    Author     : Admin
--%>

<%@page import="dto.Account"%>
<%@page import="dao.AccountDao"%>
<%@page import="dao.OrderDao"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
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
            <form action="MainController" method="get">
                <input type="text" name="searchByEmail" placeholder="email" value="${param.searchByEmail}">

            <input type="submit" value="searchByEmail" name="action">
        </form>



        <form action="MainController" method="get"> from <input value="${param.from}" type="date" name="from">to<input value="${param.to}" type="date" name="to">
            <input type="submit" name="action" value="searchByDate">
        </form>
        <h1></h1>
        <table>
            <tr><th>Order ID</th>
                <th>Order Date</th>
                <th>Ship Date</th>
                <th>Status</th>
                <th>ID</th>

            </tr>
            <% String searchByEmail = request.getParameter("searchByEmail");
                String from = request.getParameter("from");
                String to = request.getParameter("to");

                ArrayList<Order> list = new ArrayList<Order>();

                if ((searchByEmail == null || searchByEmail.isEmpty()) && (from == null || from.isEmpty()) && (to == null || to.isEmpty())) {
                    list = OrderDao.getOrders();
                } else if (searchByEmail != null && !searchByEmail.isEmpty()) {

                    list = OrderDao.getOrders(searchByEmail);
                } else if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())) {
                    list = OrderDao.getOrders(from, to);
                }
                request.setAttribute("orList", list);

            %>
            <c:forEach var="order" items="${requestScope.orList}">
                <tr>
                    <td><c:out value="${order.orderID}"></c:out></td>
                    <td><c:out value="${order.orderDate}"></c:out></td>
                    <td><c:out value="${order.shipDate}"></c:out></td>

                        <td> <c:if test="${order.status eq 1}"><c:out value="processing"></c:out></c:if>
                        <c:if test="${order.status eq 2}"><c:out value="complete"></c:out></c:if>
                        <c:if test="${order.status eq 3}"><c:out value="canceled"></c:out></c:if>
                            </td>
                            <td><c:out value="${order.accID}"></c:out></td>

                    </tr>
            </c:forEach>
        </table>
        <%} else {%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>
