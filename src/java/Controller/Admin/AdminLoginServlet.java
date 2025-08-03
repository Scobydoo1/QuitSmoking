/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.AdminDao;
import DAO.CoachDao;
import DTO.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.MemberDao;
import DAO.QuitPlanDAO;
import java.util.Map;

/**
 *
 * @author Nguyen Tien Dat
 */
public class AdminLoginServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            AdminDao dao = new AdminDao();
            Admin admin = dao.login(username, password); // kiểm tra trong DB

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);

                //Lay so luong member
                MemberDao memberDAO = new MemberDao();
                int totalMembers = memberDAO.getTotalMemberCount();
                session.setAttribute("totalMembers", totalMembers);

                //lay so luong coach
                CoachDao coachDAO = new CoachDao();
                int totalCoaches = coachDAO.getTotalCoachCount();
                session.setAttribute("totalCoaches", totalCoaches);

                //lay so luong nguoi dang ky
                QuitPlanDAO qprDAO = new QuitPlanDAO();
                int totalRegisteredMembers = qprDAO.getRegisteredMemberCount();
                session.setAttribute("totalRegisteredMembers", totalRegisteredMembers);
                //lay so luong thanh vien theo tung goi
                Map<String, Integer> memberCountByPlan = qprDAO.getMemberCountByQuitPlan();
                session.setAttribute("memberCountByPlan", memberCountByPlan);

                response.sendRedirect("adminDashboard.jsp");
            } else {
                request.setAttribute("username", username);
                request.setAttribute("error", "Sai tài khoản hoặc mật khẩu.");
                request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống.");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
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
