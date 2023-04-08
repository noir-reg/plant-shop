 
<%@page import="dao.CategoryDao"%>
<%@page import="dto.Category"%>
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


        <table>
            <tr> 
                <th>Cate ID</th>
                <th>Name</th>

            </tr>
            <%
                ArrayList<Category> list = CategoryDao.getCategories();
                request.setAttribute("catList", list);

            %>
            <c:forEach var="cat" items="${requestScope.catList}">
                <tr>

                    <td><c:out value="${cat.cateID}"></c:out></td>

                        <td><form action="MainController?cateID=${cat.cateID}"  method="post"><input name="cateName" value="${cat.cateName}" /><input type="submit" value="updateName" name="action"></form>

                    </td>


                </tr>
            </c:forEach>
        </table>
        <h3><a href="MainController?action=createCategory">Create new Category</a></h3>
        <%} else {%>
        <h1 style="color: red;text-align: center">You need to login</h1>
        <%}%> 
        
    </body>
</html>

