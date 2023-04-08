/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SaveShoppingCartServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          HttpSession s=request.getSession(false);
          if(s!=null){
              String name=(String)s.getAttribute("name");
              String email=(String)s.getAttribute("email");
              HashMap<String,Integer> cart=(HashMap<String,Integer>)s.getAttribute("cart");
              if(cart!=null&&!cart.isEmpty()){
                  if(name==null||name.equals("")){
                      request.setAttribute("WARNING","you must login to finish the shopping");
                      request.getRequestDispatcher("viewcart.jsp").forward(request, response);
                      
                  }else{
                      boolean result=OrderDao.insertOrder(email, cart);
                      if(result){
                          s.setAttribute("cart", null);
                          request.setAttribute("WARNING", "your cart is saved successfully");
                         request.getRequestDispatcher("viewcart.jsp").forward(request, response);

                      }else{
                           request.setAttribute("WARNING", "these products are out of stock");
                         request.getRequestDispatcher("viewcart.jsp").forward(request, response);
                      }
                  }
              }
              else{
                   s.setAttribute("WARNING", "your cart is empty");
                         request.getRequestDispatcher("viewcart.jsp").forward(request, response);
              }
          }
          else{
              response.sendRedirect("index.jsp");
          }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SaveShoppingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SaveShoppingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
