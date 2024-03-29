/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myservlet;

import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AdminDisplayServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Account> list=(ArrayList) request.getAttribute("list");
            HttpSession s=request.getSession();
            Account admin=(Account) s.getAttribute("acc");
            if(admin!=null){
                 
            out.print("<H1>Welcome "+admin.getFullname()+" back !!!</H1> ");
            if(list!=null&&list.size()>0){
                out.print("<table>");
                out.print("<tr>");
                out.print("<th>ID</th>");
                out.print("<th>FullName</th>");
                out.print("<th>Status</th>");
                out.print("</tr>");
                
                for(Account acc:list){
                out.print("<tr>");
                out.print("<td>"+acc.getAccID()+"</td>");
                out.print("<td>"+acc.getFullname()+"</td>");
                out.print("<td>"+acc.getStatus()+"</td>");
                if(acc.getRole()!=1){
                    String tmp=(acc.getStatus()==1)?"Block":"UnBlock";
                    out.print("<td><a href='MainController?email="+acc.getEmail()+"&action=block'>"+tmp+"</a>");
                }
                out.print("</tr>");
            }
                out.print("</table>");
                out.print("<h1><a href='index.jsp'>Home</a></h1>");
            }else{
                response.sendRedirect("AdminServlet");
            }
            }else{
                response.sendRedirect("index.jsp");
            }
           
        }catch(Exception e){
            
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
