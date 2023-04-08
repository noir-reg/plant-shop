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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h3>Welcome <%=name%> comeback</h3>
            <form method="post" action="MainController"><input  type="submit" value="logout" name="action"></form>
            <a href="personalpage.jsp">View all orders</a>
        </section>
        <section>
            <%
                ArrayList<Order> list =(ArrayList<Order>) session.getAttribute("statusOrdersList");
                String[] status = {"", "processing", "completed", "cancelled"};
                String tmp="";
                if (list != null && !list.isEmpty()) {
                    for (Order or : list) {%>
            <table class="order"> 
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>Action</td></tr>
                <tr><td><%=or.getOrderID()%></td>
                    <td><%=or.getOrderDate()%></td>
                    <td><%=or.getShipDate()%></td>
                    <td><%= status[or.getStatus()]%><br/>
                        <%if (or.getStatus() == 1)tmp="cancel";else if(or.getStatus() == 3) tmp="reorder";%>
                        <a href="MainController?action=<%=tmp%>&orderID=<%=or.getOrderID()%>"><%=tmp%></a> 
                    </td>
                    <td><a href="MainController?orderid=<%=or.getOrderID()%>&action=detail">detail</a></td> </tr>               
            </table>
            <% }
            } else{
            %>
            <p>You don't have any order yet</p>
            <%}%>
        </section>

        <footer><%@include file="footer.jsp" %></footer>
            <%}else{%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>
