 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SupportDAO;
import DTO.Member;
import DTO.Support;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Tien Dat
 */
public class SupportServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String idMember = request.getParameter("idMember");

        SupportDAO dao = new SupportDAO();
        String idCoach = dao.getCoachIdByMember(idMember);

        request.setAttribute("idMember", idMember);
        request.setAttribute("idCoach", idCoach);

        request.getRequestDispatcher("support.jsp").forward(request, response);

        

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
//        HttpSession session = request.getSession();
//        String role = (String) session.getAttribute("role");
//        SupportDAO dao = new SupportDAO();
//
//        try {
//            String idMember = request.getParameter("idMember");
//            String idCoach = request.getParameter("idCoach");
//            String authorSend = request.getParameter("authorSend"); // 'member' hoặc 'coach'
//            String content = request.getParameter("content");
//
//            Support support = new Support();
//            support.setIdMember(idMember);
//            support.setIdCoach(idCoach);
//            support.setAuthorSend(authorSend);
//            support.setContent(content);
//            support.setFeedbackDate(new java.sql.Date(System.currentTimeMillis()));
//
//            dao.insertMessage(support);
//
//            // Sau khi gửi, redirect lại trang chat với member tương ứng
//            if ("member".equalsIgnoreCase(role)) {
//                // member chat, redirect về support page
//                response.sendRedirect("SupportServlet");
//            } else if ("coach".equalsIgnoreCase(role)) {
//                // coach chat, redirect kèm param selectedMember để load tin nhắn
//                response.sendRedirect("SupportServlet?selectedMember=" + idMember);
//            } else {
//                response.sendRedirect("login.jsp");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Error: " + e.getMessage());
//            request.getRequestDispatcher("support.jsp").forward(request, response);
//        }
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
