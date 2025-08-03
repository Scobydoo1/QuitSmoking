/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import DAO.QuitPlanDAO;
import DTO.QuitPlan;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "QuitplanManagerServlet", urlPatterns = {"/QuitplanManagerServlet"})
public class QuitplanManagerServlet extends HttpServlet {

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
            out.println("<title>Servlet QuitplanManagerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuitplanManagerServlet at " + request.getContextPath() + "</h1>");
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
        List<QuitPlan> list = new ArrayList<>();
        QuitPlanDAO dao = new QuitPlanDAO();
        list = dao.getAllQuitPlans();

        // 2. Đặt vào request và forward đến JSP
        request.setAttribute("quitplans", list);
        request.getRequestDispatcher("AdminQuitPlan.jsp")
                .forward(request, response);
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
        String idQuitPlan = request.getParameter("idQuitPlan");
        QuitPlanDAO dao = new QuitPlanDAO();
        // 2. Gọi DAO xóa
        boolean success = dao.deleteQuitPlan(idQuitPlan);

        // 3. Lưu thông báo vào session nếu cần
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("msg", "Xóa QuitPlan " + idQuitPlan + " thành công.");
        } else {
            session.setAttribute("msg", "Xóa thất bại hoặc không tìm thấy IDQuitPlan = " + idQuitPlan);
        }

        // 4. Chuyển về trang quản lý QuitPlan để load lại danh sách
        response.sendRedirect("QuitplanManagerServlet");
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
