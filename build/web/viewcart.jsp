<%-- 
    Document   : viewcart
    Created on : Oct 8, 2022, 9:17:10 PM
    Author     : Admin
--%>

<%@page import="dto.Plant"%>
<%@page import="dao.PlantDao"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>

        <section>
            <%String name = (String) session.getAttribute("name");
                  if (name != null) {%>
            <h3>Welcome <%=session.getAttribute("name")%> back</h3>
            <h3><a href="MainController?action=logout">Logout</a></h3>
            <h3><a href="personalpage.jsp">Personal Page</a></h3>
            <%}%>

            <p style="color: red"><%=(request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%></p>
            <table width="100%" class="shopping">

                <% HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    Plant plant = null;

                    int money = 0;
                    if (cart != null) {
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            int quantity = cart.get(pid);
                            plant = PlantDao.getPlant(pid);
                            if (!cart.isEmpty()) {%>
                <tr><td>Product id</td><td>Image</td><td>Price</td><td>Quantity</td><td>Action</td></tr>
                <% }%>

                <form  action="MainController?pid=<%=pid%>&quantity=<%=quantity%>" method="post">
                    <tr><td><input type="hidden" value="<%=pid%>"name="pid"><a href="GetPlantServlet?pid=<%=pid%>"><%=pid%></a></td>
                        <td><img src='<%=plant.getImgpath()%>' class="plantimg"/></td>
                        <td><%=plant.getPrice()%></td>
                        <td><input type="number" value="<%=quantity%>"name="quantity"> </td>
                        <td><input type="submit" value="update" name="action"><input type="submit" value="delete" name="action"></td>
                            <%money += plant.getPrice() * quantity;%>
                    </tr>

                </form>
                <%
                    }
                } else {
                %>
                <tr><td>Your cart is empty</td></tr>
                <%}%>
                <tr><td>Total money: <%=money%> </td></tr>
            </table>
        </section>

        <section>
            <form action="MainController" method="post">
                <input type="submit" value="saveorder" name="action" class="submitorder">

            </form>

        </section>


        <footer>
            <%@include file="footer.jsp"%>
        </footer>

    </body>
</html>
