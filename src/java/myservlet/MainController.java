/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private String url  ;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            if (action == null||action.equals("")||action.equals("search")) {
                url = "index.jsp";
            } else if (action.equals("login")) {
                url = "LoginServlet";
            } else if (action.equals("logout")) {
                url="LogoutServlet";
            } else if(action.equals("register")){
                url = "RegisterServlet";
            }else if(action.equals("block")){
                url="BlockServlet";
            }else if(action.equals("addtocart")){
                url="AddToCartServlet";
            }else if(action.equals("viewcart")){
                url="viewcart.jsp";
            }else if(action.equals("update")){
                url="UpdateCartServlet";
            }else if(action.equals("saveorder")){
                url="SaveShoppingCartServlet";
            }else if(action.equals("detail")){
                url="orderdetails.jsp";
            }else if(action.equals("delete")){
                url="DeleteCartServlet";
            }else if(action.equals("manageaccounts")){
                url="manageaccounts.jsp";
            }else if(action.equals("updateaccountstatus")){
                url="UpdateAccountStatusServlet";
            }else if(action.equals("find")){
                url="SearchByDate";
            }else if(action.equals("cancel")){
                url="CancelServlet";
            }else if(action.equals("reorder")){
                url="ReOrderServlet";
            }else if(action.equals("order")){
                url="ViewOrdersServlet";
            }else if(action.equals("changeprofile")){
                url="changeprofile.jsp";
            }else if(action.equals("change")){
                url="ChangeProfileServlet";
            }else if(action.equals("searchAccount")){
                url="manageaccounts.jsp";
            }else if(action.equals("manageorders")){
                url="manageorders.jsp";
            }else if(action.equals("searchByEmail")){
                url="manageorders.jsp";
            }else if(action.equals("searchByDate")){
                url="manageorders.jsp";
            }else if(action.equals("manageplants")){
                url="manageplants.jsp";
            }else if(action.equals("managecategories")){
                url="managecategories.jsp";
            }else if(action.equals("updatePrice")){
                url="UpdatePriceServlet";
            }else if(action.equals("updateplantstatus")){
                url="UpdatePlantStatus";
            }else if(action.equals("createPlant")){
                url="createplant.jsp";
            }else if(action.equals("create")){
                url="CreatePlantServlet";
            }else if(action.equals("updateName")){
                url="UpdateNameServlet";
            }else if(action.equals("createCategory")){
                 url="createcategory.jsp";
            }else if(action.equals("createCat")){
                url="CreateCategoryServlet";
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
