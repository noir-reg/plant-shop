<%-- 
    Document   : index
    Created on : Oct 1, 2022, 2:54:14 PM
    Author     : Admin
--%>

<%@page import="dao.PlantDao"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.ArrayList"%>
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
            <% String keyWord = request.getParameter("txtsearch");
                String searchBy = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"out of stock", "available"};
                if (keyWord == null && searchBy == null) {
                    list = PlantDao.getPlants("", "");
                } else {
                    list = PlantDao.getPlants(keyWord, searchBy);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant p : list) {%>
            <table class="product">
                <tr>
                    <td><img src='<%=p.getImgpath()%>' class="plantimg"/></td>
                    <td>Product ID:<%=p.getId()%></td>
                    <td>Product Name:<%=p.getName()%></td>
                    <td>Price:<%=p.getPrice()%></td>
                    <td>Status:<%=tmp[p.getStatus()]%></td>
                    <td>Category:<%=p.getCatename()%></td>
                    <td><a href="MainController?action=addtocart&id=<%=p.getId()%>">Add to cart</a></td>
                </tr>   

            </table>
            <% }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
