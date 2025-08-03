/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDao;
import DAO.SystemDao;
import DTO.RegistrationPayment;
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
@WebServlet(name = "QuitPlanRegister", urlPatterns = {"/QuitPlanRegister"})
public class QuitPlanRegister extends HttpServlet {

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
        HttpSession session = request.getSession();
        MemberDao mem = new MemberDao();
        SystemDao sys = new SystemDao();
        String memberId = (String) session.getAttribute("id"); // IDMember
        String roleID = (String) session.getAttribute("role"); // IDMember
//        if (memberId == null || roleID.equalsIgnoreCase("coach")) {
//            // Chưa đăng nhập → chuyển về login
//            request.getRequestDispatcher("homepage.jsp").forward(request, response);
//
//            return;
//        }

        RegistrationPayment re = sys.getByMember(memberId);
        String status = null;

// Kiểm tra nếu không có dữ liệu (memberId không tồn tại trong bảng), thực thi trường hợp else
        if (re != null && memberId != null  && !roleID.equalsIgnoreCase("coach")) {
            status = re.getStatus();
        } else {
            // Nếu không tìm thấy memberId trong cơ sở dữ liệu, thực thi trực tiếp vào trường hợp else
            String goal = request.getParameter("goal");

            if ("Silver".equalsIgnoreCase(goal)) {
                response.sendRedirect("GOISILIVER.jsp");
            } else if ("Gold".equalsIgnoreCase(goal)) {
                response.sendRedirect("GOIGOLD.jsp");
            } else {
                response.sendRedirect("GOIDIAMOND.jsp");
            }
            return; // Đảm bảo không tiếp tục kiểm tra status nữa khi không có dữ liệu
        }

// Nếu status là null hoặc không phải "completed"
        if (status != null && !status.equalsIgnoreCase("completed")) {
            request.setAttribute("error", "Bạn đã trong khóa, không thể đăng ký khóa mới");
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
            return;
        }

// Nếu status là null hoặc không phải "completed"
        if (status != null && !status.equalsIgnoreCase("completed")) {
            request.setAttribute("error", "Bạn đã trong khóa, không thể đăng ký khóa mới");
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
            return;
        } else {
            String goal = request.getParameter("goal");

            if ("Silver".equalsIgnoreCase(goal)) {
                response.sendRedirect("GOISILIVER.jsp");
            } else if ("Gold".equalsIgnoreCase(goal)) {
                response.sendRedirect("GOIGOLD.jsp");
            } else {

                response.sendRedirect("GOIDIAMOND.jsp");
            }
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
