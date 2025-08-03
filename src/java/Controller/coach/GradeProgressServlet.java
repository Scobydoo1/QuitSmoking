/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.coach;

import DAO.ProgressLogDAO;
import DTO.ProgressLog;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Tien Dat
 */
public class GradeProgressServlet extends HttpServlet {

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
            out.println("<title>Servlet GradeProgressServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GradeProgressServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int idLog = Integer.parseInt(request.getParameter("idLog"));
            int point = Integer.parseInt(request.getParameter("point"));

            ProgressLogDAO dao = new ProgressLogDAO();
            ProgressLog log = dao.getById(idLog);

            if (log == null) {
                request.setAttribute("errorMessage", "Không tìm thấy nhật ký để chấm điểm.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Chỉ cho phép chấm nếu trạng thái là "submit"
            if (!"submit".equalsIgnoreCase(log.getStatus())) {
                request.setAttribute("errorMessage", "Chỉ có thể chấm điểm các tiến trình đã gửi.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            log.setPoint(point);
            boolean updated = ProgressLogDAO.update(log);

            if (updated) {
                // Quay về trang xem tiến trình của member
                response.sendRedirect("ViewProgressServlet?id=" + log.getIdMember());
            } else {
                request.setAttribute("errorMessage", "Không thể lưu điểm.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
