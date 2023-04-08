<%-- 
    Document   : personalpage
    Created on : Oct 1, 2022, 4:54:07 PM
    Author     : Admin
--%>

<%@page import="dto.OrderDetail"%>
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
            if (name == null) {
        %>
        <p><font color="red">you must login to view this page</p>
        <p></p>
        <%} else {%> 
        <header>
            <%@include file="headerlogineduser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%=name%> comeback</h3>
            <form method="post" action="MainController"><input  type="submit" value="logout" name="action"></form>
        </section>
        <section>
            <%  String orderID = request.getParameter("orderid");
                if (orderID != null) {
                    int orderIDI = Integer.parseInt(orderID.trim());
                    ArrayList<OrderDetail> list = OrderDao.getOrderDetail(orderIDI);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail or : list) {%>
            <table class="order"> 
                <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Quantity</td></tr>
                <tr><td><%=or.getOrderID()%></td>
                    <td><%=or.getPlantID()%></td>
                    <td><%=or.getPlantName()%></td>
                    <td><img src="<%=or.getImgPath()%>" class="plantimg"/><br/><%=or.getPrice()%></td>
                    <td><%=or.getQuantity()%></td>
                    <%money = money + or.getPrice() * or.getQuantity(); %>
                </tr>               
            </table>
            <% }%>
            <h3>Total money: <%=money%></h3>
            <%  } else {
            %>
            <p>You don't have any order yet</p>
            <%} }
                    }
                %>
        </section>

        <footer><%@include file="footer.jsp" %></footer>

    </body>
</html>
