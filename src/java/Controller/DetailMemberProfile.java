/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CoachDao;
import DAO.MemberDao;
import DTO.Coach;
import DTO.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nghia
 */
@WebServlet(name = "DetailMemberProfile", urlPatterns = {"/DetailMemberProfile"})
public class DetailMemberProfile extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailMemberProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailMemberProfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(false);
        MemberDao userDAO = new MemberDao();
        CoachDao coachDao = new CoachDao();
        try {

            String idMember = (String) session.getAttribute("id");
            String idCoach=coachDao.getCoachIdFromMember(idMember);
            Coach coachDTO= coachDao.getCoachById(idCoach);
            
            // Truy vấn thông tin chi tiết của người dùng
            Member member = userDAO.getMemberById(idMember);

            if (member != null) {
                // Nếu tìm thấy người dùng, truyền thông tin vào request
                request.setAttribute("member", member);
                request.setAttribute("coach", coachDTO);
                // Chuyển tiếp dữ liệu đến JSP để hiển thị
                request.getRequestDispatcher("profile.jsp").forward(request, response);

            } else {
                // Nếu không tìm thấy người dùng, hiển thị thông báo lỗi
                response.getWriter().println("No user found with the username: " + idMember);
            }

        } catch (Exception e) {
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
