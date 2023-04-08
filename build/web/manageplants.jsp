 
<%@page import="dto.Plant"%>
<%@page import="dao.PlantDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Account"%>
<%@page import="dao.AccountDao"%>
<%@page import="dao.OrderDao"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
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
            ${requestScope.updateResult}
            ${requestScope.sResult}
            
            <table>
                <tr><th>Image</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Status </th>
                    <th>Category ID</th>
                    <th>Description</th>

                </tr>
            <%
                ArrayList<Plant> list = PlantDao.getPlants();
                request.setAttribute("plantList", list);

            %>
            <c:forEach var="plant" items="${requestScope.plantList}">
                <tr>
                    <td><img src="${plant.imgpath}" class="plantimg"/></td>
                    <td><c:out value="${plant.id}"></c:out></td>
                    <td><c:out value="${plant.name}"></c:out></td>
                    <td><form action="MainController?plantID=${plant.id}"  method="post"><input name="price" value="${plant.price}" /><input type="submit" value="updatePrice" name="action"></form>
                        
                    </td>

                        <td> <c:if test="${plant.status eq 0}"><c:out value="out of stock"></c:out></c:if>
                        <c:if test="${plant.status eq 1}"><c:out value="available"></c:out></c:if>
 
                        </td>
                    <td><c:out value="${plant.category}"></c:out></td>
                    <td><c:out value="${plant.description}"></c:out>
                    
                    </td>
                     <td>
                         
                            <c:url var="link" value="MainController">
                                <c:param name="PID" value="${plant.id}"></c:param>
                                <c:param name="pStatus" value="${plant.status}"></c:param>
                                <c:param name="action" value="updateplantstatus"></c:param>
                            </c:url>
                            <a href="${link}">Change Status</a>
                        
                    </td>
                    </tr>
            </c:forEach>
        </table>
            <h3><a href="MainController?action=createPlant">Create new plant</a></h3>
        <%} else {%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
    </body>
</html>

